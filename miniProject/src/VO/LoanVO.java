package VO;

public class LoanVO {
	private int LOANID;
	private int BANKMEMBERID;
	private int LOANPRODUCTID;
	private Long AMOUNT;
	public int getLOANID() {
		return LOANID;
	}
	public void setLOANID(int lOANID) {
		LOANID = lOANID;
	}
	public int getBANKMEMBERID() {
		return BANKMEMBERID;
	}
	public void setBANKMEMBERID(int bANKMEMBERID) {
		BANKMEMBERID = bANKMEMBERID;
	}
	public int getLOANPRODUCTID() {
		return LOANPRODUCTID;
	}
	public void setLOANPRODUCTID(int lOANPRODUCTID) {
		LOANPRODUCTID = lOANPRODUCTID;
	}
	public Long getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Long aMOUNT) {
		AMOUNT = aMOUNT;
	}
	@Override
	public String toString() {
		return "LoanVO [LOANID=" + LOANID + ", BANKMEMBERID=" + BANKMEMBERID + ", LOANPRODUCTID=" + LOANPRODUCTID
				+ ", AMOUNT=" + AMOUNT + "]";
	}
	
}
