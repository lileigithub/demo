package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class test {
    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"), new Apple(120, "red"));
/*        List<Apple> greenApples =
                filterApples(inventory, new ApplePredicate() {
                    @Override
                    public boolean test(Apple apple) {
                        return "red".equals(apple.getColor());
                    }
                });*/
        List<Apple> greenApples =
                filterApples(inventory, (apple) -> "red".equals(apple.getColor()));
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
    }

    public static <T> List<T> filterApples(List<T> inventory,  //泛型为什么要往返回值左边加<T>？
                                           Predicate<T> p) {
        List<T> result = new ArrayList<T>();
        for (T apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 挑选大于150克的绿苹果
     *
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (p.test(apple))
                result.add(apple);
        }
        return result;
    }
}
