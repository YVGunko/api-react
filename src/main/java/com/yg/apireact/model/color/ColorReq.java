package com.yg.apireact.model.color;

import java.util.List;
import java.util.stream.Collectors;

public class ColorReq {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ColorReq(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ColorReq() {
		super();
	}

	private String id;
	private String name;

 	public static ColorReq colorToColorReq (Color b) {
 		return new ColorReq(b.getId(), b.getName());		
 	}
 	public static List<ColorReq> colorToColorReq (List<Color> list) {
 		return list.stream().map(b -> colorToColorReq(b)).collect(Collectors.toList());		
 	}
}
