package com.elephant.wms.common.infrastructure.template.compnent;

import com.elephant.wms.common.infrastructure.object.Result;
import jakarta.annotation.Nonnull;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.Optional;

public abstract class SingerVerification<T> implements Processor {

    protected abstract Class<T> getType();

    protected abstract  @Nonnull List<String> verified(T entity,Exchange exchange);

    protected abstract @Nonnull List<String> verifiedEntity(T entity);

    protected static void verifiedExt(List<String> errors,String error){
        if(!error.isEmpty()) errors.add(error);
    }

    protected static String isEmpty(Optional optional,String error){
        if(optional.isEmpty()) return error;
        return "";
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        T entity = exchange.getMessage().getBody(getType());

        if(null == entity){
            exchange.getMessage().setBody(new Result<>(false, "信息为空"));
            return;
        }

        List<String> errors = verified(entity,exchange);

        if(errors.isEmpty()) {
            exchange.getMessage().setBody( new Result<>(entity));
            return;
        }

        exchange.getMessage().setBody( new Result<>(false,errors));
    }
}
