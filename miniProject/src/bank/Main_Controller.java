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
			System.out.println("<C>.�����_ȭ��");
			System.out.println("<M>.������_ȭ��");
			System.out.println("<B>.���������_ȭ��");
			System.out.println("<E>.����");
			System.out.print("�۾��� �����ϼ��� >>");
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
