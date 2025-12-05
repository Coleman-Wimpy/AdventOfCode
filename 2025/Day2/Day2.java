package Day2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    static Long invalidIdTotalValue = 0L;
    static Long invalidIdTotalValueV2 = 0L;

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("Day2.txt");
            String input = fileReader.readAllAsString().trim();

            List<String> idStrings = List.of(input.split(","));

            for (String idRange : idStrings) {
                checkForInvalidIds(idRange);
                checkForInvalidIdsV2(idRange);
            }

            System.out.println(
                "Invalid ID Total Value: " + invalidIdTotalValue
            );

            System.out.println(
                "Invalid ID Total Value V2: " + invalidIdTotalValueV2
            );

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkForInvalidIds(String idRange) {
        String[] idRanges = idRange.split("-");
        Long start = Long.parseLong(idRanges[0]);
        Long end = Long.parseLong(idRanges[1]);

        for (Long i = start; i <= end; i++) {
            String id = i.toString();
            Long midPoint = id.length() / 2L;
            String left = id.substring(0, midPoint.intValue());
            String right = id.substring(midPoint.intValue());

            if (left.equals(right)) invalidIdTotalValue += i;
        }
    }

    private static void checkForInvalidIdsV2(String idRange) {
        String[] idRanges = idRange.split("-");
        Long start = Long.parseLong(idRanges[0]);
        Long end = Long.parseLong(idRanges[1]);

        for (Long i = start; i <= end; i++) {
            String id = i.toString();
            boolean isInvalidId = checkArrayHasAllDuplicates(id);
            if (isInvalidId) invalidIdTotalValueV2 += i;
        }
    }

    private static boolean checkArrayHasAllDuplicates(String id) {
        // System.out.println("Checking ID: " + id);
        for (int j = 1; j < id.length() + 1; j++) {
            boolean isInvalidId = true;
            String digit = "";
            ArrayList<String> digits = new ArrayList<>();

            for (char c : id.toCharArray()) {
                digit += c;
                if (digit.length() == j) {
                    digits.add(digit);
                    digit = "";
                }
            }

            if (!digit.isEmpty()) digits.add(digit);

            if (digits.size() < 2) return false;

            String firstDigit = digits.get(0);
            //System.out.println("Digit Array: " + digits);
            for (String currentDigit : digits) {
                if (!firstDigit.equals(currentDigit)) {
                    isInvalidId = false;
                    break;
                }
            }
            if (isInvalidId) {
                System.out.println("ID is invalid: " + id);
                return true;
            }
        }
        return false;
    }
}
