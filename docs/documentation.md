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

When the user sends a `CreateStoryRequest`, it is checked in the `StoryService` and if the check is positive, a domain model is created and saved. Tested with postman post request. Configure request as JSON format. And as request body example:
```json
{
    "title": "A chess random story",
    "topic": "Chess",
    "amountOfSentences": 5
}
``` 