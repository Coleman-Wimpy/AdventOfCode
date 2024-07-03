import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Day3 {

  public static void main(String[] args) {

    try {
      System.out.println("Advent of Code: Day 3");

      Scanner scanner = new Scanner(new File("partNumber.txt"));

      List<char[]> inputMatrix = new ArrayList<char[]>();

      while (scanner.hasNextLine()) {
        char[] lineCharArray = scanner.nextLine().toCharArray();
        inputMatrix.add(lineCharArray);
      }

      System.out.println(getPartNumberPart1(inputMatrix));

      scanner.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  static int getPartNumberPart1(List<char[]> inputMatrix) {
    int result = 0;
    List<Character> numberBuffer = new ArrayList<Character>();
    boolean isValidNumber = false;
    int xMax = inputMatrix.size();
    int yMax = inputMatrix.get(0).length;

    for (int x = 0; x < inputMatrix.size(); x++) {
      char[] charArray = inputMatrix.get(x);
      for (int y = 0; y < charArray.length; y++) {
        char character = charArray[y];
        if (!Character.isDigit(character) && !numberBuffer.isEmpty() && isValidNumber) {
          String numberString = "";
          for(char number : numberBuffer) {
            numberString += number;
          }
          result += Integer.parseInt(numberString);
          isValidNumber = false;
          numberBuffer.clear();
        } else if (Character.isDigit(character)) {
          numberBuffer.add(character);
          if (checkSymbolNeighbors(inputMatrix, x, y, xMax, yMax))
            isValidNumber = true;
        } else {
          numberBuffer.clear();
        }
      }
    }

    return result;
  }

  static boolean checkSymbolNeighbors(List<char[]> inputMatrix, int x, int y, int xMax, int yMax) {

    int startPosX = (x - 1 < 0) ? x : x - 1;
    int startPosY = (y - 1 < 0) ? y : y - 1;
    int endPosX = (x + 1 > xMax - 1) ? x : x + 1;
    int endPosY = (y + 1 > yMax - 1) ? y : y + 1;

    for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
      for (int colNum = startPosY; colNum <= endPosY; colNum++) {
        char character = inputMatrix.get(rowNum)[colNum];
        if (!Character.isLetterOrDigit(character) && character != '.')
          return true;
      }
    }
    return false;
  }
}
