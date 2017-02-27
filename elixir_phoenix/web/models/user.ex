defmodule ElixirPhoenix.User do
  use ElixirPhoenix.Web, :model

  schema "users" do
    field :name, :string
    field :dept, :string
  end

  def changeset(user, params \\ %{}) do
    user
    |> cast(params, [:name, :dept])
    |> validate_required([:name, :dept])
  end
end
