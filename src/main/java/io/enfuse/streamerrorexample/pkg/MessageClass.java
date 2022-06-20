package io.enfuse.streamerrorexample.pkg;

public class MessageClass {

    private String key;
    private String content;

    public MessageClass(String key, String content) {
        this.key = key;
        this.content = content;
    }

    public MessageClass() {
    }

    public String content() {
        return content;
    }

    public String key() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setContent(String content) {
        this.content = content;
    }
}