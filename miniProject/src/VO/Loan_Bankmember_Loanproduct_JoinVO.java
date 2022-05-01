package VO;

public class Loan_Bankmember_Loanproduct_JoinVO {
	private int LOANID;
	private Long AMOUNT;
	private int BANKMEMBERID;
	private String ACCOUNT;
	private int MEMBERID;
	private int SCORE;
	private Long BALANCE;
	private String PLUSACCOUNT;
	private int LOANPRODUCTID;
	private Long MAXAMOUNT;
	private int BANKID;
	private String PRODUCTNAME;
	private String CONDITION;
	public int getLOANID() {
		return LOANID;
	}
	public void setLOANID(int lOANID) {
		LOANID = lOANID;
	}
	public Long getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Long aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public int getBANKMEMBERID() {
		return BANKMEMBERID;
	}
	public void setBANKMEMBERID(int bANKMEMBERID) {
		BANKMEMBERID = bANKMEMBERID;
	}
	public String getACCOUNT() {
		return ACCOUNT;
	}
	public void setACCOUNT(String aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	public int getMEMBERID() {
		return MEMBERID;
	}
	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}
	public int getSCORE() {
		return SCORE;
	}
	public void setSCORE(int sCORE) {
		SCORE = sCORE;
	}
	public Long getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(Long bALANCE) {
		BALANCE = bALANCE;
	}
	public String getPLUSACCOUNT() {
		return PLUSACCOUNT;
	}
	public void setPLUSACCOUNT(String pLUSACCOUNT) {
		PLUSACCOUNT = pLUSACCOUNT;
	}
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
		return "Loan_Bankmember_Loanproduct_JoinVO [LOANID=" + LOANID + ", AMOUNT=" + AMOUNT + ", BANKMEMBERID="
				+ BANKMEMBERID + ", ACCOUNT=" + ACCOUNT + ", MEMBERID=" + MEMBERID + ", SCORE=" + SCORE + ", BALANCE="
				+ BALANCE + ", PLUSACCOUNT=" + PLUSACCOUNT + ", LOANPRODUCTID=" + LOANPRODUCTID + ", MAXAMOUNT="
				+ MAXAMOUNT + ", BANKID=" + BANKID + ", PRODUCTNAME=" + PRODUCTNAME + ", CONDITION=" + CONDITION + "]";
	}
	
	
}
