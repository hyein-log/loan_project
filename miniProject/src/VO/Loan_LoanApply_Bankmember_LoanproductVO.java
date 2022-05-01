package VO;

import java.sql.Date;

public class Loan_LoanApply_Bankmember_LoanproductVO {
	private Long AMOUNT;
	private int LOANAPPLYNUM;
	private int LOANID;
	private Date STARTDATE;
	private Date ENDDATE;
	private int PROLONG;
	private int NOWSCORE;
	private int LOANPRODUCTID;
	private Long MAXAMOUNT;
	private int BANKID;
	private String PRODUCTNAME;
	private String CONDITION;
	private int BANKMEMBERID;
	private String ACCOUNT;
	private int MEMBERID;
	private int SCORE;
	private Long BALANCE;
	private String PLUSACCOUNT;
	public Long getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Long aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public int getLOANAPPLYNUM() {
		return LOANAPPLYNUM;
	}
	public void setLOANAPPLYNUM(int lOANAPPLYNUM) {
		LOANAPPLYNUM = lOANAPPLYNUM;
	}
	public int getLOANID() {
		return LOANID;
	}
	public void setLOANID(int lOANID) {
		LOANID = lOANID;
	}
	public Date getSTARTDATE() {
		return STARTDATE;
	}
	public void setSTARTDATE(Date sTARTDATE) {
		STARTDATE = sTARTDATE;
	}
	public Date getENDDATE() {
		return ENDDATE;
	}
	public void setENDDATE(Date eNDDATE) {
		ENDDATE = eNDDATE;
	}
	public int getPROLONG() {
		return PROLONG;
	}
	public void setPROLONG(int pROLONG) {
		PROLONG = pROLONG;
	}
	public int getNOWSCORE() {
		return NOWSCORE;
	}
	public void setNOWSCORE(int nOWSCORE) {
		NOWSCORE = nOWSCORE;
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
	@Override
	public String toString() {
		return "Loan_LoanApply_Bankmember_LoanproductVO [AMOUNT=" + AMOUNT + ", LOANAPPLYNUM=" + LOANAPPLYNUM
				+ ", LOANID=" + LOANID + ", STARTDATE=" + STARTDATE + ", ENDDATE=" + ENDDATE + ", PROLONG=" + PROLONG
				+ ", NOWSCORE=" + NOWSCORE + ", LOANPRODUCTID=" + LOANPRODUCTID + ", MAXAMOUNT=" + MAXAMOUNT
				+ ", BANKID=" + BANKID + ", PRODUCTNAME=" + PRODUCTNAME + ", CONDITION=" + CONDITION + ", BANKMEMBERID="
				+ BANKMEMBERID + ", ACCOUNT=" + ACCOUNT + ", MEMBERID=" + MEMBERID + ", SCORE=" + SCORE + ", BALANCE="
				+ BALANCE + ", PLUSACCOUNT=" + PLUSACCOUNT + ", getAMOUNT()=" + getAMOUNT() + ", getLOANAPPLYNUM()="
				+ getLOANAPPLYNUM() + ", getLOANID()=" + getLOANID() + ", getSTARTDATE()=" + getSTARTDATE()
				+ ", getENDDATE()=" + getENDDATE() + ", getPROLONG()=" + getPROLONG() + ", getNOWSCORE()="
				+ getNOWSCORE() + ", getLOANPRODUCTID()=" + getLOANPRODUCTID() + ", getMAXAMOUNT()=" + getMAXAMOUNT()
				+ ", getBANKID()=" + getBANKID() + ", getPRODUCTNAME()=" + getPRODUCTNAME() + ", getCONDITION()="
				+ getCONDITION() + ", getBANKMEMBERID()=" + getBANKMEMBERID() + ", getACCOUNT()=" + getACCOUNT()
				+ ", getMEMBERID()=" + getMEMBERID() + ", getSCORE()=" + getSCORE() + ", getBALANCE()=" + getBALANCE()
				+ ", getPLUSACCOUNT()=" + getPLUSACCOUNT() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
