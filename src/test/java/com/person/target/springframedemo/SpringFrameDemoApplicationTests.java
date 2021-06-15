package com.person.target.springframedemo;

import com.person.target.springframedemo.domain.entity.auto.UserInfo;
import com.person.target.springframedemo.mapper.auto.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringFrameDemoApplicationTests.class)
@MapperScan("com.person.target.springframedemo.mapper.auto")
@ActiveProfiles("dev")
class SpringFrameDemoApplicationTests {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private SpringFrameDemoApplicationTests tests;

    @Test
    @Transactional
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(123);
        userInfo.setUserName("张三");
        userInfo.setGender("M");
        userInfo.setCreateTime(new Date());
        userInfoMapper.insert(userInfo);
        tests.test2();
    }

    public void test2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(111);
        userInfo.setUserName("李四");
        userInfo.setGender("M");
        userInfo.setCreateTime(new Date());
        userInfoMapper.insert(userInfo);
        throw new RuntimeException("异常");
    }


}
