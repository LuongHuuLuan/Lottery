package model;

public class Province {
	private int idPro;
	private String codePro;
	private String name;

	public Province() {
	}

	public Province(int idPro, String codePro, String name) {
		this.idPro = idPro;
		this.codePro = codePro;
		this.name = name;
	}

	public int getIdPro() {
		return idPro;
	}

	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	public String getCodePro() {
		return codePro;
	}

	public void setCodePro(String codePro) {
		this.codePro = codePro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
