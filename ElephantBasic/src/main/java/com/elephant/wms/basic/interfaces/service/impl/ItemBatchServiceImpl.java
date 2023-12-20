package com.elephant.wms.basic.interfaces.service.impl;

import com.elephant.wms.basic.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.basic.interfaces.service.ItemBatchService;
import com.elephant.wms.basic.interfaces.service.convert.ServiceConvert;
import com.elephant.wms.common.infrastructure.object.Result;
import jakarta.annotation.Resource;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service("itemBatchService")
public class ItemBatchServiceImpl implements ItemBatchService {

    @Resource
    private ItemBatchMapper itemBatchMapper;

    @Produce
    ProducerTemplate producerTemplate;

    @Override
    public Optional<ItemBatchDTO> queryById(Long id) {
        ItemBatchPO entity =  itemBatchMapper.selectById(id);
        return Optional.ofNullable(ServiceConvert.INSTANCE.toItemBatchDTO(entity));
    }

    @Override
    public Optional<Long> create(Map<String, Object> param) {
        Result<Long> result = (Result<Long>)
                producerTemplate.requestBody("direct:createItemBatch", param);
        return Optional.ofNullable(result.getData());
    }

}
