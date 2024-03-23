/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.usv.rf.utils;

import java.awt.print.Printable;
import java.util.Arrays;
import java.util.stream.IntStream;
/*
 * Autor:Dragoi Andrei Marius
 * grupa: 3142A
 */
public class DataUtils {
    
    public static void printMatrix(double[][] x){
    //System.out.println("numRows="+x.length);
        for(double[] lin: x){
            for(double xcrt: lin)
                System.out.print(String.format("%10.2f  \t",xcrt));
            System.out.println("numCols="+lin.length);
        }
    }
    
    public static void printPatternsAndWeigthsSet(double[][] X, double[] f){
	IntStream.range(0, X.length)
	.forEach(i-> System.out.println( Arrays.toString(X[i]) + " \t["+ f[i] + "]"));					
    }
    public static void printMeansAndStandardDeviations(double[][] X, double[] f) {
		System.out.println("Features means and standard deviations");
		double[] avg = StatisticsUtils.calculateWeightedAverages(X, f);
		double[] std = StatisticsUtils.calculateFeaturesStandardDeviations(X, f);
		for (int j=0; j<X[0].length; j++)
			System.out.println(String.format("%d %10.6f   %10.6f", j+1, avg[j], std[j])); 
    }
    
	public static double[][] normalizedSet(double[][] x) {
		double[][] normalizedLearningSet = new double[x.length][x[0].length];
		// to add implementation
		return normalizedLearningSet;
	}
    
	
	public static double[][] autoScaledSet (double X[][], double f[]){

		int n = X.length;
		int p = X[0].length;
		double[][] xAutoScaled = new double [n][p];
		//to add implementation
		return xAutoScaled;
	}

}
