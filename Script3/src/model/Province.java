package model;

public class Province {
	private int idPro;
	private String name;

	public Province() {
	}

	public Province(int idPro, String name) {
		this.idPro = idPro;
		this.name = name;
	}

	public int getIdPro() {
		return idPro;
	}

	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
