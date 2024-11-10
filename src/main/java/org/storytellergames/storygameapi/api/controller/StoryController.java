package org.storytellergames.storygameapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.storytellergames.storygameapi.api.model.request.CreateStoryRequest;
import org.storytellergames.storygameapi.api.service.StoryService;


@RestController
@RequestMapping("/api/stories")
public class StoryController {
    private static final int MIN_AMOUNT_OF_SENTENCES = 5;
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }

    @PostMapping
    public ResponseEntity<Void> createStory(@RequestBody CreateStoryRequest createStoryRequest) {
        if(createStoryRequest.getTitle() == null ||
                createStoryRequest.getTitle().isEmpty() ||
                createStoryRequest.getAmountOfSentences() < MIN_AMOUNT_OF_SENTENCES){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean result = storyService.createStory(createStoryRequest.getTitle(), createStoryRequest.getTopic(), createStoryRequest.getAmountOfSentences());

        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
