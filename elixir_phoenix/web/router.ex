defmodule ElixirPhoenix.Router do
  use ElixirPhoenix.Web, :router

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/", ElixirPhoenix do
    pipe_through :api

    get "/ping", ApplicationController, :ping
    get "/fact", ApplicationController, :fact
    get "/users/:user_id", ApplicationController, :user
  end
end
