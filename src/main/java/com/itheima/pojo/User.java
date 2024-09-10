package com.itheima.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
//lombok, setter, getter, toString auto-generation
//pom依赖，实体类上加注解
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//转换为json字符串时忽略：
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
