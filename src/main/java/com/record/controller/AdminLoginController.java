package com.record.controller;

import com.record.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("admin/user")

public class AdminLoginController {

    @PostMapping("login")
    public Result login(){
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.success(map);
    }

    @GetMapping("info")
    public Result info(){
        Map<String,Object> inf = new HashMap<>();
        inf.put("roles","[admin]");
        inf.put("name","admin");
        inf.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(inf);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.success();
    }
}
