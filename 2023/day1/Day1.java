import java.io.File;
import java.util.*;

public class Day1 {

  public static void main(String[] args) {

    System.out.println("Advent of Code 2023 - Day 1");
    int resultPart1 = 0;
    int resultPart2 = 0;

    try {
      Scanner scanner = new Scanner(new File("calibration.txt"));
      if (!scanner.hasNextLine()) {
        System.out.println("File is empty.");
      }

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        if (line.isEmpty())
          break;

        int calibrationData = getCalibrationData(line);
        if (calibrationData == -1) {
          System.out.println("No Numbers in line: " + line);
        } else {
          resultPart1 += calibrationData;
        }

        int calibrationDataPart2 = getCalibrationDataPart2(line);
        if (calibrationDataPart2 == -1) {
          System.out.println("No Numbers in line: " + line);
        } else {
          resultPart2 += calibrationDataPart2;
        }
      }

      System.out.println("Result: " + resultPart1);
      System.out.println("Result: " + resultPart2);
      scanner.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static int getCalibrationData(String input) {
    char firstNumber = ' ';
    char lastNumber = ' ';

    for (char ch : input.toCharArray()) {
      if (Character.isDigit(ch) && firstNumber == ' ') {
        firstNumber = ch;
        lastNumber = ch;
      } else if (Character.isDigit(ch)) {
        lastNumber = ch;
      }
    }

    if (firstNumber == ' ')
      return -1;

    String num = "" + firstNumber + lastNumber;
    return Integer.valueOf(num);
  }

  private static int getCalibrationDataPart2(String input) {

    HashMap<String, Integer> nu berMap = new HashMap<String, Integer>();
    HashMap<Integer, String> wordLocationMap = new HashMap<Integer, String>();

    numberMap.put("one", 1);
    numberMap.put("two", 2);
    numberMap.put("three", 3);
    numberMap.put("four", 4);
    numberMap.put("five", 5);
    numberMap.put("six", 6);
    numberMap.put("seven", 7);
    numberMap.put("eight", 8);
    numberMap.put("nine", 9);

    for (Map.Entry<String, Integer> entry : numberMap.entrySet()) {
      if (input.contains(entry.getKey())) {
        wordLocationMap.put(input.indexOf(entry.getKey()), entry.getKey());
        wordLocationMap.put(input.lastIndexOf(entry.getKey()), entry.getKey());
      }
    }

    ArrayList<Integer> sortedValues = new ArrayList<Integer>(wordLocationMap.keySet());
    Collections.sort(sortedValues);

    for (int index : sortedValues) {
      String wordKey = wordLocationMap.get(index);
      input = input.replaceFirst(wordKey, numberMap.get(wordKey) + "");
    }

    return getCalibrationData(input);
  }
}
