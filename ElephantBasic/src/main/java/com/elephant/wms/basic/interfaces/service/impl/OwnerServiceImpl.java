package com.elephant.wms.basic.interfaces.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.infrastructure.mapper.OwnerMapper;
import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.basic.interfaces.service.convert.ServiceConvert;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OwnerServiceImpl implements OwnerService {

    @Resource
    private OwnerMapper ownerMapper;

    @Override
    public Optional<OwnerDTO> queryByCode(String code) {
        QueryWrapper<OwnerPO> query = new QueryWrapper<>();
        query.eq("code",code);
        OwnerPO entity =  ownerMapper.selectOne(query);
        return Optional.ofNullable(ServiceConvert.INSTANCE.toOwnerDTO(entity));
    }
}
