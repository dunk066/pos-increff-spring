package com.increff.pos.service;

import com.increff.pos.dao.BrandDao;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.pojo.InventoryPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private BrandDao dao;

    @Transactional
    public InventoryPojo add(InventoryPojo p) {
        dao.insert(p);
        return p;
    }

    @Transactional
    public InventoryPojo get(int id) {
        return dao.select(InventoryPojo.class, id);
    }
}
