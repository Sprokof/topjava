## Requests for Meals
# Get
* url: /topjava/rest/user/meals
* url: /topjava/rest/user/meals/{id}
* url: /topjava/rest/user/meals/filter?startDate=2020-01-30&endDate=2020-01-30&startTime10:00:00&endTime=20:00:00
* Content-Type: application/json


# Delete
* /topjava/rest/user/meals/{id}

# Post 
* url: /topjava/rest/user/meals
* Content-Type: application/json

{
"dateTime":"2023-11-27T16:50:10", 
"description":"dinner", 
"calories":2010
}

# Put
* url: /topjava/rest/user/meals/{id}
* Content-Type: application/json

{
"id"=10000,
"dateTime":"2023-11-27T16:50:10",
"description":"dinner",
"calories":2020
}

## Requests for Admin

# Get
* url: /topjava/rest/admin/users
* url: /topjava/rest/admin/users{id}
* url: /topjava/rest/admin/users/{id}/with-meals
* Content-Type: application/json

# Post
* /topjava/rest/admin/users
  Content-Type: application/json

{
"name": "User",
"email": "new@google.ru",
"password": "password",
"enabled": true,
"registered": "2023-11-27T12:29:20",
"roles": [
"USER"
],
"caloriesPerDay": 2000,
"meals": null
}

# PUT 
* url: /topjava/rest/admin/users
* Content-Type: application/json

{
"id":100004,
"name": "User",
"email": "updated@google.ru",
"password": "password",
"enabled": true,
"registered": "2023-11-17:36:26",
"roles": [
"USER"
],
"caloriesPerDay": 2000,
"meals": null
}

# DELETE
* url: /topjava/rest/admin/users{id}

## Request for Profile

# Get

* url: topjava/rest/profile 
* url: topjava/rest/profile/with-meals 
* Content-Type: application/json

# Put

* url: topjava/rest/profile
* Content-Type: application/json

{
"name": "User",
"email": "user@yandex.ru",
"password": "password",
"enabled": true,
"registered": "2023-11-27T13:52:04.277+00:00",
"roles": [
"USER"
],
"caloriesPerDay": 2000,
"meals": null
}

# Delete
* url: topjava/rest/profile
