
public interface Candidate{
  public int getFitness();
  public Candidate crossOver(Candidate a);
  public Candidate mutate(int numMutations);
  public Candidate makeCopy();
  public String getString();
}
