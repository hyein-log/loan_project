package VO;

public class LoanProductVO {
	private int LOANPRODUCTID;
	private Long MAXAMOUNT;
	private int BANKID;
	private String PRODUCTNAME;
	private String CONDITION;
	public int getLOANPRODUCTID() {
		return LOANPRODUCTID;
	}
	public void setLOANPRODUCTID(int lOANPRODUCTID) {
		LOANPRODUCTID = lOANPRODUCTID;
	}
	public Long getMAXAMOUNT() {
		return MAXAMOUNT;
	}
	public void setMAXAMOUNT(Long mAXAMOUNT) {
		MAXAMOUNT = mAXAMOUNT;
	}
	public int getBANKID() {
		return BANKID;
	}
	public void setBANKID(int bANKID) {
		BANKID = bANKID;
	}
	public String getPRODUCTNAME() {
		return PRODUCTNAME;
	}
	public void setPRODUCTNAME(String pRODUCTNAME) {
		PRODUCTNAME = pRODUCTNAME;
	}
	public String getCONDITION() {
		return CONDITION;
	}
	public void setCONDITION(String cONDITION) {
		CONDITION = cONDITION;
	}
	@Override
	public String toString() {
		return "LoanProductVO [LOANPRODUCTID=" + LOANPRODUCTID + ", MAXAMOUNT=" + MAXAMOUNT + ", BANKID=" + BANKID
				+ ", PRODUCTNAME=" + PRODUCTNAME + ", CONDITION=" + CONDITION + "]";
	}
	
}
