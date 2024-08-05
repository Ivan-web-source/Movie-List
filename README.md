# My Personal Project (Movie Collection App)

## Application Mechanism

By using the Movie Collection application, users can create a **list of movie** into the app. The **movie** itself contains some relevant information such as:
- title
- director
- genre 
- duration 
for the movie. Then, users can enter their personal rating for the **movie**.

## Application Usage & Interest

As for the functionality of this application, **cinema enjoyers** can use this app as a collection for the movies that they've already watched. I am inspired to create this project because recently watching movies in cinema has been a part of my interest to spend some time together with my friends.

## User Stories Phase 0

- As a user, I want to be able to create a new movie and add it to a list of movie
- As a user, I want to be able to view a list of the titles of the movies in my list of movie 
- As a user, I want to be able to see the list of unwatched movies in my list
- As a user, I want to be able to give a rating to each movie that I have already watched 

## User Stories Phase 2

- As a user, I want to have the chance to save my Movie Collection to file
- As a user, I want to have an option to load my saved Movie Collection from file

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by selecting the button labelled as "Add Movie".
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting the button labelled as "View Movie List".
- You can locate my visual component by starting the GUI application.
- You can save the state of my application by selecting the button labelled as "Save Movie List".
- You can reload the state of my application by selecting the button labelled as "Load Movie List".

# Phase 4: Task 2

Mon Aug 05 12:36:42 PDT 2024
New movie: Movie 1 has been added.
Mon Aug 05 12:37:01 PDT 2024
New movie: Movie 2 has been added.
Mon Aug 05 12:37:14 PDT 2024
Movie Movie 2 has been watched.
Mon Aug 05 12:37:14 PDT 2024
Movie 2 rating added.

# Phase 4: Task 3

Based on looking at my UML design, one thing that I might consider to refactor if I have more time is to create a new abstract class "MovieUI" that can combine the similar methods from "MovieCollectionApp" and "MovieCollectionUI". And because of that change, both classes will extend "MovieUI" and override or add new methods that are based on the needs of presenting in console or graphical interface. In this way, I can organize the similar methods that these two classes implement while improving my project design.