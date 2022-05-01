package VO;

import java.sql.Date;

public class LoanApplyVO {
	private int LOANAPPLYNUM;
	private int LOANID;
	private Date STARTDATE;
	private Date ENDDATE;
	private int PROLONG;
	private int NOWSCORE;
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
	@Override
	public String toString() {
		return "LoanApplyVO [LOANAPPLYNUM=" + LOANAPPLYNUM + ", LOANID=" + LOANID + ", STARTDATE=" + STARTDATE
				+ ", ENDDATE=" + ENDDATE + ", PROLONG=" + PROLONG + ", NOWSCORE=" + NOWSCORE + "]";
	}
	
}
