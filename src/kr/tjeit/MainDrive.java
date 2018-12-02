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
//	0을 누르기 전까지는 반복적으로 메뉴를 출력	
		
		printMenu();
		
	}
	
	static void printMenu() {
		
		Scanner scan = new Scanner(System.in);
		int userInput = 0;
		
//		사용자가 0번을 눌러서 종료하기 전까지는 프로그램이 계속 유지.
		while(true) {
//			단순 메뉴 출력
			System.out.println("*****메뉴 목록*****");
			System.out.println("1. 전화번호 추가");
			System.out.println("2. 전화번호 목록 조회");
			System.out.println("0. 프로그램 종료");
			System.out.println("==============");
			System.out.print("번호를 입력하세요 : ");
			
//			선택지 입력
			userInput = scan.nextInt();
			
//			0을 입력 => 종료의사 표명 => while 탈출 => 자동 종료.
//			0을 안 눌렀다면? 프로그램이 계속 켜져있게 됨.
			if (userInput == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			else if (userInput == 1) {
//				전화번호 추가 메뉴 코드 작성
				addPhoneNum();
			}
			else if (userInput == 2) {
//				전화번호 목록 조회 메쏘드 콜.
				printPhoneNumList();
			}
			else {
				System.out.println("잘못된 입력입니다.");
			}
			
		}
	}
	
	
	
//	실제로 파일에 연락처 정보를 저장하는 메쏘드.
	static void addPhoneNum() {
		
		Scanner scanner = new Scanner(System.in);
//		정보를 입력받아서 텍스트파일에 추가
		System.out.print("이름을 입력해주세요 : ");
		String name = scanner.nextLine();
		
		System.out.print("이름을 입력해주세요 : ");
		String phoneNum = scanner.nextLine();
		
		 
		File f = new File("C:/temp/phonebook.csv");
		FileWriter fw;
		try {
			fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
//			bw를 이용해서 기록할 사항: 조경진, 01051123237, 2018-12-02 오후

//			이름, 연락처는 이미 입력 받은 상태.
//			등록일시만 자동으로 따내면 처리 완료.
			
//			Calendar 변수를 하나 만듦 => 현재시간 저장.
			Calendar cal = Calendar.getInstance();
			
//			2018-12-02 오후 12:25 양식을 미리 지정. (yyyy-MM-dd a h:mm)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a h:mm");
//			만들어둔 양식을 이용해 String으로 변환
			String datasStr = sdf.format(cal.getTime());

//			확인차 화면에 출력
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
//		사용자에게 추가로 입력받을 필요가 X.
//		파일을 열고 그 안에 들어있는 모든 내용을 출력.
//		파일을 읽는 FileReader, BufferedReader
		
		File file = new File("C:/temp/phonebook.csv");
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				
//				읽어온 line을 가지고 가공해서 Person 클래스로 추가.
//				peopleList 배열에 하나하나 대입

				String[] infos = line.split(",");
				
				Person p = new Person();
				p.setName(infos[0]);
				p.setPhoneNum(infos[1]);
				
//				infos[2] => 캘린더 변수로 변환
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
