package com.yg.apireact.model.color;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yg.apireact.model.division.DivisionRepository;

@Service
public class ColorService {
	private static final Logger log = LoggerFactory.getLogger(ColorService.class);
	@Autowired
	ColorRepository repo;
	@Autowired
	DivisionRepository repoDiv;
	
	public ResponseEntity<List<ColorReq>> find(String divisionCode, String colorName) {
		List<ColorReq> respond = new ArrayList<ColorReq>();
		try {
			Assert.hasLength(divisionCode, "Division Code provided isn't valid");
			repoDiv.findById(divisionCode).orElseThrow(() -> new IllegalArgumentException(
					"Division not found exception. divisionCode=".concat(divisionCode)));

			if (StringUtils.isNotBlank(colorName)) {
				respond = ColorReq.colorToColorReq(repo.findByDivisionCodeAndNameContainingOrderByName(divisionCode, colorName)
						.orElseGet(() -> List.of(new Color())));
			} else {
				respond = ColorReq.colorToColorReq(repo.findByDivisionCodeOrderByName(divisionCode)
						.orElseGet(() -> List.of(new Color())));
			}
			return new ResponseEntity<>(respond, HttpStatus.OK);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			log.error("Color not found exception. divisionCode=".concat(divisionCode).concat("ColorName = ")
					.concat(colorName));
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Color find method's been raised Exception. Message=".concat(divisionCode).concat("ColorName = ")
					.concat(colorName), e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	ResponseEntity<ColorReq> find(String id) {
		try {
			Assert.hasLength(id, "id provided isn't valid");

			ColorReq respond = ColorReq.colorToColorReq(repo.findById(id).orElseThrow());

			return new ResponseEntity<>(respond, HttpStatus.OK);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			log.error("Color not found exception. id=".concat(id));
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Color find method's been raised Exception. Message=", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
