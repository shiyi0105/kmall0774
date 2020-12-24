package com.kgc.kmall.manager.controller;


import com.kgc.kmall.bean.PmsSkuInfo;
import com.kgc.kmall.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api(tags = "sku相关接口",description = "提供sku相关的API")
public class SkuController {


    @Reference
    SkuService skuService;


    @ApiOperation("保存sku")
    @RequestMapping("/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo skuInfo){
        String result = skuService.saveSkuInfo(skuInfo);
        return result;
    }
}
