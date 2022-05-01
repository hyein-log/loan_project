package bank;

import java.util.Scanner;

import CONTROLLER.Banker_Controller;
import CONTROLLER.Manager_Controller;
import CONTROLLER.Customer_Controller;

public class Main_Controller {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		boolean flag = true;
		while (flag) {
			System.out.println("================");
			System.out.println("[ Main_Display ]");
			System.out.println("================");
			System.out.println("<C>.사용자_화면");
			System.out.println("<M>.관리자_화면");
			System.out.println("<B>.은행관계자_화면");
			System.out.println("<E>.종료");
			System.out.print("작업을 선택하세요 >>");
			String select_no = scan.nextLine();
			switch (select_no) {
			case "C":case "c":
				Customer_Controller.public_Mode();
				break;
			case "M":case "m":
				Manager_Controller.Manager_Mode();
				break;
			case "B":case "b":
				Banker_Controller.Banker_Mode();
				break;
			default :
				flag = false;
				break;
			}
		}
		System.out.println("=======");
		System.out.println("[ END ]");
		System.out.println("=======");
		
	}
}
