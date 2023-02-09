package com.noticeboard.Dao;

import com.noticeboard.Entity.User;

public class UserRepository {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
