package com.person.target.springframedemo.domain.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户数据传输实体对象")
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 826639690508777218L;

    @ApiModelProperty(value = "用户id", name = "userId")
    @NotNull(message = "用户id不能为空", groups = {Update.class, Delete.class})
    private Long userId;

    @ApiModelProperty(value = "用户名", name = "userName")
    @NotBlank(message = "用户名不能为空", groups = {Add.class, Query.class})
    private String userName;

    @ApiModelProperty(value = "年龄", name = "age")
    @NotNull(message = "年龄不能为空", groups = {Add.class})
    private Integer age;

    @ApiModelProperty(value = "性别", name = "gender")
    @NotBlank(message = "性别不能为空", groups = {Add.class})
    private String gender;

    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private Date updateTime;

    public interface Add{}

    public interface Update{}

    public interface Delete{}

    public interface Query{}
}
