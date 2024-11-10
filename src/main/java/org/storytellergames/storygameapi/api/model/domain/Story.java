package org.storytellergames.storygameapi.api.model.domain;

public class Story {

    private boolean isCompleted;
    private String title;
    private String topic;
    private String[] sentences;
    private int amountOfSentences;

    public Story(String title, int amountOfSentences, String topic) {
        this.title = title;
        this.amountOfSentences = amountOfSentences;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String[] getSentences() {
        return sentences;
    }

    public void setSentences(String[] sentences) {
        this.sentences = sentences;
    }

    public int getAmountOfSentences() {
        return amountOfSentences;
    }

    public void setAmountOfSentences(int amountOfSentences) {
        this.amountOfSentences = amountOfSentences;
    }

    public void addSentence(String sentence) {
        if(sentences == null) {
            sentences = new String[1];
            sentences[0] = sentence;
        }
        else if(sentences.length < amountOfSentences){
            String[] newSentences = new String[sentences.length + 1];
            System.arraycopy(sentences, 0, newSentences, 0, sentences.length);
            newSentences[newSentences.length - 1] = sentence;
            setSentences(newSentences);
        }

        if(sentences.length >= amountOfSentences){
            setCompleted(true);
        }
    }
}
