package com.example.posts.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @BeforeAll
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }
    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach");
    }
    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }
    @Test
    void myTest(){
        int value = 100;
        Assertions.assertEquals(100,value);
    }

    //GIVEN - WHEN -THEN
    @Test
    void testConstructor(){
        // GIVEN
        int id = 2;
        String username = "toto";
        String password = "pass";
        // WHEN
        User user = new User(id,username,password);
        // THEN
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(username, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());

    }
}