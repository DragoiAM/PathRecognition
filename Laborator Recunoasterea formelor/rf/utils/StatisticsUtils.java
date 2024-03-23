package ro.usv.rf.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/*
 * Autor:Dragoi Andrei Marius
 * grupa: 3142A
 */
public class StatisticsUtils {
	
	public static double calculateFeatureAverage(double[] feature)
	{
		double average = 0.0;
		for (int i=0; i<feature.length; i++)
		{
			average += feature[i];
		}
		average = average/feature.length;
		return average;
	}
	

	
	public static Map<Pattern, Double> getPatternsMapFromInitialSet(double[][] patternSet) {
		Map<Pattern, Double> patternsMap = new LinkedHashMap<Pattern, Double>();
		
		for (int i=0;i<patternSet.length; i++)
		{
			Pattern patternObject = new Pattern( patternSet[i]);
			if(patternsMap.containsKey(patternObject))
			{
				double currentWeight = patternsMap.get(patternObject);
				double newWeight = currentWeight +1;
				patternsMap.put(patternObject, newWeight);
			}
			else
			{
				patternsMap.put(patternObject, 1.);
			}
		}
		return patternsMap;
	}


	public static double[] calculateWeightedAverages(Map<Pattern, Double> patternsMap, int numberOfFeatures) {
		double[]  weightedAverages = new double[numberOfFeatures];
		int totalWeight = 0;
		for(Map.Entry<Pattern, Double> entry : patternsMap.entrySet())
		{
			Pattern patternObject = entry.getKey();
			double weight = entry.getValue();
			totalWeight += weight;
			double[] patternValues = patternObject.getPatternValues();
			for (int j=0; j<numberOfFeatures; j++)
			{
				weightedAverages[j] += patternValues[j] * weight;
			}
		}
		for (int j=0; j<numberOfFeatures; j++)
		{
			weightedAverages[j] /= totalWeight;
			System.out.println(weightedAverages[j]);
		}

		return weightedAverages;
	}
	
	public static double[] getPatternWeigths(Map<Pattern, Double> patternsMap) {
		return patternsMap.values().stream()
				.mapToDouble(x -> x.doubleValue() )
				.toArray();		
	}

	public static double[][] getPatterns(Map<Pattern, Double> patternsMap) {
		return patternsMap.keySet().stream()
				.map(Pattern::getPatternValues )
				.toArray(double[][]::new);		
	}

	public static double[] calculateWeightedAverages(double[][] X, double[] f) {
		// avgj = Sigma(xj*fi) / Sigma(fi)
		int n = X.length;
		int p = X[0].length;
		int j;
		double[] avgj = new double [p];
		//to add implementation
		return avgj;
	}

	public static double[] calculateFeaturesStandardDeviations(double[][] X, double[] f, double[] avgj) {
		// stdj = Sigma[fi(xj - avgj)^2] / Sigma(fi)
		//
		int n = X.length;
		int p = X[0].length;
		int j;
		double[] stdj = new double [p];
        //to add implementation
		return  stdj;
		
	}

	
	public static double[] calculateFeaturesStandardDeviations(double[][] X, double[] f) {
		// stdj = Sigma[fi(xj - avgj)^2] / Sigma(fi)
		double avgj[] = calculateWeightedAverages(X, f);
		return calculateFeaturesStandardDeviations(X, f, avgj);
	}
	
	public double[] calculateFeaturesAverages(double[][] X) {
		return calculateWeightedAverages(getPatternsMapFromInitialSet(X), X[0].length);
	}
	public static Map<Pattern, Double> calculateFrequencies(Map<Pattern, Double> patternsMap, int totalPatterns) {
		Map<Pattern, Double> frequencies = new HashMap<>();
		for (Map.Entry<Pattern, Double> entry : patternsMap.entrySet()) {
			frequencies.put(entry.getKey(), (double) entry.getValue() / totalPatterns);
		}
		return frequencies;
	}
	public static double[] calculateFeatureDispersion(double[][] featureSet) {
		int numberOfFeatures = featureSet[0].length;
		int n = featureSet.length;
		double[] featureDispersion = new double[numberOfFeatures];
		double[] featureAverages = new double[numberOfFeatures];

		// Calculate the average for each feature first
		for (int i = 0; i < numberOfFeatures; i++) {
			double sum = 0.0;
			for (int k = 0; k < n; k++) {
				sum += featureSet[k][i];
			}
			featureAverages[i] = sum / n;
		}

		// Calculate the dispersion for each feature
		for (int i = 0; i < numberOfFeatures; i++) {
			double sumOfSquares = 0.0;
			for (int k = 0; k < n; k++) {
				sumOfSquares += Math.pow(featureSet[k][i] - featureAverages[i], 2);
			}
			featureDispersion[i] = sumOfSquares / (n - 1);
		}

		return featureDispersion;
	}
	public static double calculateCovariance(double[] feature1, double[] feature2) {
		if (feature1.length != feature2.length) {
			throw new IllegalArgumentException("Features must have the same number of observations");
		}

		double mean1 = calculateMean(feature1);
		double mean2 = calculateMean(feature2);
		double covariance = 0.0;

		for (int i = 0; i < feature1.length; i++) {
			covariance += (feature1[i] - mean1) * (feature2[i] - mean2);
		}

		return covariance / (feature1.length - 1);
	}


	public static double calculateMean(double[] feature) {
		double sum = 0.0;
		for (double value : feature) {
			sum += value;
		}
		return sum / feature.length;
	}
	public static double calculateVariance(double[] feature) {
		double mean = calculateMean(feature);
		double sumOfSquares = 0.0;
		for (double value : feature) {
			sumOfSquares += Math.pow(value - mean, 2);
		}
		return sumOfSquares / (feature.length - 1);
	}
	public static double calculateStandardDeviation(double[] feature) {
		double variance = calculateVariance(feature);
		return Math.sqrt(variance);
	}

	public static double[][] standardizeFeatures(double[][] patternSet) {
		int numberOfPatterns = patternSet.length;
		int numberOfFeatures = patternSet[0].length;
		double[][] standardizedPatternSet = new double[numberOfPatterns][numberOfFeatures];

		for (int j = 0; j < numberOfFeatures; j++) {
			double[] feature = new double[numberOfPatterns];
			// Extract the j-th feature across all patterns
			for (int i = 0; i < numberOfPatterns; i++) {
				feature[i] = patternSet[i][j];
			}
			double mean = calculateMean(feature);
			double standardDeviation = calculateStandardDeviation(feature);
			// Standardize the j-th feature
			for (int i = 0; i < numberOfPatterns; i++) {
				standardizedPatternSet[i][j] = (patternSet[i][j] - mean) / standardDeviation;
			}
		}

		return standardizedPatternSet;
	}


	public static double calculateCorrelationCoefficient(double[] feature1, double[] feature2) {
		double covariance = calculateCovariance(feature1, feature2);
		double varianceFeature1 = calculateVariance(feature1);
		double varianceFeature2 = calculateVariance(feature2);

		return covariance / Math.sqrt(varianceFeature1 * varianceFeature2);
	}
	

}
