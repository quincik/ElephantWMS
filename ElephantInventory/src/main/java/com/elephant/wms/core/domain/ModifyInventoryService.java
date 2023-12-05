package com.elephant.wms.core.domain;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.core.enums.InventoryOptEnum;
import com.elephant.wms.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import com.elephant.wms.interfaces.service.ItemBatchService;
import com.elephant.wms.interfaces.service.StorageService;
import jakarta.annotation.Resource;
import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ModifyInventoryService {

    @Produce("direct:modifyInventory")
    ProducerTemplate producerTemplate;

    @Transactional
    public Result<InventoryDetailPO> modifyInventory(ModifyInventoryBO modify){

        Result<InventoryDetailPO> result =
                (Result<InventoryDetailPO>) producerTemplate.requestBodyAndHeader(null,"modify",modify);

        return result;
    }
}
