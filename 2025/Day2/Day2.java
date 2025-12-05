package Day2;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Day2 {

    static Long invalidIdTotalValue = 0L;

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("Day2.txt");
            String input = fileReader.readAllAsString().trim();

            List<String> idStrings = List.of(input.split(","));

            for (String idRange : idStrings) {
                checkForInvalidIds(idRange);
            }

            System.out.println(
                "Invalid ID Total Value: " + invalidIdTotalValue
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
}
