package main;

import java.util.ArrayList;
import java.util.List;

public class PerceptronLearn {
	
	public static void main(String[] args) {
		List<Integer[]> dataset = new ArrayList<>();
		dataset.add(new Integer[] {0, 0});
		dataset.add(new Integer[] {0, 1});
		dataset.add(new Integer[] {1, 0});
		dataset.add(new Integer[] {1, 1});
		
		int x0 = 1;
		
		float w0 = 0;
		float w1 = 0;
		float w2 = 0;
		float stepLength = 0.5f;
		
		System.out.println(String.format("|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s", "x1", "x2", "expected", "w0", "w1", "w2", "calculatedValue", "predicted", "changeSign"));
		System.out.println(String.format("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
		System.out.println("|\t\t|\t\t|\t\t\t|\t0.00\t|\t0.00\t|\t0.00\t|\t\t\t|\t\t\t|\t");
		
		boolean done = false;
		int currentDatasetIndex = 0;
		int sinceLastChange = 0;
		while(!done) {
			Integer[] currentDataset = dataset.get(currentDatasetIndex);
			int x1 = currentDataset[0];
			int x2 = currentDataset[1];
			
			int expected = trainerFunction(x1, x2);
			float calculatedValue = x0*w0 + x1*w1 + x2*w2;
			int predicted = calculatedValue >= 0 ? 1 : 0;
			
			float w0New = improveWeight(w0, stepLength, expected, predicted, x0);
			float w1New = improveWeight(w1, stepLength, expected, predicted, x1);
			float w2New = improveWeight(w2, stepLength, expected, predicted, x2);
			
			float change = w0New - w0;
			
			w0 = w0New;
			w1 = w1New;
			w2 = w2New;

			printStep(x1, x2, expected, w0, w1, w2, calculatedValue, predicted, change);
			
			sinceLastChange++;
			if(change != 0.0f) {
				sinceLastChange = 0;
			}
			if(sinceLastChange == dataset.size()) {
				done = true;
			}
			
			currentDatasetIndex = (currentDatasetIndex + 1) % dataset.size();
		}
		
		System.out.println("\n=============================================\n");
		System.out.println("w0 = "+w0);
		System.out.println("w1 = "+w1);
		System.out.println("w2 = "+w2);
	}
	
	public static int trainerFunction(int x1, int x2) {
		return (x1 != 0 || x2 != 0) ? 1 : 0;
	}

	public static float improveWeight(float wi, float stepLength, int expected, int predicted, int xi) {
		if(expected == predicted) { return wi; }
		wi = wi + stepLength * (expected - predicted) * xi;
		return wi;
	}
	
	public static void printStep(int x1, int x2, int expected, float w0, float w1, float w2, float calculatedValue, int predicted, float change) {
		String changeSign = change > 0 ? "+" : change == 0 ? "=" : "-";
		System.out.println(String.format("|\t%d\t|\t%d\t|\t%d\t\t|\t%.1f\t|\t%.1f\t|\t%.1f\t|\t%.1f\t\t|\t%d\t\t|\t%s", x1, x2, expected, w0, w1, w2, calculatedValue, predicted, changeSign));
	}
}
