# JEE_Projet
Projet Logiciel Location Vehicule

## Installation

### En mode Dev

Utilisation de docker et docker-compose.
Prérequis : 
- docker
- docker-compose
- mvn (maven)

Tout d'abord on build le project:

`mvn package`

Clean :

`mvn clean`

On peut ensuite lancer le serveur tomcat & phpmyadmin depuis docker:

`sudo docker-compose up;`

/!\ A noter que l'adresse de la base de données n'est pas localhost mais db /!\

Faire un Ctrl + C pour quitter.

En cas de problème, commencer par faire un :

`sudo docke-compose down -v`.

#### Hello World

Pour tester l'installation :

http://localhost:8080/LocaJee


