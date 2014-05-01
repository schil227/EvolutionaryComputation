
public interface Candidate {
  public int getFitness();
  public Candidate[] crossOver(Candidate a);
  public void mutate(int numMutations);
  public Candidate makeCopy();
}
