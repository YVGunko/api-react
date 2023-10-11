package com.yg.apireact.model.outDoorOrderRow;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OutDoorOrderRowReq {
	/*
	 * OutDoorOrderRow((@NotNull String id, @NotNull OutDoorOrder outDoorOrder,
	 * String attribute, Integer number, String barcode, Product product, String
	 * size, Color color, Color liner, Color rant, Color shpalt, Color vstavka,
	 * Color gelenok, Color guba, Color kabluk,
	 * 
	 * Color matirovka,
	 * 
	 * Color pechat, Color proshiv, Color pyatka, Color sled, Color spoyler, Color
	 * ashpalt, Color other, Boolean prodir,
	 * 
	 * Boolean difersize, Boolean tert, Boolean frez, Boolean sample)
	 */
	public OutDoorOrderRowReq(String id, String order_id, String attribute, Integer number, String barcode,
			String product_id, String sProduct, String size, String color_id, String sColor, String liner_id,
			String sLiner, String rant_id, String sRant, String shpalt_id, String sShpalt, String vstavka_id,
			String sVstavka, String gelenok_id, String sGelenok, String guba_id, String sGuba, String kabluk_id,
			String sKabluk, String matirovka_id, String sMatirovka, String pechat_id, String sPechat, String proshiv_id,
			String sProshiv, String pyatka_id, String sPyatka, String sled_id, String sSled, String spoyler_id,
			String sSpoyler, String ashpalt_id, String sAshpalt, Boolean prodir, Boolean difersize, Boolean tert,
			Boolean frez, Boolean sample, String plastizol_id, String sPlastizol, Date createdAt) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.attribute = attribute;
		this.number = number;
		this.barcode = barcode;
		this.product_id = product_id;
		this.sProduct = sProduct;
		this.size = size;
		this.color_id = color_id;
		this.liner_id = liner_id;
		this.shpalt_id = shpalt_id;
		this.rant_id = rant_id;
		this.sColor = sColor;
		this.sLiner = sLiner;
		this.sShpalt = sShpalt;
		this.sRant = sRant;
		this.vstavka_id = vstavka_id;
		this.sVstavka = sVstavka;
		this.gelenok_id = gelenok_id;
		this.sGelenok = sGelenok;
		this.guba_id = guba_id;
		this.sGuba = sGuba;
		this.kabluk_id = kabluk_id;
		this.sKabluk = sKabluk;
		this.matirovka_id = matirovka_id;
		this.sMatirovka = sMatirovka;
		this.pechat_id = pechat_id;
		this.sPechat = sPechat;
		this.proshiv_id = proshiv_id;
		this.sProshiv = sProshiv;
		this.pyatka_id = pyatka_id;
		this.sPyatka = sPyatka;
		this.sled_id = sled_id;
		this.sSled = sSled;
		this.spoyler_id = spoyler_id;
		this.sSpoyler = sSpoyler;
		this.ashpalt_id = ashpalt_id;
		this.sAshpalt = sAshpalt;
		this.plastizol_id = plastizol_id;
		this.sPlastizol = sPlastizol;
		this.prodir = prodir;
		this.difersize = difersize;
		this.tert = tert;
		this.frez = frez;
		this.sample = sample;
		this.createdAt = createdAt;
	}

	public OutDoorOrderRowReq(String id, String order_id, String attribute, Integer number, String barcode,
			String product_id, String size, String color_id, String liner_id, String rant_id, String shpalt_id,
			String vstavka_id, String gelenok_id, String guba_id, String kabluk_id, String matirovka_id,
			String pechat_id, String proshiv_id, String pyatka_id, String sled_id, String spoyler_id, String ashpalt_id,
			Boolean prodir, Boolean difersize, Boolean tert, Boolean frez, Boolean sample, String plastizol_id) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.attribute = attribute;
		this.number = number;
		this.barcode = barcode;
		this.product_id = product_id;
		this.size = size;
		this.color_id = color_id;
		this.liner_id = liner_id;
		this.shpalt_id = shpalt_id;
		this.rant_id = rant_id;
		this.vstavka_id = vstavka_id;
		this.gelenok_id = gelenok_id;
		this.guba_id = guba_id;
		this.kabluk_id = kabluk_id;
		this.matirovka_id = matirovka_id;
		this.pechat_id = pechat_id;
		this.proshiv_id = proshiv_id;
		this.pyatka_id = pyatka_id;
		this.sled_id = sled_id;
		this.spoyler_id = spoyler_id;
		this.ashpalt_id = ashpalt_id;
		this.plastizol_id = plastizol_id;
		this.prodir = prodir;
		this.difersize = difersize;
		this.tert = tert;
		this.frez = frez;
		this.sample = sample;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor_id() {
		return color_id;
	}

	public void setColor_id(String color_id) {
		this.color_id = color_id;
	}

	public String getLiner_id() {
		return liner_id;
	}

	public void setLiner_id(String liner_id) {
		this.liner_id = liner_id;
	}

	public String getShpalt_id() {
		return shpalt_id;
	}

	public void setShpalt_id(String shpalt_id) {
		this.shpalt_id = shpalt_id;
	}

	public String getRant_id() {
		return rant_id;
	}

	public void setRant_id(String rant_id) {
		this.rant_id = rant_id;
	}

	public String getsColor() {
		return sColor;
	}

	public void setsColor(String sColor) {
		this.sColor = sColor;
	}

	public String getsLiner() {
		return sLiner;
	}

	public void setsLiner(String sLiner) {
		this.sLiner = sLiner;
	}

	public String getsShpalt() {
		return sShpalt;
	}

	public void setsShpalt(String sShpalt) {
		this.sShpalt = sShpalt;
	}

	public String getsRant() {
		return sRant;
	}

	public void setsRant(String sRant) {
		this.sRant = sRant;
	}

	public String getVstavka_id() {
		return vstavka_id;
	}

	public void setVstavka_id(String vstavka_id) {
		this.vstavka_id = vstavka_id;
	}

	public String getsVstavka() {
		return sVstavka;
	}

	public void setsVstavka(String sVstavka) {
		this.sVstavka = sVstavka;
	}

	public String getGelenok_id() {
		return gelenok_id;
	}

	public void setGelenok_id(String gelenok_id) {
		this.gelenok_id = gelenok_id;
	}

	public String getsGelenok() {
		return sGelenok;
	}

	public void setsGelenok(String sGelenok) {
		this.sGelenok = sGelenok;
	}

	public String getGuba_id() {
		return guba_id;
	}

	public void setGuba_id(String guba_id) {
		this.guba_id = guba_id;
	}

	public String getsGuba() {
		return sGuba;
	}

	public void setsGuba(String sGuba) {
		this.sGuba = sGuba;
	}

	public String getKabluk_id() {
		return kabluk_id;
	}

	public void setKabluk_id(String kabluk_id) {
		this.kabluk_id = kabluk_id;
	}

	public String getsKabluk() {
		return sKabluk;
	}

	public void setsKabluk(String sKabluk) {
		this.sKabluk = sKabluk;
	}

	public String getMatirovka_id() {
		return matirovka_id;
	}

	public void setMatirovka_id(String matirovka_id) {
		this.matirovka_id = matirovka_id;
	}

	public String getsMatirovka() {
		return sMatirovka;
	}

	public void setsMatirovka(String sMatirovka) {
		this.sMatirovka = sMatirovka;
	}

	public String getPechat_id() {
		return pechat_id;
	}

	public void setPechat_id(String pechat_id) {
		this.pechat_id = pechat_id;
	}

	public String getsPechat() {
		return sPechat;
	}

	public void setsPechat(String sPechat) {
		this.sPechat = sPechat;
	}

	public String getProshiv_id() {
		return proshiv_id;
	}

	public void setProshiv_id(String proshiv_id) {
		this.proshiv_id = proshiv_id;
	}

	public String getsProshiv() {
		return sProshiv;
	}

	public void setsProshiv(String sProshiv) {
		this.sProshiv = sProshiv;
	}

	public String getPyatka_id() {
		return pyatka_id;
	}

	public void setPyatka_id(String pyatka_id) {
		this.pyatka_id = pyatka_id;
	}

	public String getsPyatka() {
		return sPyatka;
	}

	public void setsPyatka(String sPyatka) {
		this.sPyatka = sPyatka;
	}

	public String getSled_id() {
		return sled_id;
	}

	public void setSled_id(String sled_id) {
		this.sled_id = sled_id;
	}

	public String getsSled() {
		return sSled;
	}

	public void setsSled(String sSled) {
		this.sSled = sSled;
	}

	public String getSpoyler_id() {
		return spoyler_id;
	}

	public void setSpoyler_id(String spoyler_id) {
		this.spoyler_id = spoyler_id;
	}

	public String getsSpoyler() {
		return sSpoyler;
	}

	public void setsSpoyler(String sSpoyler) {
		this.sSpoyler = sSpoyler;
	}

	public String getAshpalt_id() {
		return ashpalt_id;
	}

	public void setAshpalt_id(String ashpalt_id) {
		this.ashpalt_id = ashpalt_id;
	}

	public String getsAshpalt() {
		return sAshpalt;
	}

	public void setsAshpalt(String sAshpalt) {
		this.sAshpalt = sAshpalt;
	}

	public Boolean getProdir() {
		return prodir;
	}

	public void setProdir(Boolean prodir) {
		this.prodir = prodir;
	}

	public Boolean getDifersize() {
		return difersize;
	}

	public void setDifersize(Boolean difersize) {
		this.difersize = difersize;
	}

	public Boolean getTert() {
		return tert;
	}

	public void setTert(Boolean tert) {
		this.tert = tert;
	}

	public Boolean getFrez() {
		return frez;
	}

	public void setFrez(Boolean frez) {
		this.frez = frez;
	}

	public Boolean getSample() {
		return sample;
	}

	public void setSample(Boolean sample) {
		this.sample = sample;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public OutDoorOrderRowReq(@NotNull String id, String order_id, String attribute, Integer number, String barcode,
			String product_id) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.attribute = attribute;
		this.number = number;
		this.barcode = barcode;
		this.product_id = product_id;
	}
	public OutDoorOrderRowReq(String sProduct, Integer number) {
		super();
		this.sProduct = sProduct;
		this.number = number;
	}

	@JsonProperty("id")
	private String id;
	@JsonProperty("order_id")
	private String order_id;
	@JsonProperty("attribute")
	private String attribute;
	@JsonProperty("number")
	private Integer number;
	@JsonProperty("barcode")
	private String barcode;

	@JsonProperty("product_id")
	private String product_id;
	@JsonProperty("sProduct")
	private String sProduct;

	public String getsProduct() {
		return sProduct;
	}

	public void setsProduct(String sProduct) {
		this.sProduct = sProduct;
	}

	@JsonProperty("size")
	private String size;
	@JsonProperty("color_id")
	private String color_id;
	@JsonProperty("liner_id")
	private String liner_id;
	@JsonProperty("shpalt_id")
	private String shpalt_id;
	@JsonProperty("rant_id")
	private String rant_id;
	@JsonProperty("sColor")
	private String sColor;
	@JsonProperty("sLiner")
	private String sLiner;
	@JsonProperty("sShpalt")
	private String sShpalt;
	@JsonProperty("sRant")
	private String sRant;
	// ***Атрибут
	@JsonProperty("vstavka_id")
	private String vstavka_id;
	@JsonProperty("sVstavka")
	private String sVstavka;
	@JsonProperty("gelenok_id")
	private String gelenok_id;
	@JsonProperty("sGelenok")
	private String sGelenok;
	@JsonProperty("guba_id")
	private String guba_id;
	@JsonProperty("sGuba")
	private String sGuba;
	@JsonProperty("kabluk_id")
	private String kabluk_id;
	@JsonProperty("sKabluk")
	private String sKabluk;

	@JsonProperty("matirovka_id")
	private String matirovka_id;
	@JsonProperty("sMatirovka")
	private String sMatirovka;

	@JsonProperty("pechat_id")
	private String pechat_id;
	@JsonProperty("sPechat")
	private String sPechat;
	@JsonProperty("proshiv_id")
	private String proshiv_id;
	@JsonProperty("sProshiv")
	private String sProshiv;
	@JsonProperty("pyatka_id")
	private String pyatka_id;
	@JsonProperty("sPyatka")
	private String sPyatka;
	@JsonProperty("sled_id")
	private String sled_id;
	@JsonProperty("sSled")
	private String sSled;
	@JsonProperty("spoyler_id")
	private String spoyler_id;
	@JsonProperty("sSpoyler")
	private String sSpoyler;
	@JsonProperty("ashpalt_id")
	private String ashpalt_id;
	@JsonProperty("sAshpalt")
	private String sAshpalt;
	@JsonProperty("plastizol_id")
	private String plastizol_id;
	@JsonProperty("sPlastizol")
	private String sPlastizol;

	@JsonProperty("prodir")
	private Boolean prodir = false;

	@JsonProperty("difersize")
	private Boolean difersize = false;
	@JsonProperty("tert")
	private Boolean tert = false;
	@JsonProperty("frez")
	private Boolean frez = false;
	@JsonProperty("sample")
	private Boolean sample = false;
	@JsonProperty("createdat")
	private Date createdAt;
	
	public OutDoorOrderRowReq() {
		super();
	}

	public String getPlastizol_id() {
		return plastizol_id;
	}

	public void setPlastizol_id(String plastizol_id) {
		this.plastizol_id = plastizol_id;
	}

	public String getsPlastizol() {
		return sPlastizol;
	}

	public void setsPlastizol(String sPlastizol) {
		this.sPlastizol = sPlastizol;
	}

	/*
	 * OutDoorOrderRowReq(String id, String order_id, String attribute, Integer
	 * number, String barcode, String product_id, String sProduct, String size,
	 * String color_id, String liner_id, String rant_id, String shpalt_id, String
	 * sColor, String sLiner, String sRant, String sShpalt, String vstavka_id,
	 * String sVstavka, String gelenok_id, String sGelenok, String guba_id, String
	 * sGuba, String kabluk_id, String sKabluk, String matirovka_id, String
	 * sMatirovka, String pechat_id, String sPechat, String proshiv_id, String
	 * sProshiv, String pyatka_id, String sPyatka, String sled_id, String sSled,
	 * String spoyler_id, String sSpoyler, String ashpalt_id, String sAshpalt,
	 * Boolean prodir, Boolean difersize, Boolean tert, Boolean frez, Boolean
	 * sample, String plastizol_id, String sPlastizol
	 *
	 *repeated.current.sProduct
	 *+IF(repeated.current.sColor!="..."," "+repeated.current.sColor,"")
	 *+IF(repeated.current.sLiner!="..."," Подкл."+repeated.current.sLiner,"")
	 *+IF(repeated.current.sRant!="..."," Рант."+repeated.current.sRant,"")
	 *+IF(repeated.current.sShpalt!="..."," Шпал."+repeated.current.sShpalt,"")
	 *+IF(repeated.current.size!="0"," р."+repeated.current.size,"")
	 *+IF(repeated.current.sample==true,", Образец.","")
	 *
	 *IF(repeated.current.sVstavka!="...","Вст."+repeated.current.sVstavka,"")
	 *+IF(repeated.current.sAshpalt!="..."," "
	 *+IF(params.ppDivisionCode==appVars.tepCode,"Шпал.","Крас.")+repeated.current.sAshpalt,"")
	 *+IF(repeated.current.sSpoyler!="..."," "
	 *+IF(params.ppDivisionCode==appVars.tepCode,"Спойл.","М1.")+repeated.current.sSpoyler,"")
	 *+IF(repeated.current.sGuba!="..."," "
	 *+IF(params.ppDivisionCode==appVars.tepCode,"Губа.","М2.")+repeated.current.sGuba,"")
	 *+IF(repeated.current.sKabluk!="..."," "
	 *+IF(params.ppDivisionCode==appVars.tepCode,"Кабл.","М3.")+repeated.current.sKabluk,"")
	 *+IF(repeated.current.sGelenok!="..."," "+"Гел."+repeated.current.sGelenok,"")
	 *+IF(repeated.current.sSled!="..."," "+"След."+repeated.current.sSled,"")
	 *+IF(repeated.current.sPyatka!="..."," "+"Пятк."+repeated.current.sPyatka,"")
	 *+IF(repeated.current.tert==true," "+"Терт.","")
	 *+IF(repeated.current.sMatirovka!="..."," "+"Мат."+repeated.current.sMatirovka,"")
	 *+IF(repeated.current.sPechat!="..."," "+"Печ."+repeated.current.sPechat,"")
	 *+IF(repeated.current.sProshiv!="..."," "+"Прош."+repeated.current.sProshiv,"")
	 *+IF(repeated.current.prodir==true," "+"Продир.","")
	 *+IF(repeated.current.frez==true," "+"Фрез.","")
	 *+IF(repeated.current.difersize==true," "+"Полупара","")
	 *+IF(repeated.current.sPlastizol!="..."," "+"Пл."+repeated.current.sPlastizol,"")
	 *+IF(repeated.current.attribute!=""," "+"Доп."+repeated.current.attribute,"")
	 */
	
	public static OutDoorOrderRowReq rowToRowReq(OutDoorOrderRow b) {
		return new OutDoorOrderRowReq(b.getId(), b.getOutDoorOrder().getId(), b.getAttribute(), b.getNumber(),
				b.getBarcode(), b.getProduct().getId(), b.getProduct().getName(), b.getSize(), b.getColor().getId(),
				b.getColor().getName(), b.getLiner().getId(), b.getLiner().getName(), b.getRant().getId(),
				b.getRant().getName(), b.getShpalt().getId(), b.getShpalt().getName(), b.getVstavka().getId(),
				b.getVstavka().getName(), b.getGelenok().getId(), b.getGelenok().getName(), b.getGuba().getId(),
				b.getGuba().getName(), b.getKabluk().getId(), b.getKabluk().getName(), b.getMatirovka().getId(),
				b.getMatirovka().getName(), b.getPechat().getId(), b.getPechat().getName(), b.getProshiv().getId(),
				b.getProshiv().getName(), b.getPyatka().getId(), b.getPyatka().getName(), b.getSled().getId(),
				b.getSled().getName(), b.getSpoyler().getId(), b.getSpoyler().getName(), b.getAshpalt().getId(),
				b.getAshpalt().getName(), b.getProdir(), b.getDifersize(), b.getTert(), b.getFrez(), b.getSample(),
				b.getPlastizol().getId(), b.getPlastizol().getName(), b.getCreatedAt());
	}

	public static List<OutDoorOrderRowReq> rowToRowReq(List<OutDoorOrderRow> list) {
		return list.stream().map(b -> rowToRowReq(b)).collect(Collectors.toList());
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
