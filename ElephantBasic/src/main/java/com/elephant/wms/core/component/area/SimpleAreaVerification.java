package com.elephant.wms.core.component.area;

import com.elephant.wms.infrastructure.common.Result;
import com.elephant.wms.infrastructure.po.AreaPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SimpleAreaVerification implements Processor {

    protected  String verified(AreaPO entity,Integer index){

        String message = "";
        if(null == entity.getCode() || entity.getCode().isBlank()){
            message += index + "、库区编码为空。\n";
        }
        if(null == entity.getType()){
            message += index + "、库区类型为空。\n";
        }
        return message;
    }

    public void process(Exchange exchange) throws Exception {

        List<AreaPO> entities = exchange.getMessage().getBody(List.class);

        if(entities.isEmpty()){
            exchange.getMessage().setBody( new Result<>(false,"无库位信息。"));
            return;
        }

        String message = "";
        for (int i = 0; i < entities.size(); i++ ) {
            message += verified(entities.get(i),i);
        }

        if(!message.isBlank()){
            exchange.getMessage().setBody( new Result<>(false,message));
        }
    }

}
