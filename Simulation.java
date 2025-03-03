package assignment_2_part_2;

import java.util.ArrayList;
import java.util.Random;
public class Simulation {

    // Method to generate normally distributed random numbers and then add them to the  list
    public void generateNormalRandomNumbers(ArrayList<Double> list, final int size) {
        Random rand = new Random();  // Random object for generating the Gaussian values
        for (int i = 0; i < size; i++) {  // Loop to generate the 100,000 random numbers
            list.add(rand.nextGaussian());  // Generate Gaussian random numbers (mean=0, stddev=1)
        }
    }

    // Method to create bins and categorize the list of random numbers into specified bins
    public int[] makeBins(ArrayList<Double> list, final int numBins) {
        int[] bins = new int[numBins];  // Array to hold counts for each bin

        // Calculate minimum and maximum values only once to avoid redundant calls
        final double minVal = getMin(list);
        final double maxVal = getMax(list);
        final double range = maxVal - minVal;  // Calculate the range of values
        final double binSize = range / numBins;  // Calculate the size of each bin

        // Place each number into the appropriate bin
        for (Double value : list) {
            int binIndex = (int) ((value - minVal) / binSize);  // This is used tod etermine the bin index for each number
            
            // Ensure binIndex is within bounds (clamp to 0 and numBins - 1)
            binIndex = Math.min(Math.max(binIndex, 0), numBins - 1);
            bins[binIndex]++;  // Increment the count for the selected bin
        }
        return bins;  // Return the array containing the counts for each bin
    }

    // Method to find the minimum value in a list of Doubles
    public double getMin(ArrayList<Double> list) {
        double min = Double.MAX_VALUE;  // Initialize min to the largest possible value
        for (Double num : list) {  // Loop through each number to find the minimum
            if (num < min) {  // Update min if a smaller number is found
                min = num;
            }
        }
        return min;  // Return the minimum value
    }

    // Method to find the maximum value in a list of Doubles
    public double getMax(ArrayList<Double> list) {
        double max = -Double.MAX_VALUE;  // Initialize max to the smallest possible value
        for (Double num : list) {  // Loop through each number to find the maximum
            if (num > max) {  // Update max if a larger number is found
                max = num;
            }
        }
        return max;  // Return the maximum value
    }
}
