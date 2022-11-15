package model;

public class Prize {
	private int idPri;
	private String namePri;
	private int prize;

	public Prize(int idPri, String namePri, int prize) {
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

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

}
