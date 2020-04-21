# todo
Application servant de demonstration pour l'architecture hexagonal


Pre-requis :

java 8
maven
docker

#create and start mongo container
- docker pull mongo
- docker run -d -p 27017-27019:27017-27019 --name mongo-todo-app mongo

command mongo :
- use todo
- db.createCollections("taks")
