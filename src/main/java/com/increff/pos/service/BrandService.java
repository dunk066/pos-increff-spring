package com.increff.pos.service;

import com.increff.pos.dao.BrandDao;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.util.StringUtil;
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
		normalize(p);
		getCheckBrand(p.getBrand(),p.getCategory());
		dao.insert(p);
		return p;
	}

	//how to implement this
	@Transactional(rollbackOn = ApiException.class)
	public BrandMasterPojo get(int id) throws ApiException {
		return getCheck(id);
	}

	@Transactional
	public List<BrandMasterPojo> getAll() {
		return dao.selectAll();
	}

	@Transactional(rollbackOn  = ApiException.class)
	public void update(int id, BrandMasterPojo p) throws ApiException {
		normalize(p);
		getCheckBrand(p.getBrand(),p.getCategory());
		BrandMasterPojo ex = getCheck(id);
		ex.setBrand(p.getBrand());
		ex.setCategory(p.getCategory());
		dao.update(ex);
	}

	@Transactional
	public BrandMasterPojo getCheck(int id) throws ApiException {
		BrandMasterPojo p = dao.selectById(id);
		if (p == null) {
			throw new ApiException("brand and category with given ID does not exit, id: " + id);
		}
		return p;
	}

	@Transactional
	public void getCheckBrand(String brand,String category) throws ApiException {
		BrandMasterPojo p = dao.selectByBrandCategory(brand,category);
		if (p == null) {
			throw new ApiException("brand and category already exists");
		}
	}

	protected static void normalize(BrandMasterPojo p) {
		p.setBrand(StringUtil.toLowerCase(p.getBrand()));
		p.setCategory(StringUtil.toLowerCase(p.getCategory()));
	}
}
