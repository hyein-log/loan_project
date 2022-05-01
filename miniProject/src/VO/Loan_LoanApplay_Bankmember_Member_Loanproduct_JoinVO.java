package VO;

import java.sql.Date;

public class Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO {
	private Long BALANCE;
	private int LOANPRODUCTID;
	private int BANKID;
	private Long AMOUNT;
	private int SCORE;
	private int MEMBERID;
	private String ACCOUNT;
	private int BANKMEMBERID;
	private String PLUSACCOUNT;
	private int LOANID;
	private String PRODUCTNAME;
	private Long MAXAMOUNT;
	private int LOANAPPLYNUM;
	private Date STARTDATE;
	private int PROLONG;
	private int NOWSCORE;
	private Date ENDDATE;
	private int PASSWORD;
	private String MEMBERNAME;
	private int EMPNUM;
	private Long ANNUALSALES;
	private Date ESTDATE;
	public Long getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(Long bALANCE) {
		BALANCE = bALANCE;
	}
	public int getLOANPRODUCTID() {
		return LOANPRODUCTID;
	}
	public void setLOANPRODUCTID(int lOANPRODUCTID) {
		LOANPRODUCTID = lOANPRODUCTID;
	}
	public int getBANKID() {
		return BANKID;
	}
	public void setBANKID(int bANKID) {
		BANKID = bANKID;
	}
	public Long getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Long aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public int getSCORE() {
		return SCORE;
	}
	public void setSCORE(int sCORE) {
		SCORE = sCORE;
	}
	public int getMEMBERID() {
		return MEMBERID;
	}
	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}
	public String getACCOUNT() {
		return ACCOUNT;
	}
	public void setACCOUNT(String aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	public int getBANKMEMBERID() {
		return BANKMEMBERID;
	}
	public void setBANKMEMBERID(int bANKMEMBERID) {
		BANKMEMBERID = bANKMEMBERID;
	}
	public String getPLUSACCOUNT() {
		return PLUSACCOUNT;
	}
	public void setPLUSACCOUNT(String pLUSACCOUNT) {
		PLUSACCOUNT = pLUSACCOUNT;
	}
	public int getLOANID() {
		return LOANID;
	}
	public void setLOANID(int lOANID) {
		LOANID = lOANID;
	}
	public String getPRODUCTNAME() {
		return PRODUCTNAME;
	}
	public void setPRODUCTNAME(String pRODUCTNAME) {
		PRODUCTNAME = pRODUCTNAME;
	}
	public Long getMAXAMOUNT() {
		return MAXAMOUNT;
	}
	public void setMAXAMOUNT(Long mAXAMOUNT) {
		MAXAMOUNT = mAXAMOUNT;
	}
	public int getLOANAPPLYNUM() {
		return LOANAPPLYNUM;
	}
	public void setLOANAPPLYNUM(int lOANAPPLYNUM) {
		LOANAPPLYNUM = lOANAPPLYNUM;
	}
	public Date getSTARTDATE() {
		return STARTDATE;
	}
	public void setSTARTDATE(Date sTARTDATE) {
		STARTDATE = sTARTDATE;
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
	public Date getENDDATE() {
		return ENDDATE;
	}
	public void setENDDATE(Date eNDDATE) {
		ENDDATE = eNDDATE;
	}
	public int getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(int pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getMEMBERNAME() {
		return MEMBERNAME;
	}
	public void setMEMBERNAME(String mEMBERNAME) {
		MEMBERNAME = mEMBERNAME;
	}
	public int getEMPNUM() {
		return EMPNUM;
	}
	public void setEMPNUM(int eMPNUM) {
		EMPNUM = eMPNUM;
	}
	public Long getANNUALSALES() {
		return ANNUALSALES;
	}
	public void setANNUALSALES(Long aNNUALSALES) {
		ANNUALSALES = aNNUALSALES;
	}
	public Date getESTDATE() {
		return ESTDATE;
	}
	public void setESTDATE(Date eSTDATE) {
		ESTDATE = eSTDATE;
	}
	@Override
	public String toString() {
		return "Loan_LoanApplay_Bankmember_Member_Loanproduct_JoinVO [BALANCE=" + BALANCE + ", LOANPRODUCTID="
				+ LOANPRODUCTID + ", BANKID=" + BANKID + ", AMOUNT=" + AMOUNT + ", SCORE=" + SCORE + ", MEMBERID="
				+ MEMBERID + ", ACCOUNT=" + ACCOUNT + ", BANKMEMBERID=" + BANKMEMBERID + ", PLUSACCOUNT=" + PLUSACCOUNT
				+ ", LOANID=" + LOANID + ", PRODUCTNAME=" + PRODUCTNAME + ", MAXAMOUNT=" + MAXAMOUNT + ", LOANAPPLYNUM="
				+ LOANAPPLYNUM + ", STARTDATE=" + STARTDATE + ", PROLONG=" + PROLONG + ", NOWSCORE=" + NOWSCORE
				+ ", ENDDATE=" + ENDDATE + ", PASSWORD=" + PASSWORD + ", MEMBERNAME=" + MEMBERNAME + ", EMPNUM="
				+ EMPNUM + ", ANNUALSALES=" + ANNUALSALES + ", ESTDATE=" + ESTDATE + "]";
	}
	
}
