import java.sql.*;
import java.util.Scanner;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import javax.print.attribute.standard.Finishings;

public class Test {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		Integer mainnum = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.101:3308/madang", "jshan",
					"wltjd7851");
			Statement stmt = con.createStatement();
			/*
			 * ResultSet rs = stmt.executeQuery("SELECT * FROM Book"); while (rs.next())
			 * System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
			 * rs.getString(3)); con.close();
			 */
		} catch (Exception e) {

			System.out.println(e);
			System.exit(0);
		}
		do {
			System.out.println("---------------------------------------------");
			System.out.println("1. 고객 관리");
			System.out.println("2. 도서 관리");
			System.out.println("3. 종료");
			System.out.println("---------------------------------------------");

			mainnum = scanner.nextInt();

			if (mainnum == 1) {
				Customer();
			} else if (mainnum == 2) {
				Book();

			} else if (mainnum == 3) {
				scanner.close();
				System.exit(0);
			} else {
				System.out.println("1~3의 정수를 입력해주세요.(현재 입력한 숫자 : " + mainnum + " )");
			}
		} while (mainnum != 3);

	}

	public static void Customer() {
		Integer Custmnum = 0;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("---------------------------------------------");
			System.out.println("1. 고객 추가");
			System.out.println("2. 고객 삭제");
			System.out.println("3. 고객 검색");
			System.out.println("4. 고객 수정");
			System.out.println("5. 돌아가기");
			System.out.println("---------------------------------------------");

			Custmnum = scan.nextInt();
			if (Custmnum == 1) {

			} else if (Custmnum == 2) {

			} else if (Custmnum == 3) {

			} else if (Custmnum == 4) {

			} else if (Custmnum == 5) {
				scan.close();
				break;
			} else {
				System.out.println("1~5의 정수를 입력해주세요.(현재 입력한 숫자 : " + Custmnum + " )");
			}
		} while (Custmnum != 5);

	}

	public static void Book() {
		Integer booknum = 0;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("---------------------------------------------");
			System.out.println("1. 도서 추가");
			System.out.println("2. 도서 삭제");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 조회");
			System.out.println("5. 도서 수정");
			System.out.println("6. 돌아가기");
			System.out.println("---------------------------------------------");

			booknum = scan.nextInt();
			scan.nextLine();
			if (booknum == 1) {
				System.out.println("---------------------------------------------");
				System.out.println("책의 이름을 입력해주세요");
				String bname = scan.nextLine();
				System.out.println("책의 출판사를 입력해주세요");
				String publisher = scan.nextLine();
				System.out.println("책의 가격을 입력해주세요");
				Integer bprice = scan.nextInt();
				Bookinsert(bname, publisher, bprice);
				System.out.println("---------------------------------------------");
			} else if (booknum == 2) {

			} else if (booknum == 3) {

			} else if (booknum == 4) {
				Booklook();
			} else if (booknum == 5) {

			} else if (booknum == 6) {
				scan.close();
				break;
			} else {
				System.out.println("1~5의 정수를 입력해주세요.(현재 입력한 숫자 : " + booknum + " )");
			}
		} while (booknum != 5);

	}

	public static void Bookinsert(String bname, String publisher, Integer bprice) {
		int insertCount = 0;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.101:3308/madang", "jshan",
					"wltjd7851");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs =stmt.executeQuery("select * from Book");
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			String sql = "INSERT INTO Book(bookid, bookname, publisher, price) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rowCount+1);
			ps.setString(2, bname.toString());
			ps.setString(3, publisher.toString());
			ps.setInt(4, bprice);
			insertCount = ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
			System.exit(0);
		} 
	}

	public static void Booklook() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.101:3308/madang", "jshan",
					"wltjd7851");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Book");
			while (rs.next())
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			con.close();
		} catch (Exception e) {

			System.out.println(e);
			System.exit(0);
		}
	}

}