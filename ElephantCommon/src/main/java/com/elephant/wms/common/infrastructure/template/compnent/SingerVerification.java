package com.elephant.wms.common.infrastructure.template.compnent;

import com.elephant.wms.common.infrastructure.object.Result;
import jakarta.annotation.Nonnull;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class SingerVerification<T> implements Processor {

    protected abstract Class<T> getType();

    /**
     * 用于 Entity 信息的必填校验
     * @param entity
     * @return
     */
    protected @Nonnull List<String> verifiedEntityExt(@Nonnull T entity){
        return new LinkedList<>();
    };

    /**
     * 用于对应具体 verified 业务要求校验
     * 具体 verified 主要逻辑实现
     * @param entity
     * @param exchange
     * @return
     */
    protected  @Nonnull List<String> verifiedExt(@Nonnull T entity,Exchange exchange){
        return new LinkedList<>();
    };

    private @Nonnull List<String> verified(T entity,Exchange exchange){
        List<String> result = verifiedEntityExt(entity);
        if( ! result.isEmpty() ) return result;
        result.addAll(verifiedExt(entity,exchange));
        return result;
    };

    /**
     * 工具函数 用于追加 verified 错误信息
     * @param errors
     * @param error
     */
    protected static void appendError(List<String> errors,String error){
        if(!error.isEmpty()) errors.add(error);
    }
    protected static void appendError(List<String> errors,List<String> error){
        if(!error.isEmpty()) errors.addAll(error);
    }

    /**
     * 工具函数 用于做 Optional 空对象错误信息追加
     * @param optional
     * @param error
     * @return
     */
    protected static String emptyError(Optional optional,String error){
        if(optional.isEmpty()) return error;
        return "";
    }

    protected static String emptyError(List list,String error){
        if(list.isEmpty()) return error;
        return "";
    }

    protected static String notEmptyError(List list,String error){
        if(!list.isEmpty()) return error;
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
