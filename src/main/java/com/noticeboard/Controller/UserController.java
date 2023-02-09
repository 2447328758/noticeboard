package com.noticeboard.Controller;


import com.noticeboard.Dao.UserRepository;
import com.noticeboard.Entity.Result;
import com.noticeboard.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/login")
    @ResponseBody()
    public Result login(@RequestParam("username") String username,
                        @RequestParam("pwd") String pwd,
                        HttpSession session){
        Result result = new Result();
        User user;
        result.setCode(Result.ResultCode.FAIL);
        if (Objects.equals(username, "") || Objects.equals(pwd, "")){
            result.setMsg("用户名和密码不能为空");
        }else {
            user = userRepository.findByUsernameAndPassword(username,pwd);
            if (user!=null){
                result.setData(user);
                result.setMsg("登陆成功");
                result.setCode(Result.ResultCode.OK);
                session.setAttribute("user",user);
            }
        }
//        List
//        <Map<String, Object>> result = jdbcTemplate.queryForList(String.format("select * from admin where name = \'%s\' and pwd = \'%s\'", username, pwd));
//        System.out.println(result.get(0).keySet());
//        System.out.println(result.get(0).values());
        return result;
    }

    @PostMapping("/register")
    @ResponseBody()
    public User register(@RequestParam("username") String username,
                      @RequestParam("pwd") String pwd){
        User user=new User();
        if (username == "" || pwd == "" || userRepository.findByUsername(username) != null)
            return user;
//        List
//        <Map<String, Object>> result = jdbcTemplate.queryForList(String.format("select * from admin where name = \'%s\' and pwd = \'%s\'", username, pwd));
//        System.out.println(result.get(0).keySet());
//        System.out.println(result.get(0).values());

        user.setPassword(pwd);
        user.setUsername(username);
        return userRepository.save(user);
    }


}
