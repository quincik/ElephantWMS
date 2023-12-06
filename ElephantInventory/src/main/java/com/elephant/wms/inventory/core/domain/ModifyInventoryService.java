package com.elephant.wms.inventory.core.domain;

import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.inventory.infrastructure.po.InventoryDetailPO;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
