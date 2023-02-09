package com.noticeboard.Controller;


import com.noticeboard.Dao.AdminRepository;
import com.noticeboard.Dao.UserRepository;
import com.noticeboard.Entity.Admin;
import com.noticeboard.Entity.Result;
import com.noticeboard.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @PostMapping("/login")
    @ResponseBody()
    public Result login(@RequestParam("username") String username,
                        @RequestParam("pwd") String pwd,
                        HttpSession session){
        Result result = new Result();
        Admin admin;
        result.setCode(Result.ResultCode.FAIL);
        if (Objects.equals(username, "") || Objects.equals(pwd, "")){
            result.setMsg("用户名和密码不能为空");
        }else {
            admin = adminRepository.findByUsernameAndPassword(username,pwd);
            if (admin!=null){
                result.setData(admin);
                result.setMsg("登陆成功");
                result.setCode(Result.ResultCode.OK);
                session.setAttribute("admin",admin);
            }
        }
//        List
//        <Map<String, Object>> result = jdbcTemplate.queryForList(String.format("select * from admin where name = \'%s\' and pwd = \'%s\'", username, pwd));
//        System.out.println(result.get(0).keySet());
//        System.out.println(result.get(0).values());
        return result;
    }

    @RequestMapping("/main")
    public String main(HttpSession session){
        if (session.getAttribute("admin")==null)
            return "/error";
        else
            return "admin/main";
    }
    @RequestMapping(value = {"/login.html","/login"})
    public String loginView(){
        return "admin/login";
    }
}
