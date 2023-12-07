package com.elephant.wms.basic.interfaces.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.infrastructure.mapper.ItemMapper;
import com.elephant.wms.basic.infrastructure.po.ItemPO;
import com.elephant.wms.basic.interfaces.service.ItemService;
import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.basic.interfaces.service.convert.ServiceConvert;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemServiceImpl implements ItemService {

    @Resource
    ItemMapper itemMapper;

    @Override
    public Optional<ItemDTO> queryByCode(String code, String ownerCode) {

        QueryWrapper<ItemPO> query = new QueryWrapper<>();
        query.eq("code",code);
        query.eq("owner_code",ownerCode);
        ItemPO itemPO = itemMapper.selectOne(query);
         return Optional.ofNullable(ServiceConvert.INSTANCE.toItemDTO(itemPO));
    }
}
