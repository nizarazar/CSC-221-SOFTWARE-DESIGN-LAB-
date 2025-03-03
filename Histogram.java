// Nizar Azar
// Assignment 2 part 2
// Modified code to use public Histogram(Simulation s)

package assignment_2_part_2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Histogram extends JPanel {
    private static final long serialVersionUID = 1L;

    // Margins for spacing around the histogram
    final int TOP_MARGIN = 20;
    final int BOTTOM_MARGIN = 20;
    final int LEFT_MARGIN = 20;
    final int RIGHT_MARGIN = 20;

    // Instance variables for bins, min value, range, bin size, and number of bins
    private int[] bins; // Array to hold bin count
    private double min; // Minimum value in the dataset
    private double range; // Range of the dataset from max to min
    private double binSize; // Size of each bin
    private final int numBins; // Total number of bins

    // Default constructor with modified parameter name
    public Histogram(Simulation s) {
        setBackground(Color.WHITE); // Set background color to white
        this.numBins = 11; // Default number of bins

        // Generate random numbers and calculate bins
        ArrayList<Double> randomNumbers = new ArrayList<>();
        s.generateNormalRandomNumbers(randomNumbers, 100000); // Generate 100,000 random numbers
        bins = s.makeBins(randomNumbers, numBins); // Categorize numbers into bins
        min = s.getMin(randomNumbers); // Find the minimum value in the dataset
        range = s.getMax(randomNumbers) - min; // Calculate the range of values
        binSize = range / numBins; // Calculate the size of each bin
    }

    // Overloaded constructor with modified parameter name
    public Histogram(Simulation s, ArrayList<Double> randomNumbers, int numBins) {
        setBackground(Color.WHITE); // Set background color to white
        this.numBins = numBins; // Set the number of bins

        // Generate bins and calculate range
        bins = s.makeBins(randomNumbers, numBins); // Categorize numbers into bins
        min = s.getMin(randomNumbers); // Find the minimum value in the dataset
        range = s.getMax(randomNumbers) - min; // Calculate the range of values
        binSize = range / numBins; // Calculate the size of each bin
    }

    // Method to draw the entire histogram
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the paintComponent method for the superclass
        drawXAxis(g); // Draw the x-axis
        drawYAxis(g); // Draw the y-axis
        drawBins(g); // Draw the bins
        drawXLabels(g); // Draw the labels along the x-axis
    }

    // Method to draw the x-axis
    private void drawXAxis(Graphics g) {
        int x1 = LEFT_MARGIN; // Start point of the x-axis
        int y1 = getHeight() - BOTTOM_MARGIN; // Y-coordinate of the x-axis
        int x2 = getWidth() - RIGHT_MARGIN; // End point of the x-axis
        g.drawLine(x1, y1, x2, y1); // Draw the x-axis line
        g.setColor(Color.BLACK); // Set the text color to black
    }

    // Method to draw the y-axis
    private void drawYAxis(Graphics g) {
        int x1 = LEFT_MARGIN; // X-coordinate of the y-axis
        int y1 = getHeight() - BOTTOM_MARGIN; // Start point of the y-axis
        int y2 = TOP_MARGIN; // End point of the y-axis
        g.drawLine(x1, y1, x1, y2); // Draw the y-axis line
        g.setColor(Color.BLACK); // Set the text color to black
    }

    // Method to draw the bins of the histogram
    private void drawBins(Graphics g) {
        g.setColor(Color.GRAY); // Set the color of the bins to gray
        int binWidth = (getWidth() - LEFT_MARGIN - RIGHT_MARGIN) / numBins; // Calculate the width of each bin

        for (int i = 0; i < bins.length; i++) { // Loop through each bin
            int binHeight = scaleY(bins[i], (double) getHeight() - TOP_MARGIN - BOTTOM_MARGIN); // Scale the bin height
            int x = LEFT_MARGIN + i * binWidth; // Calculate the x-coordinate of the bin
            int y = getHeight() - BOTTOM_MARGIN - binHeight; // Calculate the y-coordinate of the bin
            g.fillRect(x, y, binWidth, binHeight); // Draw the bin
            g.setColor(Color.BLACK); // Set the border color to black
            g.drawRect(x, y, binWidth, binHeight); // Draw a border around the bin
            g.setColor(Color.BLUE); // Set the text color to blue
            String binValue = String.valueOf(bins[i]); // Get the bin value as a string
            int textX = x + binWidth / 2 - g.getFontMetrics().stringWidth(binValue) / 2; // Center the text horizontally
            int textY = y - 5; // Position the text slightly above the bin
            g.drawString(binValue, textX, textY); // Draw the bin value
            g.setColor(Color.GRAY); // Reset the color to gray for the next bin
        }
    }

    // Method to draw the labels along the x-axis
    private void drawXLabels(Graphics g) {
        g.setColor(Color.BLACK); // Set the text color to black
        DecimalFormat formatter = new DecimalFormat("#.##"); // Formatter for the labels
        int binWidth = (getWidth() - LEFT_MARGIN - RIGHT_MARGIN) / numBins; // Calculate the spacing between labels
        int x = LEFT_MARGIN; // Start position for the first label
        int y = getHeight() - BOTTOM_MARGIN + 20; // Position of the labels

        for (int i = 0; i <= numBins; i++) { // Loop through the number of bins
            double labelValue = min + i * binSize; // Calculate the label value
            g.drawString(formatter.format(labelValue), x - 10, y); // Draw the label
            x += binWidth; // Move to the next label position
        }
    }

    // Method to scale a value to fit the chart height
    private int scaleY(int value, double chartHeight) {
        int maxBinValue = 0; // Initialize the maximum bin value

        for (int bin : bins) { // Loop through the bins to find the maximum value
            maxBinValue = Math.max(maxBinValue, bin);
        }

        return (int) ((value / (double) maxBinValue) * chartHeight); // Scale the value relative to the maximum bin value
    }
}
