# MONGODBCRUD

An application illustrating crud operations of mongodb

## How to run the application

1)Clone this code in to your local folder
git clone https://github.com/sxc37701/MONGODBCRUD.git

2)Open windows console command prompt and Navigate to MONGODBCRUD\target

3)Run the below command
java -jar crud-0.0.1-SNAPSHOT.jar

4)Application starts running at port :8989

## Rest End points

##

3.Display the movie and show’s detail using title.
GET : http://localhost:8989/api/{title}

4. Retrieve all the movies and shows in database.
   GET :http://localhost:8989/api

5. Insert the new movie and show.--multiple data documents allowed
   POST:http://localhost:8989/api
   Sample data:

   ***

[ {
"id": 54,
"title": "Family Guy1",
"clips_count": 300,
"description": "test desc",
"episodes_count": 300,
"genres": [
"Animation and Cartoons~Primetime Animation",
"Teen",
"Comedy~Sitcoms"
],
"score": 8.35370739,
"seasons_count": 20,
"company": "FOX_Test",
"released_at": "2023-11-21T19:01:09.000+00:00",
"rating": "TV-MA1"
}
]

1. Update the movie and show information using title. (By update only title, description, score, and rating)
   PATCH:http://localhost:8989/api/{title}
   Sample Data:

   ***

   {
   "title":"test",
   "description": "test desc111",
   "score": 10.89,
   "rating": "TV-MA1_mod"
   }

2. Delete the movie and show information using title.
   DELETE:http://localhost:8989/api/{title}
