package com.example.observer;

/**
 * description
 * @author lilei
 * @date 2022/7/28 15:15
 */
public class NewsPaper extends Subject {
    private String content;

    public void setContent(String content) {
        this.content = content;
        this.notifyObservers();
    }

    public String getContent() {
        return this.content;
    }
}
