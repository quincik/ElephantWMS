package com.elephant.wms;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.infrastructure.mapper.*;
import com.elephant.wms.basic.infrastructure.po.*;
import com.elephant.wms.inventory.core.enums.ScenarioEnum;
import com.elephant.wms.inventory.interfaces.service.InventoryService;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
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
class ElephantBasicCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Resource
    AreaMapper areaMapper;
    @Resource
    OwnerMapper ownerMapper;
    @Resource
    ItemMapper itemMapper;
    @Resource
    InventoryService inventoryService;
    @Resource
    ItemBatchMapper itemBatchMapper;
    @Resource
    StorageMapper storageMapper;

    @RepeatedTest(2)
    public void createStorage() throws Exception {

        Random random = new Random();

        QueryWrapper<AreaPO> query = new QueryWrapper<>();
        query.last("limit 10");
        List<AreaPO> list = areaMapper.selectList(query);
        AreaPO areaPO = list.get(random.nextInt(list.size()));


        int min = 100; // 最小的四位数是1000
        int max = 300; // 最大的四位数是9999
        int randomNum = random.nextInt(max - min + 1) + min;

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("code","TestS" + randomNum + "-" +areaPO.getCode() );
        param.put("areaCode",areaPO.getCode());
        param.put("type",areaPO.getType());

        mockMvc.perform(
                        post("/storage/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }

    @Test
    public void createArea() throws Exception {

        Random random = new Random();
        int min = 10; // 最小的四位数是1000
        int max = 99; // 最大的四位数是9999

        int randomNum = random.nextInt(max - min + 1) + min;

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("code","Test" + randomNum);
        param.put("type",1);

        mockMvc.perform(
                        post("/area/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }

    @Test
    public void createOwner() throws Exception {



        Random random = new Random();
        int min = 10; // 最小的四位数是1000
        int max = 43; // 最大的四位数是9999

        int randomNum = random.nextInt(max - min + 1) + min;

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("code","TestO" + randomNum);
        param.put("name","Test大象集团" + randomNum);
        param.put("contacts","Java研究者");
        param.put("contactsPhone","1578888888" );

        mockMvc.perform(
                        post("/owner/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }

    @RepeatedTest(1)
    public void createItem() throws Exception {

        Random random = new Random();

        QueryWrapper<OwnerPO> query = new QueryWrapper<>();
        query.last("limit 10");
        List<OwnerPO> list = ownerMapper.selectList(query);
        OwnerPO ownerPO = list.get(random.nextInt(list.size()));

        int min = 1000000; // 最小的四位数是1000
        int max = 9999999; // 最大的四位数是9999

        int randomNum = random.nextInt(max - min + 1) + min;

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("code","TestSN000" + randomNum);
        param.put("name","Test商品" + randomNum);
        param.put("ownerCode",ownerPO.getCode());
        param.put("expiry",random.nextInt(366) );
        param.put("weight",random.nextInt(1000*10) );

        mockMvc.perform(
                        post("/item/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }

    @RepeatedTest(1)
    public void createItemBath() throws Exception {

        Random random = new Random();

        QueryWrapper<ItemPO> query = new QueryWrapper<>();
        query.last("limit 10");
        List<ItemPO> list = itemMapper.selectList(query);
        ItemPO itemPO = list.get(random.nextInt(list.size()));

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Map<String, Object> param  = new LinkedHashMap<>();
        param.put("itemCode",itemPO.getCode());
        param.put("ownerCode",itemPO.getOwnerCode());
        param.put("manufacturingDate",currentDateTime.format(formatter) );

        mockMvc.perform(
                        post("/itemBatch/create").content(JSON.toJSONString(param))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").isBoolean());
    }

    @RepeatedTest(10)
    public void createInventory() throws Exception {

        Random random = new Random();

        QueryWrapper<ItemBatchPO> query = new QueryWrapper<>();
        query.last("limit 10");
        List<ItemBatchPO> list = itemBatchMapper.selectList(query);
        ItemBatchPO itemBatchPO = list.get(random.nextInt(list.size()));

        QueryWrapper<StoragePO> queryStoragePO = new QueryWrapper<>();
        queryStoragePO.last("limit 10");
        List<StoragePO> listStoragePO = storageMapper.selectList(queryStoragePO);
        StoragePO storagePO = listStoragePO.get(random.nextInt(listStoragePO.size()));

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        InventoryService.ModifyInventoryParam modify = new InventoryService.ModifyInventoryParam();
        modify.setAmount(random.nextInt(100));
        modify.setScenario(ScenarioEnum.RECEIVING.getCode());
        modify.setItemBatch(itemBatchPO.getId());
        modify.setStorageCode(storagePO.getCode());
        modify.setReferenceCode("TestASN" + currentDateTime.format(formatter)
                + System.currentTimeMillis());
        modify.setIdempotentKey(Md5Crypt.apr1Crypt(modify.getReferenceCode()));

        inventoryService.adjustInventory(modify);
    }


}