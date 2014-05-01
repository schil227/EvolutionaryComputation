import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;


public class Evolve {

	public static void main(String[] args) {

	}
	
	public static void EvolveCandidates(Candidate[] population, int passOnSetSize, int mutationNum, int numContinue){
		TreeMap<Integer,Candidate> sortedMap = new TreeMap();
		for(int i =0; i < population.length; i++){
			sortedMap.put(population[i].getFitness(), population[i]);
		}
		
		for(int i = population.length - passOnSetSize; i < population.length; i++){
			
		}
	}
	
	public void writeToFile(String toWrite, String fileName){
		//most of this code was taken from mkyong,
		//my lord and savior.
		try {
			File file = new File("/results/"+fileName+".txt");
 
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
