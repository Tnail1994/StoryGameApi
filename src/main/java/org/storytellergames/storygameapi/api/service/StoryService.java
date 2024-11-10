package org.storytellergames.storygameapi.api.service;

import org.springframework.stereotype.Service;
import org.storytellergames.storygameapi.api.model.domain.Story;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    private final List<Story> stories;

    public StoryService() {
        stories = new ArrayList<>();

        Story story1 = new Story("a", 2, "");
        stories.add(story1);
    }

    public Optional<Story> getStory(String title) {

        return stories.stream()
                .filter(story -> story.getTitle().equals(title))
                .findFirst();
    }

    public boolean createStory(String title, String topic, int amountOfSentences) {
        Optional<Story> storyModelOptional = getStory(title);
        if (storyModelOptional.isPresent()) {
            return false;
        }

        Story story = new Story(title,amountOfSentences,topic);

        stories.add(story);
        return true;
    }

    public List<Story> getStories(Boolean isCompleted) {

        if(isCompleted != null) {
            return stories.stream()
                    .filter(story -> story.isCompleted() == isCompleted)
                    .toList();
        }

        return stories.stream()
                .toList();
    }

    public Story addSentence(String title, String sentence) {
        Optional<Story> storyOptional = getStory(title);

        if (storyOptional.isEmpty()) {
            return null;
        }

        Story story = storyOptional.get();
        story.addSentence(sentence);
        return story;
    }
}
