package com.kgc.kmall.manager.service;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrInfoExample;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.bean.PmsBaseAttrValueExample;
import com.kgc.kmall.manager.mapper.PmsBaseAttrInfoMapper;
import com.kgc.kmall.manager.mapper.PmsBaseAttrValueMapper;
import com.kgc.kmall.service.AttrService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class AttrServiceImpl implements AttrService {
    @Resource
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Resource
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Override
    public List<PmsBaseAttrInfo> select(Long catalog3Id) {
        PmsBaseAttrInfoExample example=new PmsBaseAttrInfoExample();
        PmsBaseAttrInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCatalog3IdEqualTo(catalog3Id);
        List<PmsBaseAttrInfo> infoList = pmsBaseAttrInfoMapper.selectByExample(example);

        for (PmsBaseAttrInfo pmsBaseAttrInfo : infoList) {
            PmsBaseAttrValueExample example1=new PmsBaseAttrValueExample();
            PmsBaseAttrValueExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andAttrIdEqualTo(pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.selectByExample(example1);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return infoList;
    }

    @Override
    public Integer add(PmsBaseAttrInfo attrInfo) {
        int i = pmsBaseAttrInfoMapper.insert(attrInfo);
        return i;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(Long attrId) {
        PmsBaseAttrValueExample example=new PmsBaseAttrValueExample();
        PmsBaseAttrValueExample.Criteria criteria = example.createCriteria();
        criteria.andAttrIdEqualTo(attrId);
        List<PmsBaseAttrValue> valueList = pmsBaseAttrValueMapper.selectByExample(example);

        return valueList;
    }


}
