# My steps documented
This document is intended to summarize the steps I took to complete the task. 

## Overview the task
First, I read through all the user stories carefully. Then I got my ideas down on paper, transferred these to draw.io and expanded them.

In the overview (picture below) you can see:

![Short overview of the taks and fist thoughts](imgs/ShortOverview.drawio.png "Short overview")

## Implementing the API

### Story #1: Make it possible to create a story on backend side

I want to provide an API post method to give the user the ability to create a new story. For that story some parameters like title, topic and amount of sentences is needed.

- Create the domain model `Story` and its implementation
- Create the `StoryController` and `StoryService`
- Create the functionality for creating a new story
    - The `StoryController` gets as input an object
    - Create `CreateStoryResponse` object
    - Save `Story`s in memory inside `StoryService` as a list (Simplification #1)

Simplifications:
- #1: Saving the domain models in memory
- #2: Unit-Testing only a small fraction

When the user sends a `CreateStoryRequest`, it is checked in the `StoryService` and if the check is positive, a domain model is created and saved. Tested with postman post request at `localhost:8080/api/stories`. Configure request as JSON format. And as request body example:
```json
{
    "title": "A random chess story",
    "topic": "Chess",
    "amountOfSentences": 5
}
``` 

### Story #2: Make it possible to get all ongoing and completed stories

I want to provide an API get method with an optional query param isCompleted. Depending on the param, the query will return all running or completed story titles. The user should then be able to request specific information about the story by providing the title of the story as a query param.  

- Create new get method for `StoryController` to get ongoing, completed or all stories.
- Extend `StoryService` to get ongoing, completed or all stories
- Also added new get method for `StoryController` to get information about a story. User must provide an existing story title
- Create new response model `GetStoryInfoResponse`


Simplifications:
- #3: No unit testing

Apis:
- `localhost:8080/api/stories`
- `localhost:8080/api/stories?isCompleted=false`
- `localhost:8080\api\stories\A chess random story`

### Story #3: Make it possible to update ongoing stories via adding sentence to it

I want to provide an API put method to update an existing ongoing story. The user send with title as path variable and the sentence to add as request param (Simplification #4).

- Create new put method for `StoryController` to add a sentence to a ongoing story. The same method gives back the an new info about the story
- Refactor: Renamed `GetStoryInfoResponse` -> `StoryInfoResponse`, because 'add sentence' method gives back the same object
- Added new fuctionality to `StoryService` to add a sentence to the story


Simplifications:
- #4: Better would be request body to scale it later on

Apis:
- `localhost:8080/api/stories/new-sentence-{title}?sentence={sentence}`

### Concluding words

For the backend side, no further action is required at this time, as Stories #4 and #5 are only needed for the interface. I started writing the user stories on the backend side, as the interface itself can be implemented abstractly. In the first commit I already added the cors configuration needed for the angular application on localhost.

All commits are tagged by user story number. Normally, I would not commit and push them directly into main, but keep them in a separate branch and merge them into the production branch after the task has been completed (e.g. with a final review). 

## Implementing the Web App

As I have already completed the backend side and worked my way through the user stories, the initial project structure can be implemented smoothly.

- Adding Bootstrap to the index.html file
- A `HomeComponent` is required. This means that the home component is defined as the `HomeComponent`, which navigates to other components.
- All necessary components  `Home`, `StoryList`, `StoryDetails` and `CreateStoryComponent` already created
- Navigation from `HomeComponent` to create new story and story listen wired together
- Added environment files, because the interface to be addressed does not change. Here is a simplification #5


Simplifications:
- #5: Using an unsecure connection `http://localhost:8080`

### Story #1: As a player, I want to create a new story

The project setup has already created a `HomeComponent` that allows you to navigate to different pages. So only the New Story component had to be adapted. The following has been added or changed in this user story

- Added the http provider to the app config
- created a `StoryService` that calls the create new story api from the backend
- The Create New Story UI component is created as a form and can only be submitted if all the required fields have been filled in correctly.

### Story #2: As a player, I want to see all ongoing and completed stories
This task is quick and easy to complete as only the stories are queried and can be displayed as list buttons.

- Fill `StoryListComponent`
- Extend `StoryService` to get all stories with completed query param
- Navigate to `StoryDetailsComponent`


### Story #3: As a player, I want to add a sentence to the story
Also a simple extension of the web page. Show the topic element on the page if it is set and the input field element only when the story is not completed yet.

- Added `Story` model to features
- Extend `StoryService` to send put request to add sentence to story
- Filled `StoryDetailsComponent` 