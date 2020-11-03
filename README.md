# JEE_Projet
Projet Logiciel Location Vehicule

## Installation
### En mode Dev
Utilisation de docker et docker-compose.
Prérequis : 
- docker
- docker-compose

Dans inteliji (les autres je sais pas) :
`Build > Build Artifacts > LocaJee:war`
Cela va créer un dossier ./target,
Une fois fait, lancer docker-compose.

`sudo docker-compose up;`
Et tester localhost:8080 pour tomcat
Et tester localhost:8081 pour phpmyadmin

/!\ A noter que l'adresse de la base de données n'est pas localhost mais db /!\

Faire un Ctrl + C pour quitter.
En cas de problème, commencer par faire un `sudo docke-compose down -v`.

#### Hello World
Pour juste tester votre installation, 
Téléchargez : https://github.com/softwareyoga/docker-tomcat-tutorial/blob/master/sample.war dans votre ./target
Ensuite, lancer le docker compose.
**Et testez localhost:8080/sample**

