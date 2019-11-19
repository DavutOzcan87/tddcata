package com.dwt.tddcata;

import org.junit.After;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloTest {


    static int number = 5;
    @BeforeEach
    void beforeEach(){
        number = 5;
        System.out.println("Before each");
    }


    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all");
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each");
    }


    @AfterAll
    static void afterAll(){
        System.out.println("After all");
    }

    @Test
    public void canaryTest(){
        System.out.println(number);
        assertThat(number)
                .isEqualTo(5);
        number++;
    }

    @Test
    public void canaryTest2(){
        System.out.println(number);
        assertThat(number)
                .isEqualTo(5);
        number++;
    }



}