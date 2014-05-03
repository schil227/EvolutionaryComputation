import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class Evolve {
	static Random rand = new Random();

	public static void main(String[] args) {
		int numCandidates = 60;
		Candidate[] initialPopulation = new Candidate[numCandidates];
		for (int i = 0; i < numCandidates; i++) {
			initialPopulation[i] = new BinaryArrayCandidate(1000);
		}
		EvolveCandidates(initialPopulation, 20, 5, 5, 4100,"BinaryArrayTest1");
	}

	public static void EvolveCandidates(Candidate[] population, int passOnSetSize, int numMutations, int mutationRate, int iterationCount, String fileName) {

//		Arrays.sort(population);
		
		while (iterationCount > 0 && !population[0].isMaxFitness()) {
			Arrays.sort(population);
			System.out.println("Top fitness: " + population[0].getFitness() + ", Iteration: " + iterationCount + ", Candidate: " + population[passOnSetSize].getString());
			
			for (int i = passOnSetSize + 1; i < population.length; i++) {
				if (rand.nextInt(100) >= mutationRate) { // crossover
					population[i] = population[rand.nextInt(passOnSetSize)].crossOver(population[rand.nextInt(passOnSetSize)]);
				} else { // mutate
					population[i] = population[rand.nextInt(passOnSetSize)].mutate(numMutations);
				}
			}
			iterationCount--;

		}
		System.out.println("End. Top fitness:"+ population[0].getFitness()+ ", Candidate: " + population[0].getString());
	}

	public static void writeToFile(String toWrite, String fileName) {
		// most of this code was taken from mkyong,
		// my lord and savior.
		try {
			File file = new File("/results/" + fileName + ".txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(toWrite);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
