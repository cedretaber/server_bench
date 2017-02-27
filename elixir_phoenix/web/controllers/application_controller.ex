defmodule ElixirPhoenix.ApplicationController do
  use ElixirPhoenix.Web, :controller

  alias ElixirPhoenix.User

  def ping(conn, _param) do
    text conn, "pong"
  end

  def fact(conn, %{ "n" => n }) do
    text conn, Enum.reduce(1..String.to_integer(n), &(&1 * &2))
  end

  def user(conn, %{ "user_id" => user_id }) do
    case Repo.get(User, user_id) do
      nil ->
        conn
        |> put_status(:not_found)
        |> text("")
      user -> render conn, user: user
    end
  end
end
