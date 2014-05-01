import java.util.ArrayList;
import java.util.Random;

public class BinaryArrayCandidate implements Candidate{
	Random rand = new Random();
	int[] binaryArr;
	int length;

	public BinaryArrayCandidate(int length) {
		for (int i = 0; i < length; i++) {
			binaryArr[i] = rand.nextInt(2);
		}
		this.length = length;
	}

	public void crossOver(Candidate b) {
		int crossoverPoint = rand.nextInt(length);
		int[] tmpA = this.binaryArr.clone();
		for (int i = crossoverPoint; i < this.length; i++) {
			this.binaryArr[i] = ((BinaryArrayCandidate) b).binaryArr[i];
			((BinaryArrayCandidate) b).binaryArr[i] = tmpA[i];
		}
	}

	public void mutate(int bitsToFlip) {
		int index = rand.nextInt(length);
		for (int i = 0; i < bitsToFlip; i++) {
			this.binaryArr[index] = (this.binaryArr[index] + 1) % 2;
		}
	}

	public int getFitness(){
		int fitness = 0;
		for(int i = 0; i < length; i++){
			fitness += binaryArr[i];
		}
		return fitness;
	}
}
