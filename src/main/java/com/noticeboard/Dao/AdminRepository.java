package com.noticeboard.Dao;

import com.noticeboard.Entity.Admin;

public class AdminRepository {
    Admin findByUsernameAndPassword(String username, String password);
}
