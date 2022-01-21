package com.increff.pos.dto;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import com.increff.pos.util.ConvertUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
public class BrandDto {
    @Autowired
    private BrandService brandService;
    // todo modal add
    // todo row-wise brand,html
    // todo icon edit
    // todo report json --> pdf (ui)
    public BrandMasterPojo addBrand(BrandForm f) throws ApiException {
        checkForm(f);
        BrandMasterPojo p = ConvertUtil.convertBrandFormtoBrandMasterPojo(f);
        return brandService.add(p);
    }
    public List<BrandData> searchBrandData(BrandForm form)  {
        List<BrandMasterPojo> list = brandService.searchBrandCategoryData(form);
        List<BrandData> reqList = new ArrayList<>();
        for(BrandMasterPojo p:list){
            reqList.add(ConvertUtil.convertBrandMasterPojotoBrandData(p));
        }
        return reqList;
    }
     // todo check on all warnings --> reduce
    public BrandData getBrandData(int id) {
        return ConvertUtil.convertBrandMastePojotoBrandData(brandService.get(id));
    }

    public List<BrandData> getAllBrand(){
        List<BrandMasterPojo> p = brandService.getAll();
        List<BrandData> brandDataList = new ArrayList<>();
        for(BrandMasterPojo b:p){
            brandDataList.add(ConvertUtil.convertBrandMastePojotoBrandData(b));
        }
        return brandDataList;
    }

    // todo shift + f6 --> select all

    public BrandMasterPojo updateBrand(int id,BrandForm f) throws ApiException {
        checkForm(f);
        BrandMasterPojo p = ConvertUtil.convertBrandFormtoBrandMasterPojo(f);
        return brandService.update(id,p);
    }

    public BrandMasterPojo getByBrandCategory(BrandForm f) throws ApiException {
        return brandService.getByBrandCategory(f);
    }

    private void checkForm(BrandForm f) throws ApiException {
        if(f.getCategory() == null || f.getBrand() == null){
            throw new ApiException("No brand and category provided");
        }
    }


}
