package com.noticeboard.Entity;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * 参考文章：
 * https:blog.csdn.net/weixin_46102505/article/details/124146371
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Integer id;

    //不使用@column注解，会自动生成列名
    //用户名可能会有重复的问题
    @Column(name = "username")
    public String username;
    @JsonIgnore()
    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
