package model;

public class Lottery {
	private String idLot;
	private Date date;
	private Source source;
	private Province province;

	public Lottery() {
	}

	public Lottery(String idLot, Date date, Source source, Province province) {
		this.idLot = idLot;
		this.date = date;
		this.source = source;
		this.province = province;
	}

	public String getIdLot() {
		return idLot;
	}

	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}
