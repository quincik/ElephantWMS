package com.elephant.wms.basic.interfaces.service.impl;

import com.elephant.wms.basic.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.basic.interfaces.service.ItemBatchService;
import com.elephant.wms.basic.interfaces.service.convert.ServiceConvert;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("itemBatchService")
public class ItemBatchServiceImpl implements ItemBatchService {

    @Resource
    private ItemBatchMapper itemBatchMapper;

    @Override
    public Optional<ItemBatchDTO> queryById(Long id) {
        ItemBatchPO entity =  itemBatchMapper.selectById(id);
        return Optional.ofNullable(ServiceConvert.INSTANCE.toItemBatchDTO(entity));
    }

}
