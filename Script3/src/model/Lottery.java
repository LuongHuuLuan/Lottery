package model;

public class Lottery {
	private int idLot;
	private String nkIdLot;
	private Date date;
	private Source source;
	private Province province;

	public Lottery() {
	}

	public Lottery(String nkIdLot, Date date, Source source, Province province) {
		this.nkIdLot = nkIdLot;
		this.date = date;
		this.source = source;
		this.province = province;
	}

	public Lottery(int idLot, String nkIdLot, Date date, Source source, Province province) {
		this.idLot = idLot;
		this.nkIdLot = nkIdLot;
		this.date = date;
		this.source = source;
		this.province = province;
	}

	public int getIdLot() {
		return idLot;
	}

	public void setIdLot(int idLot) {
		this.idLot = idLot;
	}

	public String getNkIdLot() {
		return nkIdLot;
	}

	public void setNkIdLot(String nkIdLot) {
		this.nkIdLot = nkIdLot;
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
