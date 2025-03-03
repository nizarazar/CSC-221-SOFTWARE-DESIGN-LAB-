package assignment_2_part_2;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class MonteCarlo {
    
    // Main method where execution of the code begins
    public static void main(String[] args) {
        
        // ArrayList to store the generated random numbers
        ArrayList<Double> randomNumbers = new ArrayList<>();

        // Creating an instance of Simulation to generate random numbers and categorize them into bins
        Simulation simulation = new Simulation();

        // Generate 100,000 normally distributed random numbers and store them in randomNumbers
        simulation.generateNormalRandomNumbers(randomNumbers, 100000);
        
        // Create 11 bins to classify the random numbers
        int[] bins = simulation.makeBins(randomNumbers, 11);
        
        // Displaying each bin count in the console
        System.out.println("Bin counts:");
        for (int bin : bins) {
            System.out.println(bin);
        }

        // File path to save the bin counts to "Gauss.txt"
        final String filePath = "Gauss.txt";
        
        // Writing the bin counts to "Gauss.txt" file
        try (FileWriter writer = new FileWriter(Paths.get(filePath).toString())) {
            for (int bin : bins) {
                writer.write(bin + "\n");  // Writing each bin count followed by a newline
            }
        } catch (IOException e) {
            e.printStackTrace();  // Prints any file I/O errors to the terminal
        }

        // Display percentages of numbers within the 3 standard deviations
        System.out.println("Percentages:");

        // Creating an instance of Metrics to verify the distribution
        Metrics metrics = new Metrics();

        // Calculating and printing the percentage of numbers within 1, 2, and 3 standard deviations
        System.out.println(metrics.verifyDistribution(randomNumbers, 0.0, 1.0, 1.0)); // 1 std dev
        System.out.println(metrics.verifyDistribution(randomNumbers, 0.0, 1.0, 2.0)); // 2 std devs
        System.out.println(metrics.verifyDistribution(randomNumbers, 0.0, 1.0, 3.0)); // 3 std devs

        // Add histogram visualization
        System.out.println("Displaying histogram...");

        // Create and display the histogram
        int numBins = 11; // Number of bins for the histogram
        Histogram histogram = new Histogram(simulation, randomNumbers, numBins);

        JFrame visuals = new JFrame();
        visuals.setTitle("CSc 221 Histogram");
        visuals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visuals.add(histogram);
        visuals.setSize(1200, 800);
        visuals.setVisible(true);
    }
}
