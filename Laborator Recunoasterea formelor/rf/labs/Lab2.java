package ro.usv.rf.labs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import ro.usv.rf.utils.DataUtils;
import ro.usv.rf.utils.FileUtils1;
import ro.usv.rf.utils.Pattern;
import ro.usv.rf.utils.StatisticsUtils;

/*
 * Autor:Dragoi Andrei Marius
 * grupa: 3142A
 */

public class Lab2 {


	public static void main(String[] args) {
		double[][] patternSet = FileUtils1.readMatrixFromFileStream("inLab2.txt");
		System.out.println("Matric patternSet:");
		DataUtils.printMatrix(patternSet);
		int numberOfPatterns = patternSet.length;
		int numberOfFeatures = patternSet[0].length;

		for (int j=0; j<numberOfFeatures; j++)
		{
			double[] feature = new double[numberOfPatterns];
			for (int i=0; i<numberOfPatterns; i++)
			{
				feature[i] = patternSet[i][j];
			}
			System.out.println("Feature average is:" + StatisticsUtils.calculateFeatureAverage(feature));
		}

		Map<Pattern, Double> patternsMap = StatisticsUtils.getPatternsMapFromInitialSet(patternSet);

		// ----------------------- Results -----------------------
		double[] weightedAverages = StatisticsUtils.calculateWeightedAverages(patternsMap, numberOfFeatures);
		System.out.println("The array of features averages:" + Arrays.toString(weightedAverages));

		System.out.println("\nPatterns Matrix:");
		Iterator<Map.Entry<Pattern, Double>> var17 = patternsMap.entrySet().iterator();

		while(var17.hasNext()) {
			Map.Entry<Pattern, Double> entry = (Map.Entry)var17.next(); // Corrected to Double
			Pattern pattern = entry.getKey();
			Double frequency = entry.getValue(); // Use Double here
			double[] values = pattern.getPatternValues();
			for(double value : values) {
				System.out.printf("%-10.2f", value);
			}

			System.out.printf("%-10.0f", frequency); // Print frequency as a double with no decimal places
			System.out.println();
		}
		System.out.println("\nWeighted Averages:");
		for(int i=0; i<weightedAverages.length; i++)
			System.out.printf("%-10.2f", weightedAverages[i]);
		System.out.println("\n\n");
		int totalPatterns = patternsMap.values().stream().mapToInt(Double::intValue).sum();


		// Calculate the frequency of occurrence for each distinct pattern
		Map<Pattern, Double> frequencies = StatisticsUtils.calculateFrequencies(patternsMap, totalPatterns);

		// Print out the frequencies
		for (Map.Entry<Pattern, Double> entry : frequencies.entrySet()) {
			System.out.println(entry.getKey().toString() + " frequency: " + entry.getValue());
		}
		double[] featureDispersion = StatisticsUtils.calculateFeatureDispersion(patternSet);
		System.out.println("\nFeature Dispersion:");
		for(int i = 0; i < featureDispersion.length; i++) {
			System.out.printf("\nFeature %d dispersion: %-10.2f\n", (i+1), featureDispersion[i]);
		}

		double[] feature1 = new double[numberOfPatterns];
		double[] feature2 = new double[numberOfPatterns];
		for (int i = 0; i < numberOfPatterns; i++) {
			feature1[i] = patternSet[i][0]; // Assuming index 0 is for feature 1
			feature2[i] = patternSet[i][1]; // Assuming index 1 is for feature 2
		}

// Now calculate and display the covariance between feature 1 and feature 2
		double covariance = StatisticsUtils.calculateCovariance(feature1, feature2);
		System.out.println("\nCovariance between feature 1 and feature 2: " + covariance);


		for (int i = 0; i < numberOfPatterns; i++) {
			feature1[i] = patternSet[i][0]; // First feature
			feature2[i] = patternSet[i][1]; // Second feature
		}

// Calculate and display the correlation coefficient between feature 1 and feature 2
		double correlationCoefficient = StatisticsUtils.calculateCorrelationCoefficient(feature1, feature2);
		System.out.println("\nCorrelation coefficient between feature 1 and feature 2: " + correlationCoefficient);

		System.out.println("\nStandard Deviations for each feature:");
		for (int j = 0; j < numberOfFeatures; j++) {
			double[] feature = new double[numberOfPatterns];
			for (int i = 0; i < numberOfPatterns; i++) {
				feature[i] = patternSet[i][j];
			}

			double standardDeviation = StatisticsUtils.calculateStandardDeviation(feature);
			System.out.printf("\nFeature %d standard deviation: %-10.2f\n", (j + 1), standardDeviation);
		}


		// Transform and display the standardized features
		double[][] standardizedPatternSet = StatisticsUtils.standardizeFeatures(patternSet);
		System.out.println("\nStandardized Pattern Set:");
		DataUtils.printMatrix(standardizedPatternSet);





		double[][] reducedSet = StatisticsUtils.getPatterns(patternsMap);
		double[]   weights    = StatisticsUtils.getPatternWeigths(patternsMap);
		System.out.println("\n*** Reduced pattern set with patterns' weigths");
		DataUtils.printPatternsAndWeigthsSet(reducedSet, weights);
		DataUtils.printMeansAndStandardDeviations( reducedSet, weights);

		System.out.println("\n*** Normalized Set");
		double[][] xNormalized = DataUtils.normalizedSet(reducedSet);
		DataUtils.printPatternsAndWeigthsSet(xNormalized, weights);
		DataUtils.printMeansAndStandardDeviations( xNormalized, weights);

		System.out.println("\n*** Auto-Scaled Set");
		double[][] xAuto = DataUtils.autoScaledSet(reducedSet, weights);
		DataUtils.printPatternsAndWeigthsSet(xAuto, weights);
		DataUtils.printMeansAndStandardDeviations( xAuto, weights);
	}


}
