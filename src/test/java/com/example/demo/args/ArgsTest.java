package com.example.demo.args;

import com.example.demo.ReflectDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.spy;

/**
 * description
 * @author lilei
 * @date 2022/4/12 13:47
 */

class ArgsTest {


    @Test
    void getBoolean() {
        Args149 spy = spy(new Args149("a,b", new String[]{"-a", "-b"}));
        boolean a = spy.getBoolean('c');
        System.out.println(a);

    }

    @Test
    void test2() throws Exception {
        List<Integer> stringList = new ArrayList<>();
        stringList.add(1);
        ReflectDto reflectDto = new ReflectDto();
        reflectDto.setStringList(stringList);
        test3(reflectDto);
    }

    @Test
    void test3(ReflectDto reflectDto) throws Exception {
        Field field = ReflectDto.class.getDeclaredField("stringList");
        Field string = ReflectDto.class.getDeclaredField("string");

        Type genericFieldType = field.getGenericType();
        Type genericType = string.getGenericType();
        System.out.println("String:" + genericType);
        System.out.println("String:" + (genericType == Integer.class));
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type rawType = aType.getRawType();
            System.out.println("rowTyp is List:" + (rawType == List.class));
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                System.out.println("fieldArgType:" + (fieldArgType == String.class));
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("fieldArgClass = " + fieldArgClass);
                System.out.println(String.class == fieldArgClass);
            }
            field.setAccessible(true);
            Object fieldValue = field.get(reflectDto);
            System.out.println(fieldValue.getClass());

            System.out.println(fieldValue);
            System.out.println(fieldValue instanceof List<?>);
        }

    }

    Object getFieldValue(String filename, Object vul) {
        Object fieldValue = new Object();
        try {
            Field field = ReflectDto.class.getDeclaredField(filename);
            Type genericFieldType = field.getGenericType();
            if (genericFieldType == String.class) {

            }
            if (genericFieldType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericFieldType;
                Type rawType = aType.getRawType();
                System.out.println("rowTyp is List:" + (rawType == List.class));
                Type[] fieldArgTypes = aType.getActualTypeArguments();
                for (Type fieldArgType : fieldArgTypes) {
                    Class fieldArgClass = (Class) fieldArgType;
                    System.out.println("fieldArgClass = " + fieldArgClass);
                    System.out.println(String.class == fieldArgClass);
                }
                field.setAccessible(true);
                //Object fieldValue = field.get(reflectDto);
                System.out.println(fieldValue.getClass());

                System.out.println(fieldValue);
            }


            field.setAccessible(true);
            fieldValue = field.get(vul);
        } catch (NoSuchFieldException e) {
            //log.error("入参取值报错", e);
        } catch (IllegalAccessException e) {
            //log.error("入参取值报错", e);
        }
        return fieldValue;
    }
}