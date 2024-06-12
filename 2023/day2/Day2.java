import java.util.*;
import java.io.File;

public class Day2 {

  public static void main(String[] args) {

    System.out.println("Advent of Code 2023 - Day 2");
    HashMap<String, Integer> gameBag = new HashMap<>();
    gameBag.put("red", 12);
    gameBag.put("green", 13);
    gameBag.put("blue", 14);

    try {
      Scanner scanner = new Scanner(new File("gameInput.txt"));

      List<String> gameList = new ArrayList<String>();
      int part1Result = 0;

      while (scanner.hasNext()) {
        gameList.add(scanner.nextLine().split(":")[1]);
      }

      for (int i = 0; i < gameList.size(); i++) {
        if (isGameValid(gameBag, gameList.get(i)))
          part1Result += i + 1;
      }

      System.out.println("Part 1: " + part1Result);
      scanner.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  static Boolean isGameValid(HashMap<String, Integer> gameBag, String gameData) {
    String[] draws = gameData.replaceAll(";", ",").split(",");

    for (String it : draws) {
      try {
        String[] draw = it.trim().split(" ");
        int count = Integer.parseInt(draw[0]);
        String color = draw[1];
        if (count > gameBag.get(color))
          return false;
      } catch (Exception e) {
        System.out.println(it);
        System.out.println(e);
      }
    }

    return true;
  }
}
