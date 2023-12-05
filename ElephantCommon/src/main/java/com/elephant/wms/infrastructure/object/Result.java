package com.elephant.wms.infrastructure.object;

import lombok.Data;

@Data
public class Result<T> {

    T data;

    Long current;
    Long size;
    Long total;
    Boolean success = true;
    String message ;

    public Result(Long current, Long size, Long total) {
        this.current = current;
        this.size = size;
        this.total = total;
    }

    public Result(){

    }
    public Result( T data){
        this.data = data;
    }
    public Result(Boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public Result(Boolean success,T data){
        this.data = data;
        this.success = success;
    }
}
