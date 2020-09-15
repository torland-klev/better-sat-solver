
package solvers;

public class BruteForce implements Solver {

  public BruteForce(){

  }

  public int solve(int[] clauseSet, int literals){
    int interpretation = 0;
    boolean error = false;
    // Have to check at most 2^literals (i.e. 1 << literals) interpretations
    while (interpretation < (1 << literals)){
      // For each interpretation we must check if all clauses is satisfied
      error = false;
      for (int clause : clauseSet){
        // Check if clause satisfies interpretation
        // An interpretation falsifies a clause iff
        // the interpretation is a complement of the clause.
        // Therefore, if the negation of the interpretation
        // is equal to the clause, the interpretation
        // falsifies the clause.
        int negation = ~interpretation;
        // Bitwise negation on integer flips all 32 bits, but we only want to
        // flip as many there are literals. Therefore, have to flip back all
        // bits at a higher index than literal. Do this by bitmasking.
        int mask = (1 << literals) - 1;
        negation &= mask;
        //System.out.println("Interpretation: " + interpretation + " Mask: " + mask + " Negated interpretation: "+ negation + " Clause: " + clause);


        if (negation != clause){
          continue;
        } else {
          error = true;
          break;
        }
      }
      // Interpretation did not satisfy all clauses
      if (error){
        interpretation++;
        continue;
      }

      // Interpretation satisfied all clauses
      break;
    }
    if (error){
      return -1;
    }

    return interpretation;
  }

}
