package com.example.observer;

/**
 * description
 * @author lilei
 * @date 2022/7/28 15:21
 */
public class Client {
    public static void main(String[] args) {
        NewsPaper newsPaper = new NewsPaper();
        newsPaper.add(new Reader("张三"));
        newsPaper.add(new Reader("李四"));
        newsPaper.add(new Reader("王五"));
        newsPaper.setContent("号外！号外！");
    }
}
