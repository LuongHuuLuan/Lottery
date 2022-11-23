package model;

public class Result {
	private String idLot;
	private int idPri;
	private String result;

	public Result(String idLot, int idPri, String result) {
		super();
		this.idLot = idLot;
		this.idPri = idPri;
		this.result = result;
	}

	public String getIdLot() {
		return idLot;
	}

	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}

	public int getIdPri() {
		return idPri;
	}

	public void setIdPri(int idPri) {
		this.idPri = idPri;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
