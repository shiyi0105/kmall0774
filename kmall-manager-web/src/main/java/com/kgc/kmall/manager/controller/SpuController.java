package com.kgc.kmall.manager.controller;

import com.kgc.kmall.bean.PmsBaseSaleAttr;
import com.kgc.kmall.bean.PmsProductImage;
import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.bean.PmsProductSaleAttr;
import com.kgc.kmall.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "spu相关接口",description = "提供spu相关 API")
public class SpuController {


    @Reference
    SpuService spuService;

    @Value("{fileServer.url}")
    String fileServer;


    @ApiOperation("spu列表")
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(Long catalog3Id){
        List<PmsProductInfo> infoList = spuService.spuList(catalog3Id);
        return infoList;
    }


    @ApiOperation("销售属性列表")
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> saleAttrList = spuService.baseSaleAttrList();
        return saleAttrList;
    }

    @ApiOperation("上传文件")
    @RequestMapping("fileUpload")
    public  String fileUpload(@RequestParam("file")MultipartFile file){
        try{
            String confFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(confFile);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String orginalFilename=file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(orginalFilename);
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            String path=fileServer;
            for (int i = 0; i < upload_file.length; i++) {
                String s = upload_file[i];
                System.out.println("s = " + s);
                path+="/"+s;
            }
            System.out.println(path);
            return path;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }



    }

    @ApiOperation("保存spu信息")
    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        Integer integer = spuService.saveSpuInfo(pmsProductInfo);
        return integer>0?"success":"fail";
    }

    @ApiOperation("spu销售属性列表")
    @RequestMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrList=spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrList;
    }

    @ApiOperation("spu图片列表")
    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(Long spuId){
        List<PmsProductImage> pmsProductImageList = spuService.spuImageList(spuId);
        return pmsProductImageList;
    }
}
