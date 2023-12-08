package com.elephant.app;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.infrastructure.mapper.ItemMapper;
import com.elephant.wms.basic.infrastructure.mapper.OwnerMapper;
import com.elephant.wms.basic.infrastructure.mapper.StorageMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.basic.infrastructure.po.ItemPO;
import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.input.core.enums.ReceiveNoticeStatus;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ElephantReceiveCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Resource
    OwnerMapper ownerMapper;

    @Resource
    ItemMapper itemMapper;

    @Resource
    StorageMapper storageMapper;

    @Resource
    private ReceiveNoticeMapper receiveNoticeMapper;

    @RepeatedTest(2)
    public void createReceiveNotice() throws Exception {

        Random random = new Random();

        QueryWrapper<OwnerPO> query = new QueryWrapper<>();
        query.last("limit 10");
        List<OwnerPO> list = ownerMapper.selectList(query);
        OwnerPO ownerPO = list.get(random.nextInt(list.size()));

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("ownerCode",ownerPO.getCode() );
        param.put("contactsPhone",random.nextInt(10)%2==0? "":"18673647367");
        param.put("contactsAddress","浙江省杭州市西湖区云梦路中大银座北门西北侧约80米");
        param.put("referenceCode","TestPO" + currentDateTime.format(formatter)
                + System.currentTimeMillis());

        mockMvc.perform(
                        post("/receive/notice/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }

    @RepeatedTest(1)
    public void createReceiveOrder() throws Exception {

        Random random = new Random();

        QueryWrapper<StoragePO> queryStoragePO = new QueryWrapper<>();
        queryStoragePO.last("limit 10");
        List<StoragePO> listStoragePO = storageMapper.selectList(queryStoragePO);
        StoragePO storagePO = listStoragePO.get(random.nextInt(listStoragePO.size()));

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("storageCode",storagePO.getCode() );
        param.put("operatorId",1l);

        mockMvc.perform(
                        post("/receive/order/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }


    @RepeatedTest(10)
    public void createReceiveNoticeDetail() throws Exception {

        Random random = new Random();

        QueryWrapper<ReceiveNoticePO> query = new QueryWrapper<>();
        query.last("limit 50");
        query.eq("status", ReceiveNoticeStatus.CREATE);
        List<ReceiveNoticePO> list = receiveNoticeMapper.selectList(query);
        ReceiveNoticePO noticePO = list.get(random.nextInt(list.size()));


        QueryWrapper<ItemPO> queryItem = new QueryWrapper<>();
        queryItem.last("limit 50");
        queryItem.eq("owner_code", noticePO.getOwnerCode());
        List<ItemPO> listItem = itemMapper.selectList(queryItem);
        ItemPO itemPO = listItem.get(random.nextInt(listItem.size()));

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("receiveNoticeCode",noticePO.getCode() );
        param.put("ownerCode",noticePO.getOwnerCode());
        param.put("itemCode",itemPO.getCode());
        param.put("quantity",random.nextInt(100));

        mockMvc.perform(
                        post("/receive/notice/detail/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }
}
