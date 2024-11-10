package org.storytellergames.storygameapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.storytellergames.storygameapi.api.model.domain.Story;
import org.storytellergames.storygameapi.api.model.request.CreateStoryRequest;
import org.storytellergames.storygameapi.api.model.response.GetStoryInfoResponse;
import org.storytellergames.storygameapi.api.service.StoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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


    @GetMapping
    public ResponseEntity<List<String>> getStories(
            @RequestParam(required = false) Boolean isCompleted) {
        List<Story> stories = storyService.getStories(isCompleted);
        return new ResponseEntity<>(stories.stream()
                .map(Story::getTitle)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<GetStoryInfoResponse> getStoryInfo(@PathVariable String title) {
        Optional<Story> storyOptional = storyService.getStory(title);

        if(storyOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Story story = storyOptional.get();

        GetStoryInfoResponse infoDto = new GetStoryInfoResponse(story.getTitle(), story.getTopic(), "", story.isCompleted());
        return new ResponseEntity<>(infoDto, HttpStatus.OK);
    }
}
