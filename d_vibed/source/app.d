import vibe.vibe;
import mysql;

void main()
{
	auto settings = new HTTPServerSettings;
	settings.port = 8080;
	settings.bindAddresses = ["::1", "127.0.0.1"];

	auto router = new URLRouter;
	router.get("/ping", &ping);
	router.get("/fact", &fact);
	router.get("/users/:userId", &user);

	listenHTTP(settings, router);

	logInfo("Please open http://127.0.0.1:8080/ in your browser.");
	runApplication();
}

void ping(HTTPServerRequest _req, HTTPServerResponse res)
{
	res.writeBody("pong");
}

void fact(HTTPServerRequest req, HTTPServerResponse res)
{
	import std.range : iota;
	import std.algorithm.iteration : reduce;
	import std.conv : to;

	auto n = 1.iota(req.query["n"].to!int+1).reduce!"a * b";
	res.writeBody(n.to!string);
}

struct User
{
	long id;
	string name;
	string dept;
}

MySQLClient client;
MySQLClient getClient()
{
	if (client is null)
		client = new MySQLClient("host=localhost;user=root;pwd=root;db=bench");
	
	return client;
}

void user(HTTPServerRequest req, HTTPServerResponse res)
{
	auto userId = req.params["userId"];
	auto conn = getClient().lockConnection();

	User user;
	conn.execute(
		"SELECT id, name, dept FROM users where id = ? limit 1",
		userId,
		(MySQLRow row) {
			user = row.toStruct!User;
		}
	);

	if (user.id == 0)
		res.writeBody("", 404);
	else
		res.writeJsonBody(user);
}