package com.yg.apireact.model.filial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilialService {
	private static final Logger log = LoggerFactory.getLogger(FilialService.class);

	@Autowired
	FilialRepository repo;

	public String getName(Long filial_id) {
		try {
			if ((filial_id != null) && (filial_id != 0L)) { 
				return repo.findById(filial_id).orElseThrow().getName();
			} else {
				return "";
			}	
		} catch (Exception e) {
			log.error("FilialService -> getName -> ", e.getMessage());
			return "";
		}
	}
}
