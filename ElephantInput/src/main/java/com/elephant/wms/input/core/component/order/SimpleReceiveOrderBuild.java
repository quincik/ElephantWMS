package com.elephant.wms.input.core.component.order;

import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Component
public class SimpleReceiveOrderBuild implements Processor {

    private static String generateCode() {
        // 格式化日期时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = dateFormat.format(new Date());

        // 生成4位随机数
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(999); // 生成100到999之间的随机数

        // 构建收货单号
        return "RO" + formattedDate + randomNumber;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        ReceiveOrderPO entity =
                (ReceiveOrderPO) exchange.getMessage()
                        .getBody(Result.class).getData();

        entity.setCode(generateCode());
        exchange.getMessage().setBody(entity);

    }
}
