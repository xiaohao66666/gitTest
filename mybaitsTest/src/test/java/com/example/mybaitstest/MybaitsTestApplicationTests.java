package com.example.mybaitstest;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybaitsTestApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelectList() {
        //selectList()方法的参数：封装了查询条件
        //null：无任何查询条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    void contextLoads() {
    }

}
