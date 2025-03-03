package assignment_2_part_2;

import java.util.ArrayList;
public class Metrics {

    // Method to verify the distribution of values within a specified number of standard deviations from the mean
    public double verifyDistribution(ArrayList<Double> list, final double mean, final double stddev, final double numStdDevs) {
        // Calculate the lower and upper bounds for the specified range
        final double lowerBound = mean - (stddev * numStdDevs);
        final double upperBound = mean + (stddev * numStdDevs);
        
        int count = 0;  // Initialize count to track numbers within the specified range

        // Enhanced for-loop to iterate through each value in the list
        for (Double num : list) {
            // Check if the number falls within the bounds and increment count if it does
            if (num >= lowerBound && num <= upperBound) {
                count++;
            }
        }
        
        // Return the percentage of values within the specified range
        return (double) count / list.size() * 100;
    }
}