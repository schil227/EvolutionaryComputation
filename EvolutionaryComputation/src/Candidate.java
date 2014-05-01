
public interface Candidate {
  public int getFitness();
  public void crossOver(Candidate a);
  public void mutate(int numMutations);
}
