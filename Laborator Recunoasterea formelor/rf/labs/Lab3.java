package ro.usv.rf.labs;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import ro.usv.rf.utils.*;
/*
 * Autor:Dragoi Andrei Marius
 * grupa: 3142A
 */
public class Lab3 {
    public static void main(String[] args) {
        double[][] patternSet = FileUtils1.readMatrixFromFileStream("inLab3.txt");
        int numberOfPatterns = patternSet.length;
        int numberOfFeatures = patternSet[0].length;

        double [] firstPattern = patternSet[0];
        System.out.println("\neuclidDistance");
        for(int i=0; i<numberOfPatterns; i++)
        {
            double euclidDistance = DistanceUtils.distEuclid(firstPattern,patternSet[i]);

            System.out.println("Distance from first pattern to pattern "+ i+ " is "+ euclidDistance);

        }
        System.out.println("\ndistCityBlock");
        for(int i=0; i<numberOfPatterns; i++)
        {

            double distCityBlock =DistanceUtils.distCityBlock(firstPattern,patternSet[i]);

            System.out.println("Distance from first pattern to pattern "+ i + " is "+ distCityBlock);

        }
        System.out.println("\ndistChebyshev");
        for(int i=0; i<numberOfPatterns; i++)
        {
            double distChebyshev =DistanceUtils.distChebyshev(firstPattern,patternSet[i]);
            System.out.println("Distance from first pattern to pattern "+ i + " is "+ distChebyshev);

        }
        // Initialize the DistanceMatrix with the pattern set
        DistanceMatrix distanceMatrix = new DistanceMatrix(patternSet);
        // Print Euclidean distance matrix
        distanceMatrix.printDistanceMatrix(DistanceMatrix.DistanceMetric.EUCLIDEAN);

        // If you want to print the City-Block and Chebyshev matrices as well
        distanceMatrix.printDistanceMatrix(DistanceMatrix.DistanceMetric.CITY_BLOCK);
        distanceMatrix.printDistanceMatrix(DistanceMatrix.DistanceMetric.CHEBYSHEV);



        // Assuming you want to find and print neighbors of the first pattern
        int patternIndex = 0; // Change this to test with different patterns
        double[][] neighbors = distanceMatrix.neighbors(patternIndex);

        // Print the neighbors and their distances
        System.out.println("\nNeighbors and their distances for pattern " + patternIndex + ":");
        System.out.println("Indices of neighbors (in ascending order of distances):");
        for (int i = 0; i < numberOfPatterns; i++) {
            System.out.print((int) neighbors[0][i] + " ");
        }
        System.out.println("\nDistances to neighbors (in ascending order):");
        for (int i = 0; i < numberOfPatterns; i++) {
            System.out.print(neighbors[1][i] + " ");
        }

    }
}
