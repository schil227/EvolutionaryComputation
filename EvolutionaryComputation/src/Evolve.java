import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.TreeMap;

public class Evolve {
	static Random rand = new Random();

	public static void main(String[] args) {
		int numCandidates = 100;
		Candidate[] initialPopulation = new Candidate[numCandidates];
		for (int i = 0; i < numCandidates; i++) {
			initialPopulation[i] = new BinaryArrayCandidate(100);
		}
		EvolveCandidates(initialPopulation, 20, 5, 5, 100,
				"BinaryArrayTest1.txt");
	}

	public static void EvolveCandidates(Candidate[] population,
			int passOnSetSize, int numMutations, int mutationRate,
			int iterationCount, String fileName) {
		TreeMap<Integer, Candidate> sortedMap = new TreeMap<Integer, Candidate>();
		while (iterationCount > 0) {
			for (int i = 0; i < population.length; i++) {
				sortedMap.put(population[i].getFitness(), population[i]);
			}
			Candidate[] passedOnPopulation = new Candidate[population.length];
			int counter = 0;
			writeToFile("Iteration: " + iterationCount + ", top fitness:"
					+ population[population.length - 1].getFitness() + "\n",
					fileName);
			for (int i = population.length - passOnSetSize; i < population.length; i++) {
				passedOnPopulation[counter] = population[i];
				counter++;
			}
			for (int i = passOnSetSize + 1; i < population.length; i++) {
				if (rand.nextInt(100) >= mutationRate) { // crossover
					passedOnPopulation[i] = passedOnPopulation[rand
							.nextInt(passOnSetSize)]
							.crossOver(passedOnPopulation[rand
									.nextInt(passOnSetSize)]);
				} else { // mutate
					passedOnPopulation[i] = passedOnPopulation[rand
							.nextInt(passOnSetSize)].mutate(numMutations);
				}
			}

		}
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
