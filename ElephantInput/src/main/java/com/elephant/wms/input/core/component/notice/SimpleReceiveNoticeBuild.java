package com.elephant.wms.input.core.component.notice;

import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.basic.interfaces.service.StorageService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class SimpleReceiveNoticeBuild  implements Processor {

    private static String generateASN() {
        // 格式化日期时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = dateFormat.format(new Date());

        // 生成4位随机数
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(999); // 生成1000到9999之间的随机数

        // 构建收货通知单号
        return "ASN" + formattedDate + randomNumber;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        ReceiveNoticePO entity =
                (ReceiveNoticePO) exchange.getMessage()
                        .getBody(Result.class).getData();

        entity.setCode(generateASN());

        Optional<OwnerService.OwnerDTO> owner = exchange.getMessage()
                .getHeader("owner",Optional.class);
        if(null == entity.getContactsPhone() || entity.getContactsPhone().isEmpty()){
            entity.setContactsPhone(owner.get().getContactsPhone());
        }

        exchange.getMessage().setBody(entity);

    }
}
