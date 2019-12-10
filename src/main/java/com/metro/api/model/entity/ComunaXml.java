package com.metro.api.model.entity;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class ComunaXml {

	private long id;
	private Integer tMin;
	private Integer tMax;
	private String viento;
	private String simboloTiempo;
	private String dia;
	private String defAtmofera;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer gettMin() {
		return tMin;
	}

	public void settMin(Integer tMin) {
		this.tMin = tMin;
	}

	public Integer gettMax() {
		return tMax;
	}

	public void settMax(Integer tMax) {
		this.tMax = tMax;
	}

	public String getViento() {
		return viento;
	}

	public void setViento(String viento) {
		this.viento = viento;
	}

	public String getSimboloTiempo() {
		return simboloTiempo;
	}

	public void setSimboloTiempo(String simboloTiempo) {
		this.simboloTiempo = simboloTiempo;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getDefAtmofera() {
		return defAtmofera;
	}

	public void setDefAtmofera(String defAtmofera) {
		this.defAtmofera = defAtmofera;
	}

}
