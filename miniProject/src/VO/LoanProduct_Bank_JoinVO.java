package VO;

public class LoanProduct_Bank_JoinVO {
	private int LOANPRODUCTID;
	private Long MAXAMOUNT;
	private int BANKID;
	private String PRODUCTNAME;
	private String CONDITION;
	private int bankid;
	private String bankname;
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
	public int getBankid() {
		return bankid;
	}
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	@Override
	public String toString() {
		return "LoanProduct_Bank_JoinVO [LOANPRODUCTID=" + LOANPRODUCTID + ", MAXAMOUNT=" + MAXAMOUNT + ", BANKID="
				+ BANKID + ", PRODUCTNAME=" + PRODUCTNAME + ", CONDITION=" + CONDITION + ", bankid=" + bankid
				+ ", bankname=" + bankname + "]";
	}
		
		
	
}
