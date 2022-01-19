package com.increff.pos.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.increff.pos.model.AboutAppData;
import com.increff.pos.service.AboutAppService;

@Component
public class AboutAppDto {

	@Autowired
	private AboutAppService aboutAppService;

	public AboutAppData getNameandVersion() {
		AboutAppData d = new AboutAppData();
		d.setName(aboutAppService.getName());
		d.setVersion(aboutAppService.getVersion());
		return d;
	}

}