package com.elephant.wms.interfaces.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.infrastructure.mapper.StorageMapper;
import com.elephant.wms.infrastructure.po.ItemBatchPO;
import com.elephant.wms.infrastructure.po.StoragePO;
import com.elephant.wms.interfaces.service.ItemBatchService;
import com.elephant.wms.interfaces.service.StorageService;
import com.elephant.wms.interfaces.service.convert.ServiceConvert;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public Optional<StorageDTO> queryByCode(String code) {
        QueryWrapper<StoragePO> query = new QueryWrapper<>();
        query.eq("code",code);
        StoragePO entity =  storageMapper.selectOne(query);
        return Optional.ofNullable(ServiceConvert.INSTANCE.toStorageDTO(entity));
    }

}
