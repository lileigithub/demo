package com.example.optional;

import java.util.Optional;

/**
 * @author 李磊
 * @version v1.0
 * @description TODO
 * @date 2021/8/26 9:22
 */
public class TestOptional {
   /* public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }*/

/*    //避免NullPointerException的第一次尝试
      //每个null检查都会增加调用链上剩余代码的嵌套层数
    public String getCarInsuranceName(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }*/

/*    //过多的退出语句
    public String getCarInsuranceName(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }*/
public String getCarInsuranceName(Optional<Person> person) {
    return person.flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("Unknown");
}

}
class Person {
    private Optional<Car> car;
    public Optional<Car> getCar() { return car; }
}
class Car {
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() { return insurance; }
}
class Insurance {
    private String name;
    public String getName() { return name; }
}
