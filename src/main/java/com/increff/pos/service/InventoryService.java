package com.increff.pos.service;

import com.increff.pos.ApiException;
import com.increff.pos.dao.InventoryDao;
import com.increff.pos.pojo.InventoryPojo;
import com.increff.pos.pojo.ProductMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryDao dao;

    @Transactional
    public InventoryPojo add(InventoryPojo inventoryPojo) {
        dao.insert(inventoryPojo);
        return inventoryPojo;
    }

    @Transactional
    public InventoryPojo get(int id) {
        return dao.select(InventoryPojo.class, id);
    }

    @Transactional
    public List<InventoryPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional
    public InventoryPojo getByProductId(ProductMasterPojo p) {
        return dao.selectByProductId(p.getId());
    }

    @Transactional
    public InventoryPojo update(int id,InventoryPojo p) throws com.increff.pos.ApiException {
        InventoryPojo newP = check(id);
        newP.setQuantity(p.getQuantity());
        dao.update(newP);
        return newP;
    }

    @Transactional
    public InventoryPojo check(int id) throws com.increff.pos.ApiException {
        InventoryPojo p = dao.select(InventoryPojo.class, id);
        if (p == null) {
            throw new ApiException("Inventory doesn't exist - id " + id);
        }
        return p;
    }
}
