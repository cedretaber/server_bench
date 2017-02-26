defmodule ElixirPhoenix.ApplicationView do
  use ElixirPhoenix.Web, :view

  def render("user.json", %{ user: user }),
    do: %{ id: user.id, name: user.name, dept: user.dept }
end
