package com.kgc.kmall.manager.controller;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.service.AttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "attr相关接口",description = "提供sttr相关的Rest API")
public class AttrController {
    @Reference
    AttrService attrService;

    @ApiOperation("attr列表")
    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id){
        List<PmsBaseAttrInfo> infoList = attrService.select(catalog3Id);
        return infoList;
    }

    @ApiOperation("保存attr")
    @RequestMapping("/saveAttrInfo")
    public Integer saveAttrInfo(@RequestBody PmsBaseAttrInfo attrInfo){
        Integer i = attrService.add(attrInfo);
        return i;
    }
    @ApiOperation("查看属性")
    @RequestMapping("/getAttrValueList")
    public List<PmsBaseAttrValue> getArrtValueList(Long arrtId){
        List<PmsBaseAttrValue> valueList = attrService.getAttrValueList(arrtId);
        return valueList;
    }
}
