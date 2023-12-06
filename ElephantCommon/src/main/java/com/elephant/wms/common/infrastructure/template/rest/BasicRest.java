package com.elephant.wms.common.infrastructure.template.rest;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elephant.wms.common.infrastructure.object.Result;

import java.util.List;
import java.util.Map;

public abstract  class  BasicRest {

    private static final Long CURRENT  = 1L;
    private static final Long SIZE  = 10L;

    protected abstract BaseMapper getMapper();

    public <T,C>  IPage<C>  query(Map<String, Object> param,Class<C> entryClass){

        String jsonStr = JSONObject.toJSONString(param);
        C entry = JSONObject.parseObject(jsonStr, entryClass);

        QueryWrapper<C> query = new QueryWrapper<C>(entry);
        IPage<C> page = new Page<>(CURRENT,SIZE);
        if(null != param.get("current"))
            page.setCurrent((Long) param.get("current"));
        if(null != param.get("size"))
            page.setSize((Long) param.get("size"));

        return getMapper().selectPage(page, query);
    }
}
