package ro.usv.rf.utils;

import java.util.Arrays;
/*
 * Autor:Dragoi Andrei Marius
 * grupa: 3142A
 */
public class DistanceMatrix {
    private double[][] matDistEuclid; // Matrix to store Euclidean distances
    private double[][] matDistCityBlock; // Matrix to store City-Block (Manhattan) distances
    private double[][] matDistChebyshev; // Matrix to store Chebyshev distances

    // Enum to represent the choice of distance metric
    public enum DistanceMetric {
        EUCLIDEAN,
        CITY_BLOCK,
        CHEBYSHEV
    }

    // Constructor that initializes all distance matrices based on the provided patterns
    public DistanceMatrix(double[][] patterns) {
        int n = patterns.length;
        this.matDistEuclid = new double[n][n];
        this.matDistCityBlock = new double[n][n];
        this.matDistChebyshev = new double[n][n];
        initializeDistanceMatrices(patterns);
    }

    // New constructor to initialize with a specified metric
    public DistanceMatrix(double[][] patterns, DistanceMetric metric) {
        int n = patterns.length;
        switch (metric) {
            case EUCLIDEAN:
                this.matDistEuclid = new double[n][n];
                initializeDistanceMatrix(patterns, metric);
                break;
            case CITY_BLOCK:
                this.matDistCityBlock = new double[n][n];
                initializeDistanceMatrix(patterns, metric);
                break;
            case CHEBYSHEV:
                this.matDistChebyshev = new double[n][n];
                initializeDistanceMatrix(patterns, metric);
                break;
        }
    }


    // Helper method to initialize a specific distance matrix based on the chosen metric
    private void initializeDistanceMatrixMetric(double[][] patterns, DistanceMetric metric) {
        int n = patterns.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = 0;
                switch (metric) {
                    case EUCLIDEAN:
                        distance = DistanceUtils.distEuclid(patterns[i], patterns[j]);
                        this.matDistEuclid[i][j] = this.matDistEuclid[j][i] = distance;
                        break;
                    case CITY_BLOCK:
                        distance = DistanceUtils.distCityBlock(patterns[i], patterns[j]);
                        this.matDistCityBlock[i][j] = this.matDistCityBlock[j][i] = distance;
                        break;
                    case CHEBYSHEV:
                        distance = DistanceUtils.distChebyshev(patterns[i], patterns[j]);
                        this.matDistChebyshev[i][j] = this.matDistChebyshev[j][i] = distance;
                        break;
                }
            }
        }
    }

    // Helper method to initialize all distance matrices for the original constructor
    private void initializeDistanceMatrices(double[][] patterns) {
        int n = patterns.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                this.matDistEuclid[i][j] = this.matDistEuclid[j][i] = DistanceUtils.distEuclid(patterns[i], patterns[j]);
                this.matDistCityBlock[i][j] = this.matDistCityBlock[j][i] = DistanceUtils.distCityBlock(patterns[i], patterns[j]);
                this.matDistChebyshev[i][j] = this.matDistChebyshev[j][i] = DistanceUtils.distChebyshev(patterns[i], patterns[j]);
            }
        }
    }

    // Helper method to initialize a specific distance matrix based on the chosen metric
    private void initializeDistanceMatrix(double[][] patterns, DistanceMetric metric) {
        int n = patterns.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = 0;
                switch (metric) {
                    case EUCLIDEAN:
                        distance = DistanceUtils.distEuclid(patterns[i], patterns[j]);
                        this.matDistEuclid[i][j] = this.matDistEuclid[j][i] = distance;
                        break;
                    case CITY_BLOCK:
                        distance = DistanceUtils.distCityBlock(patterns[i], patterns[j]);
                        this.matDistCityBlock[i][j] = this.matDistCityBlock[j][i] = distance;
                        break;
                    case CHEBYSHEV:
                        distance = DistanceUtils.distChebyshev(patterns[i], patterns[j]);
                        this.matDistChebyshev[i][j] = this.matDistChebyshev[j][i] = distance;
                        break;
                }
            }
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double[][] matDist = this.matDistEuclid; // Default to Euclidean if not specified

        // Determine which matrix is initialized to display
        if (matDist == null) {
            if (this.matDistCityBlock != null) {
                matDist = this.matDistCityBlock;
            } else if (this.matDistChebyshev != null) {
                matDist = this.matDistChebyshev;
            }
        }

        // Check if any matrix is initialized
        if (matDist != null) {
            for (double[] row : matDist) {
                for (double dist : row) {
                    sb.append(String.format("%.3f ", dist));
                }
                sb.deleteCharAt(sb.length() - 1); // Remove the last space
                sb.append("\n");
            }
        } else {
            sb.append("No distance matrix is initialized.");
        }

        return sb.toString();
    }
    public double[][] neighbors(int i) {
        int n = matDistEuclid.length; // Assuming the matrix is square and all distances are calculated
        double[][] results = new double[2][n];
        Double[][] sortingHelper = new Double[n][2]; // To store distances and their original indices for sorting

        // Initialize with distances and indices
        for (int j = 0; j < n; j++) {
            sortingHelper[j][0] = (double)j; // Store the index
            sortingHelper[j][1] = matDistEuclid[i][j]; // Store the distance
        }

        // Sort the helper array by distances
        Arrays.sort(sortingHelper, (a, b) -> Double.compare(a[1], b[1]));

        // Populate the result array
        for (int j = 0; j < n; j++) {
            results[0][j] = sortingHelper[j][0]; // Indices of neighbors in sorted order
            results[1][j] = sortingHelper[j][1]; // Sorted distances
        }

        return results;
    }
    public void printDistanceMatrix(DistanceMetric metric) {
        double[][] matDist;
        switch (metric) {
            case EUCLIDEAN:
                matDist = this.matDistEuclid;
                System.out.println("Euclidean Distance Matrix:");
                break;
            case CITY_BLOCK:
                matDist = this.matDistCityBlock;
                System.out.println("City-Block (Manhattan) Distance Matrix:");
                break;
            case CHEBYSHEV:
                matDist = this.matDistChebyshev;
                System.out.println("Chebyshev Distance Matrix:");
                break;
            default:
                System.out.println("Unknown metric. Cannot print matrix.");
                return;
        }
        printMatrix(matDist);
    }

    private void printMatrix(double[][] mat) {
        for (double[] row : mat) {
            for (double dist : row) {
                System.out.printf("%.3f ", dist);
            }
            System.out.println();
        }
    }



    // Add toString and any other necessary methods here
}
