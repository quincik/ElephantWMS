package com.elephant.wms.input.core.component.notice;

import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.common.infrastructure.template.compnent.SingerBuild;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Nonnull;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Component
public class SimpleReceiveNoticeBuild extends SingerBuild<ReceiveNoticePO> {

    protected @Nonnull String generateCodeExt() {
        // 格式化日期时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = dateFormat.format(new Date());

        // 生成4位随机数
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(999); // 生成1000到9999之间的随机数

        // 构建收货通知单号
        return "ASN" + formattedDate + randomNumber;
    }

    protected void buildPhoneExt(ReceiveNoticePO entity, Exchange exchange){
        Optional<OwnerService.OwnerDTO> owner = exchange.getMessage()
                .getHeader("owner",Optional.class);
        if(null == entity.getContactsPhone() || entity.getContactsPhone().isEmpty()){
            entity.setContactsPhone(owner.get().getContactsPhone());
        }
    }

    @Override
    protected ReceiveNoticePO buildExt(ReceiveNoticePO entity, Exchange exchange) {
        entity.setCode(generateCodeExt());
        buildPhoneExt(entity,exchange);
        return entity;
    }

}
