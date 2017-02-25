Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

  get '/ping' => 'application#ping'
  get '/fact' => 'application#fact'
  get '/users/:user_id' => 'application#user_get'
end
