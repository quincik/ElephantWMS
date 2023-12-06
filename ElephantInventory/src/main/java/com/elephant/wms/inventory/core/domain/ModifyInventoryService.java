package com.elephant.wms.core.domain;

import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
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
