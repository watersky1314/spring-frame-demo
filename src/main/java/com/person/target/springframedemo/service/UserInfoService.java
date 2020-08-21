package com.person.target.springframedemo.service;

import com.person.target.springframedemo.domain.entity.dto.UserInfoDTO;

import java.util.List;

public interface UserInfoService {

    void addUser(UserInfoDTO userInfoDTO);

    void updateUser(UserInfoDTO userInfoDTO);

    void deleteUserById(Long userId);

    List<UserInfoDTO> queryAllUsers();

    UserInfoDTO queryUserById(Long userId);
}
