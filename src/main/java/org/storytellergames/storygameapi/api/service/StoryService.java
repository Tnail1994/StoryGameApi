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

        stories.add(new Story("A random story #1", 5, ""));

        Story aChessStory = new Story("A chess story", 10 , "Chess");
        aChessStory.addSentence("The young grandmaster stared intently at the board, her brow furrowed in deep concentration.");
        aChessStory.addSentence("Her opponent, a grizzled veteran, surveyed the position with a wry smile.\"");
        aChessStory.addSentence("Suddenly, the grandmaster's eyes lit up as she spotted a daring combination.");
        aChessStory.addSentence("She swiftly executed her plan, sacrificing a knight to open up lines of attack.");
        aChessStory.addSentence("The veteran paused, stroking his chin thoughtfully. After several minutes of tense consideration, he made his move, closing off the grandmaster's threats.");
        aChessStory.addSentence("The crowd erupted in murmurs of appreciation.");
        aChessStory.addSentence("As they shook hands, the veteran smiled warmly, knowing that he had witnessed the birth of a future champion.");
        aChessStory.addSentence("");
        stories.add(aChessStory);

        stories.add(new Story("A random story #1", 5, ""));
        Story aRandomStory = new Story("Whats happening", 5 , "");
        aChessStory.addSentence("Bob suddenly realized he had forgotten to put on pants before leaving the house.");
        aChessStory.addSentence("As he rushed back inside, he tripped over his dog and landed face-first in a mud puddle.");
        aChessStory.addSentence("The entire neighborhood erupted in laughter, forever cementing Bob's reputation as the town's most absent-minded resident.");
        aChessStory.addSentence("Bob's wife watched the whole debacle unfold from the front window, shaking her head in exasperation.");
        aChessStory.addSentence("Later that evening, Bob swore he'd never leave the house without doing a full clothes check again.");
        stories.add(aRandomStory);
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
