package benford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//declaring benford class
public class benford {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\nizar\\Desktop\\FALL 2024\\csc221\\projects\\Benford\\Dataset.txt";
        Map<Integer, Integer> digitCount = new HashMap<>();
        int totalCount = 0;

        // used to initialize the digit count map
        for (int i = 1; i <= 9; i++) {
            digitCount.put(i, 0);
        }

        // reading the data from the txt file and count the first significant digits for each line of numbers
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    char firstChar = line.charAt(0);
                    if (Character.isDigit(firstChar)) {
                        int digit = Character.getNumericValue(firstChar);
                        if (digit >= 1 && digit <= 9) {
                            digitCount.put(digit, digitCount.get(digit) + 1);
                            totalCount++;
                        }
                    }
                }
            }
            //used this catch error because I had many problem getting the right data that i was expecting
            //no need for this
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // calculate and print the percentages the first sig number showed
        for (int i = 1; i <= 9; i++) {
            double percentage = (digitCount.get(i) / (double) totalCount) * 100;
            int roundedPercentage = (int) Math.round(percentage);
            System.out.printf("%d (%6.3f%%) : %s%n", i, percentage, "*".repeat(roundedPercentage));
        }
    }
}
