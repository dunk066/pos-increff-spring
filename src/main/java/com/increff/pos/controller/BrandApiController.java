package com.increff.pos.controller;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandMasterPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api
@RestController
public class BrandApiController {

	@Autowired
	private BrandService service;

	@ApiOperation(value = "Adds a brand-category")
	@RequestMapping(path = "/api/brand", method = RequestMethod.POST)
	public void add(@RequestBody BrandForm form) throws ApiException {
		BrandMasterPojo p = convert(form);
		service.add(p);
	}

	@ApiOperation(value = "Gets a brand by ID")
	@RequestMapping(path = "/api/brand/{id}", method = RequestMethod.GET)
	public BrandData get(@PathVariable int id) throws ApiException {
		BrandMasterPojo p = service.get(id);
		return convert(p);
	}

	@ApiOperation(value = "Gets list of all brands")
	@RequestMapping(path = "/api/brand", method = RequestMethod.GET)
	public List<BrandData> getAll() {
		List<BrandMasterPojo> list = service.getAll();
		List<BrandData> list2 = new ArrayList<BrandData>();
		for (BrandMasterPojo p : list) {
			list2.add(convert(p));
		}
		return list2;
	}

	@ApiOperation(value = "Updates a brand")
	@RequestMapping(path = "/api/brand/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody BrandForm f) throws ApiException {
		BrandMasterPojo p = convert(f);
		service.update(id, p);
	}
	

	private static BrandData convert(BrandMasterPojo p) {
		BrandData d = new BrandData();
		d.setBrand(p.getBrand());
		d.setCategory(p.getCategory());
		d.setId(p.getId());
		return d;
	}

	private static BrandMasterPojo convert(BrandForm f) {
		BrandMasterPojo p = new BrandMasterPojo();
		p.setBrand(f.getBrand());
		p.setCategory(f.getCategory());
		return p;
	}

}
