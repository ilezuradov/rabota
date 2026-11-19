# Project Backend 05 — Java_Bootcamp

**Summary:** In this project, you will learn how to work with JWT authorization and expand the capabilities of Java-based web applications using Spring.

💡 *[Click here](https://new.oprosso.net/p/4cb31ec3f47a4596bc758ea1861fb624) to share your feedback on this project.* It’s anonymous and will help our team improve the training. We recommend completing the survey right after finishing the project.

## Contents

  - [Chapter I](#chapter-i)
    - [Instructions](#instructions)
  - [Chapter II](#chapter-ii)
    - [General Information](#general-information)
      - [Token, Session Token, Refresh Token](#token-session-token-refresh-token)
  - [Chapter III](#chapter-iii)
    - [Project: Tic-Tac-Toe](#project-tic-tac-toe)
    - [Task 1. Switching Basic Authorization to JWT](#task-1-switching-basic-authorization-to-jwt)
    - [Task 2. Adding Support for Game History](#task-2-adding-support-for-game-history)
    - [Task 3. Adding Leaderboard Support](#task-3-adding-leaderboard-support)

## Chapter I
### Instructions

How to learn at “School 21”:

- Here, you’ll find a unique learning experience with a lot of freedom. You’re given a task and left to find your own way to solve it, using whatever resources work best for you — whether that’s the Internet or AI tools like GigaChat. Just be mindful of information quality: verify, think critically, analyze, and compare.
- Peer-to-peer (P2P) learning is the exchange of knowledge and experience with peers, where everyone acts as both mentor and student. This approach allows you to gain a deeper understanding of the material by learning from one another.
- Feel free to ask for help: around you are peers who are also navigating this path for the first time. Share your own experience and ideas with others.  Join Rocket.Chat to stay updated with the latest community announcements. 
- Your learning is meaningless if you just copy someone else’s solutions. When receiving help from others, always make sure you fully understand the “why”, “how”, and “purpose” behind the solution. Don’t be afraid to make mistakes. 
- Does the task seem impossible? Take a break, get some fresh air and clear your mind — this has helped many people. Maybe after that, the solution will come to you naturally.
- The learning process is just as important as the result. It’s not just about completing the task — it’s about understanding HOW to solve it. 

How to work with the project:

- Before starting, clone the project from GitLab into a repository with the same name.
- All files should be created inside the _src/_ folder of the cloned repository.
- After cloning the project, create a _develop_ branch and do all your development there. Then, push the _develop_ branch to GitLab.
- Your directory should not contain any files other than those specified in the assignments.

## Chapter II
### General Information

#### Token, Session Token, Refresh Token

A **token** is a unique string of characters that replaces the user's login and password, preventing leaks of confidential information. Tokens have a specific lifespan and stop working once they expire.

A **session token** gives users the right to perform available actions during their session. It is reusable and has a short lifespan.

A **refresh token** extends the validity of the session token. It is single-use and has a long lifespan.

**Topics to study:**

- Web application;
- JWT authorization;
- PostgreSQL;
- Spring.

## Chapter III

### Project: Tic-Tac-Toe

Use the backend project from the previous week (T04).

### Task 1. Switching Basic Authorization to JWT

- Add a role enumeration with a single **USER** role.
- Implement role support at all levels.
- Extend GrantedAuthority for the domain model of the role.
- Give users a list of roles.
- Create a JwtRequest model that includes a login and password.
- Create a JwtResponse model that includes a type, accessToken, and refreshToken.
- Create a RefreshJwtRequest model that includes refreshToken.
- Implement a JwtProvider class with the following methods:
  - use io.jsonwebtoken.Jwts.builder() to generate tokens;
  - a method to generate an **accessToken** from a User, storing information about the UUID and role in the token's claims (information about the object is stored in claims for future use);
  - a method of generating a **refreshToken** from a User, storing information about the UUID in the token's claims;
  - a method to validate the accessToken;
  - a method to validate the refreshToken;
  - a method to retrieve claims.
- Create a JwtAuthentication model that extends Authentication:
  - for getAuthorities(), return the roles;
  - for getPrincipal(), return the UUID;
  - for getName(), return the UUID;
  - for authentication status, add a separate field.
- Implement a JwtUtil class with a method to create JwtAuthentication from claims.
- Update the authorization service that uses the UserService, JwtProvider, and SecurityContextHolder to implement the following methods:
  - Modify the authorization method so that it now takes a JwtRequest and returns a JwtResponse.
  - Create a method to refresh the **accessToken** that takes a refreshToken and returns a JwtResponse.
  - Create a method to refresh the **refreshToken**, which also takes a refreshToken and returns a JwtResponse.
  - Create a method to get JwtAuthentication.
- Update the authorization controller by adding or modifying endpoints:
  - for user authorization;
  - for updating the **accessToken**;
  - for updating the **refreshToken**.
- Modify the behavior of the doFilter method in the AuthFilter class:
  - Retrieve the token from the Authorization header, which contains "Bearer {accessToken}".
  - Validate the token using the JwtProvider.
  - Get claims using JwtProvider.
  - Use JwtUtil to create JwtAuthentication from the claims.
  - Set up authentication using SecurityContextHolder.
- In the Spring Configuration, allow unauthenticated access to the endpoint to update the **accessToken**.
- Add an endpoint to retrieve user information from the **accessToken**.

### Task 2. Adding Support for Game History

- Add a creation date to the game model.
- Describe a database query to get all completed games by user's UUID.
- A game is considered completed when it has one of the following states:
  - The player with the UUID won;
  - A draw.
- Add a method to the game service to get all completed games by the user's UUID.
- Add an endpoint to get all completed games by **accessToken**, accessible only to authenticated users.

### Task 3. Adding Leaderboard Support

- Create a model for information about games won that includes the user's UUID and win ratio.
- Describe a database query in which:
  - Retrieve the ratio of the number of games won to the number of losses and draws for each user.
  - Sort by the win ratio in descending order.
  - Select the top N records, each of which contains the user's UUID and their win ratio.
- In the game service, add a method to get the top N best players.
- Add an endpoint to get the top N best players that takes N (the number of top players) and returns a list of the best players (UUID and login) with their win ratios.
- The endpoint to get the best players must be accessible only to authenticated users.

