package com.example.observer;

/**
 * description
 * @author lilei
 * @date 2022/7/28 15:14
 */
public class Reader implements Observer {
    public Reader() {
        
    }

    public Reader(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public void update(Subject subject) {
        String content = ((NewsPaper) subject).getContent();
        System.out.println(name + "收到了报纸，内容是：" + content);
    }

}
