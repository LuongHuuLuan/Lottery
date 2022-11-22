package model;

public class Prize {
	private int idPri;
	private String name;
	private double prize;

	public Prize() {
	}

	public Prize(int idPri, String name, double prize) {
		this.idPri = idPri;
		this.name = name;
		this.prize = prize;
	}

	public int getIdPri() {
		return idPri;
	}

	public void setIdPro(int idPri) {
		this.idPri = idPri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public double getPrize() {
		return prize;
	}

}
