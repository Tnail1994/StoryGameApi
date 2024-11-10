package org.storytellergames.storygameapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.storytellergames.storygameapi.api.controller.StoryController;
import org.storytellergames.storygameapi.api.model.request.CreateStoryRequest;
import org.storytellergames.storygameapi.api.service.StoryService;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoryControllerTests {

    @Mock
    private StoryService storyService;

    private StoryController storyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        storyController = new StoryController(storyService);
    }

    @Test
    void createStory_Success() {
        CreateStoryRequest storyDto = new CreateStoryRequest();
        storyDto.setTopic("Test Topic");
        storyDto.setAmountOfSentences(5);
        storyDto.setTitle("Test Title");

        when(storyService.createStory(anyString(), anyString(), anyInt())).thenReturn(true);

        ResponseEntity<Void> response = storyController.createStory(storyDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(storyService).createStory(anyString(), anyString(), anyInt());
    }

    @Test
    void createStory_Failure() {
        CreateStoryRequest storyDto = new CreateStoryRequest();
        when(storyService.createStory(anyString(), anyString(), anyInt())).thenReturn(false);

        ResponseEntity<Void> response = storyController.createStory(storyDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}