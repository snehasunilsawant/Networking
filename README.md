# Networking
Spring boot Application

**Scope** :
Implemented multi-page website from scratch (using RESTful routing/Web APIs, MVC, user authentication (login/registration), front and backend validations, backend DB with basic CRUD) Focus was to integrate multiple APIs into project and move the item through front end to back end.

## Environment:
```
Framework : Spring Framework
Server : Apache
Database : MySQL ( Used repositories which eventually converts code into mySql query )
Languages : java
```
*It's a small project focusing on Spring Boot, database design and accessing session within website. Implemented within 2-3 hours.*

## Features:
1. User should able to register and login websitr sucessfully. Validation should be applied.

2. Website Dashboard should consist of:
  - Users' Name with welcome message
  - User's profile description
  - List of Logged in user's friends/network
  - List of Invitations by other users with options to accept or decline the invitation
      - Accept Invite : Adds person to the list of friends. Invitation will be disapear once selected
      - Ignore : Disregard the invite. Invitation will get disapear and will get added to all users.
  
3. All Users : 
  - Displays the list of people not yet logged users's networj NOR has not sent an invite to logged user
  - Links (On the names) redirect to the person's profile page
  - Connect : Sends an invitation to the person. The invitation will apear on person's end. One invitation is accepted, the perso will now                be the logged user's friend/network

4. User's Profile :
  - Displays user's details (name , desc etc )
