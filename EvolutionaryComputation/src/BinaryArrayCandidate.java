import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BinaryArrayCandidate implements Candidate, Comparable{
	Random rand = new Random();
	int[] binaryArr;
	int length;

	public BinaryArrayCandidate(int length) {
		binaryArr = new int[length];
		for (int i = 0; i < length; i++) {
			binaryArr[i] = rand.nextInt(2);
		}
		this.length = length;
	}
	
	public BinaryArrayCandidate(int length, int[] binaryArray){
		this.binaryArr = binaryArray;
		this.length = length;
	}

	public Candidate crossOver(Candidate b) {
		int crossoverPoint = rand.nextInt(length);
		BinaryArrayCandidate copyA = (BinaryArrayCandidate) this.makeCopy();
		BinaryArrayCandidate copyB = (BinaryArrayCandidate) b.makeCopy();
		for (int i = crossoverPoint; i < this.length; i++) {
			copyA.binaryArr[i] = ((BinaryArrayCandidate) copyB).binaryArr[i];
		}
		return copyA;
	}

	public Candidate mutate(int bitsToFlip) {
		int index = rand.nextInt(length);
		BinaryArrayCandidate copy = (BinaryArrayCandidate) this.makeCopy();
		for (int i = 0; i < bitsToFlip; i++) {
			copy.binaryArr[index] = (copy.binaryArr[index] + 1) % 2;
		}
		return copy;
	}

	public int getFitness(){
		int fitness = 0;
		for(int i = 0; i < length; i++){
			fitness += binaryArr[i];
		}
		return fitness;
	}

	public Candidate makeCopy(){
		return new BinaryArrayCandidate(this.length, Arrays.copyOf(this.binaryArr, this.length));
	}
	
	public String getString(){
		return binaryArr.toString();
	}

//	@Override
	public int compareTo(Candidate a) {
		return ((BinaryArrayCandidate) a).getFitness() - this.getFitness();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((BinaryArrayCandidate) o).getFitness() - this.getFitness();
	}
}
