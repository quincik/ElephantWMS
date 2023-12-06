package com.elephant.wms.inventory.interfaces.service.impl;

import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.inventory.core.enums.OptEnum;
import com.elephant.wms.inventory.core.enums.ScenarioEnum;
import com.elephant.wms.inventory.interfaces.service.InventoryService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.inventory.interfaces.service.convert.ServiceConvert;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

@Component("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Produce
    ProducerTemplate producerTemplate;

    @Override
    public Result<Boolean> adjustInventory(ModifyInventoryParam modifyParam) {

        // TODO 参数检查
        ModifyInventoryBO modify =
                ServiceConvert.INSTANCE.toModifyInventoryBO(modifyParam);
        modify.setScenario(ScenarioEnum.valueOf(modifyParam.getScenario()));

        Result<Boolean> result = producerTemplate
                .requestBodyAndHeader("direct:modifyInventory",null,
                        "modify",modify,Result.class);
        return result;
    }
}
