package kr.tjeit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import kr.tjeit.datas.Person;

public class MainDrive {
	
	static Person[] peopleList = new Person[100];
	
	
	public static void main(String[] args) {
//	0�� ������ �������� �ݺ������� �޴��� ���	
		
		printMenu();
		
	}
	
	static void printMenu() {
		
		Scanner scan = new Scanner(System.in);
		int userInput = 0;
		
//		����ڰ� 0���� ������ �����ϱ� �������� ���α׷��� ��� ����.
		while(true) {
//			�ܼ� �޴� ���
			System.out.println("*****�޴� ���*****");
			System.out.println("1. ��ȭ��ȣ �߰�");
			System.out.println("2. ��ȭ��ȣ ��� ��ȸ");
			System.out.println("0. ���α׷� ����");
			System.out.println("==============");
			System.out.print("��ȣ�� �Է��ϼ��� : ");
			
//			������ �Է�
			userInput = scan.nextInt();
			
//			0�� �Է� => �����ǻ� ǥ�� => while Ż�� => �ڵ� ����.
//			0�� �� �����ٸ�? ���α׷��� ��� �����ְ� ��.
			if (userInput == 0) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
			else if (userInput == 1) {
//				��ȭ��ȣ �߰� �޴� �ڵ� �ۼ�
				addPhoneNum();
			}
			else if (userInput == 2) {
//				��ȭ��ȣ ��� ��ȸ �޽�� ��.
				printPhoneNumList();
			}
			else {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
			
		}
	}
	
	
	
//	������ ���Ͽ� ����ó ������ �����ϴ� �޽��.
	static void addPhoneNum() {
		
		Scanner scanner = new Scanner(System.in);
//		������ �Է¹޾Ƽ� �ؽ�Ʈ���Ͽ� �߰�
		System.out.print("�̸��� �Է����ּ��� : ");
		String name = scanner.nextLine();
		
		System.out.print("�̸��� �Է����ּ��� : ");
		String phoneNum = scanner.nextLine();
		
		 
		File f = new File("C:/temp/phonebook.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
//			bw�� �̿��ؼ� ����� ����: ������, 01051123237, 2018-12-02 ����

//			�̸�, ����ó�� �̹� �Է� ���� ����.
//			����Ͻø� �ڵ����� ������ ó�� �Ϸ�.
			
//			Calendar ������ �ϳ� ���� => ����ð� ����.
			Calendar cal = Calendar.getInstance();
			
//			2018-12-02 ���� 12:25 ����� �̸� ����. (yyyy-MM-dd a h:mm)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a h:mm");
//			������ ����� �̿��� String���� ��ȯ
			String datasStr = sdf.format(cal.getTime());

//			Ȯ���� ȭ�鿡 ���
			System.out.println(datasStr);
			
			bw.write(name+","+phoneNum+","+datasStr);
			bw.newLine();
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
	
	
	static void printPhoneNumList() {
//		����ڿ��� �߰��� �Է¹��� �ʿ䰡 X.
//		������ ���� �� �ȿ� ����ִ� ��� ������ ���.
//		������ �д� FileReader, BufferedReader
		
		File file = new File("C:/temp/phonebook.csv");
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				
//				�о�� line�� ������ �����ؼ� Person Ŭ������ �߰�.
//				peopleList �迭�� �ϳ��ϳ� ����

				String[] infos = line.split(",");
				
				Person p = new Person();
				p.setName(infos[0]);
				p.setPhoneNum(infos[1]);
				
//				infos[2] => Ķ���� ������ ��ȯ
				SimpleDateFormat parseSdf = new SimpleDateFormat("yyyy-mm-dd a h:mm");
				Calendar c = Calendar.getInstance();
				
				c.setTime(parseSdf.parse(infos[2]));
				
				p.setCreatedAt(c);
				
				p.printMyInfo();
			}
			
			br.close();
			fr.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
