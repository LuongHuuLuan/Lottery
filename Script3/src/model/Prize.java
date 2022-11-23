package model;

public class Prize {
	private int idPri;
	private String namePri;
	private double prize;

	public Prize(int idPri, String namePri, double prize) {
		super();
		this.idPri = idPri;
		this.namePri = namePri;
		this.prize = prize;
	}

	public int getIdPri() {
		return idPri;
	}

	public void setIdPri(int idPri) {
		this.idPri = idPri;
	}

	public String getNamePri() {
		return namePri;
	}

	public void setNamePri(String namePri) {
		this.namePri = namePri;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

}
