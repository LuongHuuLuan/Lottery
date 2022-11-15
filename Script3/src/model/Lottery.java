package model;

public class Lottery {
	private int idLot;
	private int idDate;
	private int idSour;
	private int idPro;

	public Lottery(int idLot, int idDate, int idSour, int idPro) {
		super();
		this.idLot = idLot;
		this.idDate = idDate;
		this.idSour = idSour;
		this.idPro = idPro;
	}

	public int getIdLot() {
		return idLot;
	}

	public void setIdLot(int idLot) {
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

	@Override
	public String toString() {
		return "Lottery [idLot=" + idLot + ", idDate=" + idDate + ", ifSour=" + idSour + ", idPro=" + idPro + "]";
	}

}
