package com.yg.apireact.model.outDoorOrderRow;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yg.apireact.model.outDoorOrder.OutDoorOrder;
import com.yg.apireact.model.product.Product;
import com.yg.apireact.model.color.Color;

@Entity
public class OutDoorOrderRow {
	/**
	 * Percent-encodes a string so it's suitable for use in a URL Path (not a query string / form encode, which uses + for spaces, etc)
	 */
	private static String replaceReservedCharacters(String encodeMe) {
	    if (encodeMe == null) {
	        return "";
	    }
	    String encoded = encodeMe.replace("%", "");
	    //encoded = encoded.replace(" ", "");
	    encoded = encoded.replace("!", "");
	    encoded = encoded.replace("#", "");
	    encoded = encoded.replace("$", "");
	    encoded = encoded.replace("&", "");
	    encoded = encoded.replace("'", "");
	    encoded = encoded.replace("(", "");
	    encoded = encoded.replace(")", "");
	    encoded = encoded.replace("*", "");
	    encoded = encoded.replace("+", "");
	    encoded = encoded.replace(",", "");
	    encoded = encoded.replace("/", "-");
	    encoded = encoded.replace(":", "");
	    encoded = encoded.replace(";", "");
	    encoded = encoded.replace("=", "");
	    encoded = encoded.replace("?", "");
	    encoded = encoded.replace("@", "");
	    encoded = encoded.replace("[", "");
	    encoded = encoded.replace("]", "");
	    return encoded;
	}
	public OutDoorOrderRow ( String product_name, String product_id, Long number) {
		super();
		this.number = number.intValue();
		this.product = new Product(product_id,product_name,"");
	}
	public OutDoorOrderRow (@NotNull String product_name, @NotNull String product_id, 
			String color_id, String color_name, 
			String liner_id,String liner_name,
			String rant_id,String rant_name, 
			@Nullable String shpalt_id, @Nullable String shpalt_name, 
			Long number) {
		super();
		this.number = number.intValue();
		this.product = new Product(product_id,product_name,"");
		this.color = new Color(color_id,color_name);
		this.liner = new Color(liner_id,liner_name);
		this.rant = new Color(rant_id,rant_name);
		
		if (StringUtils.isNumeric(shpalt_id) & StringUtils.isNotBlank(shpalt_name))
			this.shpalt = new Color(shpalt_id,shpalt_name);
		else
			this.shpalt = new Color("0","");
	}
	public OutDoorOrderRow (@NotNull String product_name, @NotNull String product_id,
			String color_id, String color_name, 
			String liner_id,String liner_name,
			String rant_id,String rant_name, 
			@Nullable String shpalt_id, @Nullable String shpalt_name, 
			@Nullable String attribute, 
			Long number) {
		super();
		this.number = number.intValue();
		this.product = new Product(product_id,product_name,"");
		this.color = new Color(color_id,color_name);
		this.liner = new Color(liner_id,liner_name);
		this.rant = new Color(rant_id,rant_name);
		
		if (StringUtils.isNumeric(shpalt_id) & StringUtils.isNotBlank(shpalt_name))
			this.shpalt = new Color(shpalt_id,shpalt_name);
		else
			this.shpalt = new Color("0","");
		
		if (StringUtils.isNotBlank(attribute))	this.attribute = replaceReservedCharacters(attribute);
	}
	public OutDoorOrderRow (@NotNull String product_name, @NotNull String product_id,
			String color_id, String color_name, 
			String liner_id,String liner_name,
			String rant_id,String rant_name, 
			@Nullable String shpalt_id, @Nullable String shpalt_name, 
			@Nullable String attribute, 
			@Nullable String size, 
			Long number) {
		super();
		this.number = number.intValue();
		this.product = new Product(product_id,product_name,"");
		this.color = new Color(color_id,color_name);
		this.liner = new Color(liner_id,liner_name);
		this.rant = new Color(rant_id,rant_name);
		
		if (StringUtils.isNumeric(shpalt_id) & StringUtils.isNotBlank(shpalt_name))
			this.shpalt = new Color(shpalt_id,shpalt_name);
		else
			this.shpalt = new Color("0");
		
		if (StringUtils.isNotBlank(attribute))	this.attribute = replaceReservedCharacters(attribute);
		if (StringUtils.isNotBlank(size))	this.size = size;
	}
	public OutDoorOrderRow (@NotNull String product_name, @NotNull String product_id,
			String color_id, String color_name, 
			String liner_id,String liner_name,
			String rant_id,String rant_name, 
			@Nullable String attribute, 
			Long number) {
		super();
		this.number = number.intValue();
		this.product = new Product(product_id,product_name,"");
		this.color = new Color(color_id,color_name);
		this.liner = new Color(liner_id,liner_name);
		this.rant = new Color(rant_id,rant_name);
		
		if (StringUtils.isNotBlank(attribute))	this.attribute = replaceReservedCharacters(attribute);
	}
	public OutDoorOrderRow(@NotNull String id, @NotNull OutDoorOrder outDoorOrder, String attribute,
			Integer number, String barcode, Product product, String size, Color color, Color liner, Color rant,
			Color shpalt, Color vstavka, Color gelenok, Color guba, Color kabluk, Color matirovka, Color pechat, Color proshiv, Color pyatka, Color sled,
			Color spoyler, Color ashpalt, Boolean prodir, Boolean difersize, Boolean tert,
			Boolean frez, Boolean sample, Color plastizol) {
		super();
		this.id = id;
		this.outDoorOrder = outDoorOrder;
		this.attribute = replaceReservedCharacters(attribute);
		this.number = number;
		this.barcode = barcode;
		this.product = product;
		this.size = size;
		this.color = color;
		this.liner = liner;
		this.rant = rant;
		this.shpalt = shpalt;
		this.vstavka = vstavka;
		this.gelenok = gelenok;
		this.guba = guba;
		this.kabluk = kabluk;
		this.matirovka = matirovka;
		this.pechat = pechat;
		this.proshiv = proshiv;
		this.pyatka = pyatka;
		this.sled = sled;
		this.spoyler = spoyler;
		this.ashpalt = ashpalt;
		this.prodir = prodir;
		this.difersize = difersize;
		this.tert = tert;
		this.frez = frez;
		this.sample = sample;
		this.plastizol = plastizol;
	}

	@Access(AccessType.PROPERTY)
	@Id
	@NotNull
	private String id;
	
	@NotNull
	@ManyToOne(optional = false)
	private OutDoorOrder outDoorOrder;

	private String attribute;	
	private Integer number;	
	private String barcode;
	
	@ManyToOne(optional = false)
	private Product product ;
	@JsonProperty("size")
	@ColumnDefault("0")
	private String size ;
	@JsonProperty("color_id")
	@ManyToOne(optional = false)
	@ColumnDefault("0")
	private Color color ;
	@JsonProperty("liner_id")
	@ManyToOne(optional = false)
	@ColumnDefault("0")
	private Color liner ;
	@JsonProperty("rant_id")
	@ManyToOne(optional = false)
	@ColumnDefault("0")
	private Color rant ;
	@JsonProperty("shpalt_id")
	@ManyToOne(optional = false)
	@ColumnDefault("0")
	private Color shpalt ;
	//***Атрибут
		@JsonProperty("vstavka")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color vstavka ;
		
		@JsonProperty("gelenok")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color gelenok ;
		
		@JsonProperty("guba")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color guba ;
		
		@JsonProperty("kabluk")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color kabluk ;
		
		@JsonProperty("matirovka")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color matirovka ;
		
		@JsonProperty("pechat")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color pechat ;
		
		@JsonProperty("proshiv")
		@ManyToOne(optional = false)
		@ColumnDefault("0")
		private Color proshiv ;
		
		@JsonProperty("pyatka")
		@ManyToOne(optional = false)
		private Color pyatka ;
		
		@JsonProperty("sled")
		@ManyToOne(optional = false)
		private Color sled ;
		
		@JsonProperty("spoyler")
		@ManyToOne(optional = false)
		private Color spoyler ;
		
		@JsonProperty("ashpalt")
		@ManyToOne(optional = false)
		private Color ashpalt ;
		
		@JsonProperty("prodir")
		private Boolean prodir=false;
		@JsonProperty("difersize")
		private Boolean difersize=false;
		@JsonProperty("tert")
		private Boolean tert=false;
		@JsonProperty("frez")
		private Boolean frez=false;
		@JsonProperty("sample")
		private Boolean sample=false;

		@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
		@Column(name = "dt", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		private Date dt = new Date();

		@JsonProperty("plastizol")
		@ManyToOne(optional = false)
		private Color plastizol ;
		
	public OutDoorOrderRow(String id, @NotNull OutDoorOrder outDoorOrder, 
			String attribute, Integer number, String barcode,
			String product_id, String size, String color_id, String liner_id, String rant_id, String shpalt_id,
			String vstavka_id, String gelenok_id, String guba_id, String kabluk_id, String matirovka_id, 
			String pechat_id, String proshiv_id, String pyatka_id, String sled_id, String spoyler_id,
			String ashpalt_id, 
			Boolean prodir, Boolean difersize, Boolean tert, Boolean frez, Boolean sample, String plastizol_id	) {
		super();
		this.id = id;
		this.outDoorOrder = outDoorOrder;

		this.attribute = replaceReservedCharacters(attribute);
		this.number = number;
		this.barcode = barcode;
		this.product = new Product(product_id,"","");
		this.size = size;
		this.color = new Color(color_id);
		this.liner = new Color(liner_id);
		this.rant = new Color(rant_id);
		this.shpalt = new Color(shpalt_id);
		this.vstavka = new Color(vstavka_id);
		this.gelenok = new Color(gelenok_id);
		this.guba = new Color(guba_id);
		this.kabluk = new Color(kabluk_id);
		this.matirovka = new Color(matirovka_id);

		this.pechat = new Color(pechat_id);
		this.proshiv = new Color(proshiv_id);
		this.pyatka = new Color(pyatka_id);
		this.sled = new Color(sled_id);
		this.spoyler = new Color(spoyler_id);
		this.ashpalt = new Color(ashpalt_id);
		this.plastizol = new Color(plastizol_id);
		this.prodir = prodir;
		this.difersize = difersize;
		this.tert = tert;
		this.frez = frez;
		this.sample = sample;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public OutDoorOrder getOutDoorOrder() {
		return outDoorOrder;
	}
	public void setOutDoorOrder(OutDoorOrder outDoorOrder) {
		this.outDoorOrder = outDoorOrder;
	}
	public String getAttribute() {
		return replaceReservedCharacters(attribute);
	}
	public void setAttribute(String attribute) {
		this.attribute = replaceReservedCharacters(attribute);
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getLiner() {
		return liner;
	}
	public void setLiner(Color liner) {
		this.liner = liner;
	}
	public Color getRant() {
		return rant;
	}
	public void setRant(Color rant) {
		this.rant = rant;
	}
	public Color getShpalt() {
		return shpalt;
	}
	public void setShpalt(Color shpalt) {
		this.shpalt = shpalt;
	}
	public Color getVstavka() {
		return vstavka;
	}

	public void setVstavka(Color vstavka) {
		this.vstavka = vstavka;
	}

	public Color getGelenok() {
		return gelenok;
	}

	public void setGelenok(Color gelenok) {
		this.gelenok = gelenok;
	}

	public Color getGuba() {
		return guba;
	}

	public void setGuba(Color guba) {
		this.guba = guba;
	}

	public Color getKabluk() {
		return kabluk;
	}

	public void setKabluk(Color kabluk) {
		this.kabluk = kabluk;
	}

	public Color getMatirovka() {
		return matirovka;
	}

	public void setMatirovka(Color matirovka) {
		this.matirovka = matirovka;
	}

	public Color getPechat() {
		return pechat;
	}

	public void setPechat(Color pechat) {
		this.pechat = pechat;
	}

	public Color getProshiv() {
		return proshiv;
	}

	public void setProshiv(Color proshiv) {
		this.proshiv = proshiv;
	}

	public Color getPyatka() {
		return pyatka;
	}

	public void setPyatka(Color pyatka) {
		this.pyatka = pyatka;
	}

	public Color getSled() {
		return sled;
	}

	public void setSled(Color sled) {
		this.sled = sled;
	}

	public Color getSpoyler() {
		return spoyler;
	}

	public void setSpoyler(Color spoyler) {
		this.spoyler = spoyler;
	}

	public Color getAshpalt() {
		return ashpalt;
	}

	public void setAshpalt(Color ashpalt) {
		this.ashpalt = ashpalt;
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
	public Color getPlastizol() {
		return plastizol;
	}
	public void setPlastizol(Color plastizol) {
		this.plastizol = plastizol;
	}
	
	public OutDoorOrderRow() {
		super();
	}

}
