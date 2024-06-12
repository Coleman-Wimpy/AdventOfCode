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
      int part2Result = 0;

      while (scanner.hasNext()) {
        gameList.add(scanner.nextLine().split(":")[1]);
      }

      for (int i = 0; i < gameList.size(); i++) {
        Map.Entry<Boolean, Integer> resultPair = isGameValid(gameBag, gameList.get(i));
        if (resultPair.getKey())
          part1Result += i + 1;

        part2Result += resultPair.getValue();
      }

      System.out.println("Part 1: " + part1Result);
      System.out.println("Part 2: " + part2Result);
      scanner.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  static Map.Entry<Boolean, Integer> isGameValid(HashMap<String, Integer> gameBag, String gameData) {
    String[] draws = gameData.replaceAll(";", ",").split(",");
    HashMap<String, Integer> minBagContents = new HashMap<>();
    minBagContents.put("red", 0);
    minBagContents.put("blue", 0);
    minBagContents.put("green", 0);
    int gamePower = 1;
    boolean gamePossible = true;

    for (String it : draws) {
      try {
        String[] draw = it.trim().split(" ");
        int count = Integer.parseInt(draw[0]);
        String color = draw[1];
        if (count > gameBag.get(color))
          gamePossible = false;

        if (minBagContents.get(color) < count)
          minBagContents.replace(color, count);
      } catch (Exception e) {
        System.out.println(it);
        System.out.println(e);
      }
    }

    for (int number : minBagContents.values()) {
      gamePower *= number;
    }

    return new AbstractMap.SimpleEntry<Boolean, Integer>(gamePossible, gamePower);
  }
}
