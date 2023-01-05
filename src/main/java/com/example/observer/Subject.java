package com.example.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 * <p>
 * 另：java有自带的用于观察者模式的工具类java.util.Observable。
 * 和我们写的这个类基本一样。
 * 多了change参数，阻止没有数据更改的通知；
 * 多了集合转数组，加了同步，防止在通知Observer的同时注册或删除出错。
 * @author lilei
 * @date 2022/7/28 15:10
 */
public class Subject {
    List<Observer> observers = new ArrayList<>();

    void add(Observer observer) {
        observers.add(observer);
    }

    void remove(Observer observer) {
        observers.remove(observer);
    }

    void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}
