package com.person.target.springframedemo.service.impl;

import com.person.target.springframedemo.aop.BizException;
import com.person.target.springframedemo.domain.entity.auto.UserInfo;
import com.person.target.springframedemo.domain.entity.dto.UserInfoDTO;
import com.person.target.springframedemo.domain.enums.ResultEnum;
import com.person.target.springframedemo.mapper.auto.UserInfoMapper;
import com.person.target.springframedemo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    public void addUser(UserInfoDTO userInfoDTO) {
        isExistSameName(userInfoDTO.getUserName());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        userInfoMapper.insert(userInfo);
    }

    @Override
    @Transactional
    public void updateUser(UserInfoDTO userInfoDTO) {
        if(StringUtils.isNotBlank(userInfoDTO.getUserName())){
            isExistSameName(userInfoDTO.getUserName());
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        userInfo.setUpdateTime(new Date());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        userInfoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfoDTO> queryAllUsers() {
        List<UserInfo> userInfoList = userInfoMapper.selectAll();
        UserInfoDTO userInfoDTO;
        List<UserInfoDTO> resultList = new ArrayList<>();
        for (UserInfo item : userInfoList) {
            userInfoDTO = new UserInfoDTO();
            BeanUtils.copyProperties(item, userInfoDTO);
            resultList.add(userInfoDTO);
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoDTO queryUserById(Long userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO);
        return userInfoDTO;
    }

    private void isExistSameName(String userName){
        List<UserInfo> isExist = userInfoMapper.selectByExample(Example.builder(UserInfo.class).where(Sqls.custom()
                .andEqualTo("userName", userName)).build());
        if (!CollectionUtils.isEmpty(isExist)) {
            throw new BizException(ResultEnum.FAILED.getCode(), "存在相同的用户名");
        }
    }
}
