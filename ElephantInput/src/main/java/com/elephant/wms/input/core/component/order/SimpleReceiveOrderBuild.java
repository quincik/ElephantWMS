package com.elephant.wms.input.core.component.order;

import com.elephant.wms.common.infrastructure.template.compnent.SingerBuild;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class SimpleReceiveOrderBuild extends SingerBuild<ReceiveOrderPO> {

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
    protected ReceiveOrderPO build(ReceiveOrderPO entity, Exchange exchange) {
        entity.setCode(generateCode());
        return entity;
    }
}
