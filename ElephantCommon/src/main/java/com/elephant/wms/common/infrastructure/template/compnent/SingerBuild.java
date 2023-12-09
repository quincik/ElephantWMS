package com.elephant.wms.common.infrastructure.template.compnent;

import com.elephant.wms.common.infrastructure.object.Result;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class SingerBuild<T>  implements Processor {

    protected abstract T buildExt(T entity,Exchange exchange);

    @Override
    public void process(Exchange exchange) throws Exception {
        T entity =
                (T) exchange.getMessage().getBody(Result.class).getData();
        exchange.getMessage().setBody(buildExt(entity,exchange));
    }

}
