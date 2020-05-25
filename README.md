# todo
Application servant de demonstration pour l'architecture hexagonal


Pre-requis :

java 8
maven
docker

## récuperer image mysql :
- docker pull mysql

## créer et lancer un container :
- docker run --detach --name=mysql-todo-app --env="MYSQL_ROOT_PASSWORD=mdp" mysql

## lancer et arreter le container :
- docker start/stop mysql-todo-app

## commandes MYSQL :
- docker exec -it mysql-todo-app bash
- mysql -p (mdp)
- use todoTest
