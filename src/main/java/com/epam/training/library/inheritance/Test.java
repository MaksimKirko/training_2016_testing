package com.epam.training.library.inheritance;

public class Test {

    public static void main(String[] args) {

        System.out.println(SampleEnum.spring.name());
        System.out.println(SampleEnum.winter.name());

        System.out.println(SampleEnum.valueOf("spring"));
        System.out.println(SampleEnum.valueOf("winter"));

    }

}
