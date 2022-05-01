package VO;

public class GradeVO {
	private int gradeid;
	private int grade;
	public int getGradeid() {
		return gradeid;
	}
	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "GradeVO [gradeid=" + gradeid + ", grade=" + grade + "]";
	}
}
