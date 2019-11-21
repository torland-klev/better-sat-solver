package generators;

import java.lang.IndexOutOfBoundsException;
import java.lang.OutOfMemoryError;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FormulaGenerator{

  private final String AND = " and ";
  private final String OR = " or ";
  private final char NOT = '-';

  public FormulaGenerator(){

  }

  /**
  * Generates an array of bitvectors representing the supplied string formula.
  * The formula is implied to be in CNF.
  *
  * @param formula The string representation of a CNF formula.
  * @return int[] Array of bitvectors representing a CNF formula.
  */
  public int[] fromString(String formula){
    // Split the formula into String clauses
    String[] stringClauses = formula.split(AND);

    // Loop through each clause and to array
    List<Integer> intClauses = new ArrayList<>();
    List<String> stringLiterals = new ArrayList<>();
    for (String s : stringClauses){
      int clause = 0;
      String[] literals = s.split(OR);
      for (String literal : literals){
        System.out.print("\n" + l);
        // Check if literal has been seen before
        // If literal has not been seen before,
        // add it to the list and retreieve its index.
        int check = stringLiterals.indexOf(l);
        if (check < 0){
          stringLiterals.add(l);
          check = stringLiterals.indexOf(l);
        }
        System.out.print(" " + check + "\n");
        if (literal.charAt(0) == NOT){
          continue;
        }
        clause += (1 << check);
      }
      intClauses.add(clause);
    }
    // TODO: return intClauses as int[]
    System.out.println(intClauses);
    return new int[0];
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
