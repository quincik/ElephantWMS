package com.elephant.wms.basic.interfaces.rest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.basic.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.basic.interfaces.rest.convert.RestConvert;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.rest.BasicRest;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/itemBatch")
public class ItemBatchRest extends BasicRest {

    @Override
    protected BaseMapper getMapper() {
        return itemBatchMapper;
    }

    @Data
    public static class ItemBatchVO{

        private Long id;
        private String code;
        private Integer type;
        private Integer length;
        private Integer height;
        private Integer width;
        private Integer status;
        private String createTime;
        private String modifyTime;
    }

    @Resource
    ItemBatchMapper itemBatchMapper;

    @Produce
    ProducerTemplate producerTemplate;

    @PostMapping("/query")
    public Result<List<ItemBatchVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<ItemBatchPO> ItemBatchs = super.query(param, ItemBatchPO.class);
        Result<List<ItemBatchVO>> result = new Result<>(ItemBatchs.getCurrent(),ItemBatchs.getSize(),ItemBatchs.getTotal());
        result.setData(RestConvert.INSTANCE.toItemBatchVO(ItemBatchs.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        Result<Boolean> result = (Result<Boolean>)
                producerTemplate.requestBody("direct:createItemBatch", param);
        return result;
    }
}
