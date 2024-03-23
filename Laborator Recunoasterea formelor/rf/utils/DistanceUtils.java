package ro.usv.rf.utils;

import java.util.function.BiFunction;

/*
 * Autor:Dragoi Andrei Marius
 * grupa: 3142A
 */
public class DistanceUtils {

	public static double distEuclid ( double x[], double y[] )  {
		if(x.length != y.length) throw new SpatiiDeDimensiuniDiferite("("+x.length+", "+y.length+")");

		double d = 0;
		for(int j=0; j< x.length; j++)
			d += (x[j]-y[j])* (x[j]-y[j]);
		return Math.sqrt(d);
	}
	// Method for calculating City-Block (Manhattan) distance
	public static double distCityBlock(double x[], double y[]) {
		if(x.length != y.length) throw new SpatiiDeDimensiuniDiferite("("+x.length+", "+y.length+")");

		double sum = 0;
		for(int i = 0; i < x.length; i++) {
			sum += Math.abs(x[i] - y[i]);
		}
		return sum;
	}

	// Method for calculating Chebyshev distance
	public static double distChebyshev(double x[], double y[]) {
		if(x.length != y.length) throw new SpatiiDeDimensiuniDiferite("("+x.length+", "+y.length+")");

		double maxDiff = 0;
		for(int i = 0; i < x.length; i++) {
			double currentDiff = Math.abs(x[i] - y[i]);
			if(currentDiff > maxDiff) {
				maxDiff = currentDiff;
			}
		}
		return maxDiff;
	}

	public static void main(String[] args) {
		double[] x = new double[] {1, 5},
				y = new double[] {5, 2},
				z = new double[] {3, 5};  //necoliniar cu x si y
		System.out.println("d(x,x) ="+distEuclid(x,x));
		System.out.println("d(x,y) ="+distEuclid(x,y));
		System.out.println("d(y,x) ="+distEuclid(y,x));
		System.out.println("d(x,z) + d(z,y)=" + (distEuclid(x,z) + distEuclid(z,y)) );
		System.out.println("z1 este coliniar cu x si y");
		double [] z1 = new double[] {3, 3.5};
		System.out.println("d(x,z1) + d(z1,y)=" + (distEuclid(x,z1) + distEuclid(z1,y)) );
		double [] v = new double[] {0};
		System.out.println("d(x,v) ="+distEuclid(x,v));
	}
	
}



class SpatiiDeDimensiuniDiferite extends RuntimeException{

	public SpatiiDeDimensiuniDiferite(String message) {
		super(message);
	}
}
