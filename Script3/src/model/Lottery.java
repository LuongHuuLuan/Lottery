package model;

public class Lottery {
	private String idLot;
	private int idDate;
	private int idSour;
	private int idPro;

	public Lottery(String idLot, int idDate, int idSour, int idPro) {
		super();
		this.idLot = idLot;
		this.idDate = idDate;
		this.idSour = idSour;
		this.idPro = idPro;
	}

	public String getIdLot() {
		return idLot;
	}

	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}

	public int getIdDate() {
		return idDate;
	}

	public void setIdDate(int idDate) {
		this.idDate = idDate;
	}

	public int getIdSour() {
		return idSour;
	}

	public void setIdSour(int idSour) {
		this.idSour = idSour;
	}

	public int getIdPro() {
		return idPro;
	}

	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

}
