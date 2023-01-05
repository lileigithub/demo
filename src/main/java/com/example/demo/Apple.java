package com.example.demo;

/**
 * @author 李磊
 * @version v1.0
 * @description TODO
 * @date 2021/8/25 17:52
 */
public class Apple {
    public Apple(Integer weight, String color) {
        this.color = color;
        this.weight = weight;
    }

    private String color;
    private Integer weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
