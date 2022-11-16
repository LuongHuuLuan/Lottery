package model;

public class Result {
	private int idLot;
	private int idPri;
	private String result;

	public Result(int idLot, int idPri, String result) {
		super();
		this.idLot = idLot;
		this.idPri = idPri;
		this.result = result;
	}

	public int getIdLot() {
		return idLot;
	}

	public void setIdLot(int idLot) {
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

	@Override
	public String toString() {
		return "Result [idLot=" + idLot + ", idPri=" + idPri + ", result=" + result + "]";
	}

}
