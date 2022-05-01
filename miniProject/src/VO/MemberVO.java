package VO;

import java.sql.Date;

public class MemberVO {
	private int MEMBERID;
	private String MEMBERNAME;
	private int PASSWORD;
	private Long ANNUALSALES;
	private int EMPNUM;
	private Date ESTDATE;
	public int getMEMBERID() {
		return MEMBERID;
	}
	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}
	public String getMEMBERNAME() {
		return MEMBERNAME;
	}
	public void setMEMBERNAME(String mEMBERNAME) {
		MEMBERNAME = mEMBERNAME;
	}
	public int getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(int pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public Long getANNUALSALES() {
		return ANNUALSALES;
	}
	public void setANNUALSALES(Long aNNUALSALES) {
		ANNUALSALES = aNNUALSALES;
	}
	public int getEMPNUM() {
		return EMPNUM;
	}
	public void setEMPNUM(int eMPNUM) {
		EMPNUM = eMPNUM;
	}
	public Date getESTDATE() {
		return ESTDATE;
	}
	public void setESTDATE(Date eSTDATE) {
		ESTDATE = eSTDATE;
	}
	@Override
	public String toString() {
		return "MemberVO [MEMBERID=" + MEMBERID + ", MEMBERNAME=" + MEMBERNAME + ", PASSWORD=" + PASSWORD
				+ ", ANNUALSALES=" + ANNUALSALES + ", EMPNUM=" + EMPNUM + ", ESTDATE=" + ESTDATE + "]";
	}
	
}
