package model;

public class Result {
	private int idRe;
	private Lottery lottery;
	private Prize prize;
	private String result;

	public Result() {

	}

	public Result(Lottery lottery, Prize prize, String result) {
		this.lottery = lottery;
		this.prize = prize;
		this.result = result;
	}

	public Result(int idRe, Lottery lottery, Prize prize, String result) {
		this.idRe = idRe;
		this.lottery = lottery;
		this.prize = prize;
		this.result = result;
	}

	public int getIdRe() {
		return idRe;
	}

	public void setIdRe(int idRe) {
		this.idRe = idRe;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
