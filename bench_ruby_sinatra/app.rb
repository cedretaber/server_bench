require "sinatra"
require "mysql2"

configure { set :server, :puma }

get '/ping' do
  "pong"
end

get '/fact' do
  (1..params[:n].to_i).reduce(:*).to_s
end

def client
  return Thread.current[:db_client] if Thread.current[:db_client]
  client = Mysql2::Client.new(
    host: "localhost",
    username: "root",
    password: "root",
    database: "bench",
    encoding: 'utf8mb4',
    reconnect: true,
  )
  Thread.current[:db_client] = client
end

get '/users/:user_id' do
  require "json"
  client
    .prepare("SELECT * FROM users where id = ?")
    .execute(params[:user_id])
    .first
    .to_json
end
