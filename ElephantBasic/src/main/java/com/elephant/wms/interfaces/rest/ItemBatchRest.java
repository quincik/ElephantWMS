package com.elephant.wms.interfaces.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.ItemBatchPO;
import com.elephant.wms.infrastructure.template.rest.BasicRest;
import com.elephant.wms.interfaces.rest.convert.Convert;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.camel.CamelContext;
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

    @PostMapping("/query")
    public Result<List<ItemBatchVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<ItemBatchPO> ItemBatchs = super.query(param, ItemBatchPO.class);
        Result<List<ItemBatchVO>> result = new Result<>(ItemBatchs.getCurrent(),ItemBatchs.getSize(),ItemBatchs.getTotal());
        result.setData(Convert.INSTANCE.toItemBatchVO(ItemBatchs.getRecords()));

        return  result;
    }

    @Override
    @PostConstruct
    protected void init() {
        mapper = itemBatchMapper;
    }
}
