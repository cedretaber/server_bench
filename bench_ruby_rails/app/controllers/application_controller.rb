class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  def ping
    render text: "pong"
  end

  def fact
    render text: calc_fact(params[:n])
  end

  def user_get
    render json: get_user(params[:user_id]).to_json
  end

  private

  def calc_fact(n)
    (1..n.to_i).reduce(:*)
  end

  def get_user(user_id)
    User.find_by(id: user_id.to_i)
  end
end
