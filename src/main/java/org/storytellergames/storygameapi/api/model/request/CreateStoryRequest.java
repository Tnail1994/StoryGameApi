package org.storytellergames.storygameapi.api.model.request;

public class CreateStoryRequest {
    private String title;
    private String topic;
    private int amountOfSentences;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public int getAmountOfSentences() {
        return amountOfSentences;
    }

    public void setAmountOfSentences(int amountOfSentences) {
        this.amountOfSentences = amountOfSentences;
    }
}
