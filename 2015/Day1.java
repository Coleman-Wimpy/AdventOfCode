import java.io.File;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) {
		System.out.println("Advent of Code 2015 Day 1");
		try {
			Scanner scanner = new Scanner(new File("floorString.txt"));
			String floorString = scanner.nextLine();
			System.out.println("Floor Number: " + getFloor(floorString));
			System.out.println("Position of first basement level char: " + getCharPositionOfBasement(floorString));
      scanner.close();
		}catch (Exception e) {
			System.out.println("Error");
		}
	}


	public static int getFloor(String floorNumberString) {
		int floorNumber = 0;

		for (int i = 0; i < floorNumberString.length(); i++) {
			char c = floorNumberString.charAt(i);
			if (c == '(') {
				floorNumber+= 1;
			}
			else if (c == ')') {
				floorNumber-= 1;
			}
		}

		return floorNumber;
	}

	public static int getCharPositionOfBasement(String floorNumberString) {
		int floorNumber = 0;

		for (int i = 0; i < floorNumberString.length(); i++) {
			char c = floorNumberString.charAt(i);
			if (c == '(') {
				floorNumber += 1;
			}
			else if (c == ')') {
				floorNumber -= 1;
			}
			
			if (floorNumber == -1) {
				return i+1;
			}
		}

		return 0;
	}
}

