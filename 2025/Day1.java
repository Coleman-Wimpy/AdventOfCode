import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {

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

        int currentPosition = 50;
        int zeroCount = 0;
        for (String instruction : input) {
            currentPosition = doRotation(instruction, currentPosition);
            if (currentPosition == 0) zeroCount++;
        }

        System.out.println("Zero count: " + zeroCount);
    }

    private static int doRotation(String instruction, int currentPosition) {
        char rotationDir = instruction.charAt(0);
        int rotationAmount = Integer.parseInt(instruction.substring(1));

        if (rotationDir == 'R') return (currentPosition + rotationAmount) % 100;
        else return (currentPosition - (rotationAmount + 100)) % 100;
    }
}
