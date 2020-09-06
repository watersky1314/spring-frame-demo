package com.person.target.springframedemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.target.springframedemo.domain.ResultVO;
import com.person.target.springframedemo.domain.entity.auto.UserInfo;
import com.person.target.springframedemo.domain.entity.dto.UserInfoDTO;
import com.person.target.springframedemo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(value = "用户信息控制器", tags = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增用户", notes = "用户信息管理", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 0, message = "success"), @ApiResponse(code = 500, message = "fail"),
            @ApiResponse(code = 400, message = "parameter error")})
    public ResultVO<Void> addUser(@RequestBody @Validated({UserInfoDTO.Add.class}) UserInfoDTO userInfoDTO) {
        userInfoService.addUser(userInfoDTO);
        return new ResultVO<>();
    }

    @PostMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Void> updateUser(@RequestBody @Validated({UserInfoDTO.Update.class}) UserInfoDTO userInfoDTO) {
        userInfoService.updateUser(userInfoDTO);
        return new ResultVO<>();
    }

    @GetMapping(value = "/deleteUserById/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据用户id删除用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Void> deleteUserById(@PathVariable("userId") Long userId) {
        userInfoService.deleteUserById(userId);
        return new ResultVO<>();
    }


    @PostMapping(value = "/queryAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询用户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 0, message = "success"), @ApiResponse(code = 500, message = "fail"),
            @ApiResponse(code = 400, message = "parameter error")})
    public ResultVO<List<UserInfoDTO>> queryAllUsers() {
        List<UserInfoDTO> userInfoDTOList = userInfoService.queryAllUsers();
        return new ResultVO<>(userInfoDTOList);
    }

    @GetMapping(value = "/queryUserByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据用户id查询用户",  httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<UserInfoDTO> queryUserById(@PathVariable("userId") Long userId) {
        UserInfoDTO userInfoDTO = userInfoService.queryUserById(userId);
        return new ResultVO<>(userInfoDTO);
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(new Date());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("张三");
        userInfo.setAge(13);
        userInfo.setGender("M");
        userInfo.setCreateTime(new Date());
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userInfoDTO);
        System.out.println(json);
        System.out.println(JSONObject.toJSON(userInfoDTO));
    }

}
