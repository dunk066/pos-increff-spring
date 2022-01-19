package com.increff.pos.service;

import com.increff.pos.dao.BrandDao;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.util.NormalizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BrandService {

	@Autowired
	private BrandDao dao;

	@Transactional(rollbackOn = ApiException.class)
	public BrandMasterPojo add(BrandMasterPojo p) throws ApiException {
		NormalizeUtil.normalizeBrandMasterPojo(p);
		getCheckBrandCategoryExist(p.getBrand(),p.getCategory());
		dao.insert(p);
		return p;
	}

	@Transactional
	public BrandMasterPojo get(int id) {
		return dao.select(BrandMasterPojo.class,id);
	}

	@Transactional
	public List<BrandMasterPojo> getAll() {
		return dao.selectAll();
	}

	@Transactional
	public BrandMasterPojo getByBrandCategory(BrandForm form) throws ApiException {
		NormalizeUtil.normalizeBrandForm(form);
		return getCheckForBrandCategory(form);
	}

	@Transactional(rollbackOn  = ApiException.class)
	public BrandMasterPojo update(int id, BrandMasterPojo p) throws ApiException {
		NormalizeUtil.normalizeBrandMasterPojo(p);
		getCheckBrandCategoryExist(p.getBrand(),p.getCategory());
		BrandMasterPojo ex = getCheck(id);
		ex.setBrand(p.getBrand());
		ex.setCategory(p.getCategory());
		dao.update(ex);
		return ex;
	}

	@Transactional
	public List<BrandMasterPojo> searchBrandCategoryData(BrandForm form) {
		NormalizeUtil.normalizeBrandForm(form);
		return dao.searchBrandData(form.getBrand(),form.getCategory());
	}

	@Transactional
	public BrandMasterPojo getCheck(int id) throws ApiException {
		BrandMasterPojo p = dao.select(BrandMasterPojo.class,id);
		if (p == null) {
			throw new ApiException("Brand and Category with given ID does not exist - id: " + id);
		}
		return p;
	}

	@Transactional
	public void getCheckBrandCategoryExist(String brand,String category) throws ApiException {
		BrandMasterPojo p = dao.selectByBrandCategory(brand,category);
		if (p != null) {
			throw new ApiException("brand and category already exists");
		}
	}

	@Transactional
	public BrandMasterPojo getCheckForBrandCategory(BrandForm form) throws ApiException {
		BrandMasterPojo p = dao.selectByBrandCategory(form.getBrand(),form.getCategory());
		if (p == null) {
			throw new ApiException("Brand-Category doesn't exist");
		}
		return p;
	}

}
