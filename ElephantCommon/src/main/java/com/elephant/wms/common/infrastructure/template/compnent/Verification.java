package com.elephant.wms.common.infrastructure.template.compnent;

import com.elephant.wms.common.infrastructure.object.Result;
import jakarta.annotation.Nonnull;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.List;

public abstract class Verification<T> implements Processor{

    /**
     * @param entity
     * @return
     * 返回 Not Null List
     */
    public abstract  @Nonnull List<String> verified(T entity);

    public void  process(Exchange exchange) throws Exception {

        List<T> entities = exchange.getMessage().getBody(List.class);
        HashMap<Integer,List<String>> detail = new HashMap<>();

        if(entities.isEmpty()){
            detail.put(0,List.of("信息为空"));
            exchange.getMessage().setBody(new Result<>(false, detail));
            return;
        }

        for (int i = 0; i < entities.size(); i++ ) {
            List<String> entityInfo = verified(entities.get(i));
            if( entityInfo.isEmpty() ) continue;
            detail.put(i+1,entityInfo);
        }

        if(detail.isEmpty()) {
            exchange.getMessage().setBody( new Result<>(entities));
            return;
        }

        exchange.getMessage().setBody( new Result<>(false,detail));
    }
}
