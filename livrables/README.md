# JEE_Projet
Projet Logiciel Location Vehicule

## Installation

### En mode Dev

Utilisation de docker et docker-compose.
Prérequis : 
- docker
- docker-compose
- maven

Build le project :
`mvn clean package`

Lancer le serveur tomcat depuis docker :
`sudo docker-compose up`

/!\ A noter que l'adresse de la base de données n'est pas localhost mais db /!\

Faire un Ctrl + C pour quitter.

En cas de problème, faire :

`sudo docker-compose down -v`.

## Navigateur

URL de l'application : http://localhost:8080/LocaJee


