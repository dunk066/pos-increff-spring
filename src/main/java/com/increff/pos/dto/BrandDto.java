package com.increff.pos.dto;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import com.increff.pos.util.convertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandDto {
    @Autowired
    private BrandService brandService;

    public BrandMasterPojo addBrand(BrandForm f) throws ApiException {
        checkForm(f);
        BrandMasterPojo p = convertUtil.convertBrandFormtoBrandMasterPojo(f);
        return brandService.add(p);
    }

    public BrandData getBrandData(int id) {
        return convertUtil.convertBrandMastePojotoBrandData(brandService.get(id));
    }

    public List<BrandData> getAllBrand(){
        List<BrandMasterPojo> p = brandService.getAll();
        List<BrandData> reqList = new ArrayList<>();
        for(BrandMasterPojo b:p){
            reqList.add(convertUtil.convertBrandMastePojotoBrandData(b));
        }
        return reqList;
    }

    public BrandMasterPojo updateBrand(int id,BrandForm f) throws ApiException {
        checkForm(f);
        BrandMasterPojo p = convertUtil.convertBrandFormtoBrandMasterPojo(f);
        return brandService.update(id,p);
    }

    public BrandMasterPojo getByBrandCategory(BrandForm f) throws ApiException {
        return brandService.getByBrandCategory(f);
    }

    public void checkForm(BrandForm f) throws ApiException {
        if(f.getCategory() == null || f.getBrand() == null){
            throw new ApiException("No brand and category provided");
        }
    }


}
