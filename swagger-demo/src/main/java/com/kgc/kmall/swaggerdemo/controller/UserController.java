package com.kgc.kmall.swaggerdemo.controller;

import com.kgc.kmall.swaggerdemo.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户相关接口",description = "提供用户相关的Rest API")
public class UserController {



    @ApiOperation("新增用户接口")
    @PostMapping(value = "/add",produces = "application/json;charset=UTF-8")
    public boolean add(@RequestBody User user){
        return true;
    }

//    @ApiOperation("新增用户接口")
//    @PostMapping(value = "/add",produces = "application/json;charset=UTF-8")
//    public boolean add(@RequestBody @ApiParam(name = "user",value = "用户对象",required = true) User user){
//        return true;
//    }
}
