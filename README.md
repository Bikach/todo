# todo
Application servant de demonstration pour l'architecture hexagonal


Pre-requis :

java 8
maven
docker

## récuperer image mongo :
- docker pull mongo

## créer et lancer un container :
- docker run -d -p 27017-27019:27017-27019 --name mongo-todo-app mongo

## lancer et arreter le container :
- docker start/stop mongo-todo-app

## commandes mongo :
- use todoTest
- db.createCollections("task")
