package generators;

import java.lang.IndexOutOfBoundsException;
import java.lang.OutOfMemoryError;

public class FormulaGenerator{

  public FormulaGenerator(){

  }


  /**
  * Generates the maximum number of clauses from a given number of distinct literals.
  * Due to JVM Heap and "max array size"-concerns, the greatest amount of
  * clauses this method is able to generate is 2^28 (i.e. 28 literals).
  * A clause is represented as an integer, where each integer should be seen
  * as a bit-vector.
  * The clause-set is in clausal normal form (CNF).
  *
  * @param literals Number of distinct literals
  * @return int[] Unsatisfiable clause-set
  */
  public int[] allClauses(int literals){
    int[] clauses = null;
    if (literals < 0 || literals >= 31){
      throw new IndexOutOfBoundsException("Too many literals. Maximum number of literals is 30.");
    }

    try {
      clauses = new int[(1 << literals)];
    } catch (Exception e){
      e.printStackTrace();
    } catch (OutOfMemoryError e){
      throw new OutOfMemoryError("Standard JVM configurations do not support so many literals."
        + "Either increase the JVM heap size, or use a safe amount of literals."
        + " 28 literals is a safe standard amount.");
    }

    for (int i = 0; i<clauses.length; i++){
      clauses[i] = i;
    }

    return clauses;
  }

}
