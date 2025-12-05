import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {

    private static int part1Pass = 0;
    private static int part2Pass = 0;
    private static boolean startAt0 = false;

    public static void main(String[] args) {
        System.out.println("Advent of Code 2025: Day 1");

        ArrayList<String> input = new ArrayList<>();
        try (FileReader fileReader = new FileReader(new File("Day1.txt"))) {
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    input.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int startingPos = 50;
        for (String instruction : input) {
            startingPos = doRotation(instruction, startingPos);
            if (startingPos == 0) {
                part1Pass++;
            }
        }

        int currentPosition = 50;
        for (String instruction : input) {
            currentPosition = doRotationV2(instruction, currentPosition);
        }

        System.out.println("Part 1 Password: " + part1Pass);
        System.out.println("Part 2 Password: " + part2Pass);
    }

    private static int doRotation(String instruction, int currentPosition) {
        char rotationDir = instruction.charAt(0);
        int rotationAmount = Integer.parseInt(instruction.substring(1));

        if (rotationDir == 'R') return (currentPosition + rotationAmount) % 100;
        else return (currentPosition - rotationAmount) % 100;
    }

    private static int doRotationV2(String instruction, int currentPosition) {
        char rotationDir = instruction.charAt(0);
        int rotationAmount = Integer.parseInt(instruction.substring(1));
        int totalMoves = 0;
        if (rotationDir == 'R') {
            totalMoves = currentPosition + rotationAmount;
        } else {
            totalMoves = currentPosition - rotationAmount;
        }
        part2Pass += (int) Math.floor(Math.abs(totalMoves) / 100);
        if (totalMoves < 0 && !startAt0) part2Pass++;
        if (totalMoves == 0) part2Pass++;

        int newPos = totalMoves % 100;
        if (newPos == 0) startAt0 = true;
        else startAt0 = false;
        if (newPos < 0) newPos += 100;

        return newPos;
    }
}
