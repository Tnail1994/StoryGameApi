package org.storytellergames.storygameapi.api.model.response;

public class StoryInfoResponse {
    private final String title;
    private final String topic;
    private final String textToShow;
    private final Boolean isCompleted;

    public StoryInfoResponse(String title, String topic, String textToShow, Boolean isCompleted) {
        this.title = title;
        this.topic = topic;
        this.textToShow = textToShow;
        this.isCompleted = isCompleted;
    }
    
    public String getTitle() {
        return title;
    }
    public String getTopic() {
        return topic;
    }
    public String getTextToShow() {
        return textToShow;
    }
    public Boolean getIsCompleted() {
        return isCompleted;
    }
}
