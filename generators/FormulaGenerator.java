package generators;

import java.lang.IndexOutOfBoundsException;
import java.lang.OutOfMemoryError;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
  * TODO:
  *  Need a way to represent that a literal is not in the clause.
  *  Currently, "A and B" is read as "(A or -B) and (B or -A)".
  *  That is, all clauses must have all literals.
  *
  * @param formula The string representation of a CNF formula.
  * @return int[] Array of bitvectors representing a CNF formula.
  */
  public int[] fromString(String formula){
    // Split the formula into String clauses
    String[] stringClauses = formula.split(AND);
    // Loop through each clause and add to array
    List<Integer> intClauses = new ArrayList<>();
    List<String> stringLiterals = new ArrayList<>();
    for (String s : stringClauses){
      int clause = 0;
      // Iterate through all literals of the clause.
      String[] literals = s.split(OR);
      for (String literal : literals){
        literal = literal.replace("(", "");
        literal = literal.replace(")", "");
        boolean negated = false;
        if (literal.charAt(0) == NOT){
        literal = literal.replace("-", "");
          negated = true;
        }
        System.out.print("\n" + literal);
        // Check if literal has been seen before
        // If literal has not been seen before,
        // add it to the list and retreieve its index.
        int check = stringLiterals.indexOf(literal);
        if (check < 0){
          stringLiterals.add(literal);
          check = stringLiterals.indexOf(literal);
        }
        System.out.print(" " + check + "\n");
        if (negated){
          continue;
        }
        // Increase the value of the clause depending on the index of the literal.
        // If the literal has given index 3, the clause will have 8 added to it.
        // This corresponds to setting the bit in the 3rd position.
        clause += (1 << check);
      }
      intClauses.add(clause);
    }
    System.out.println(intClauses);
    return convertList(intClauses);
  }

  /**
  * Generates the maximum number of clauses from a given number of distinct literals.
  * Due to JVM Heap and "max array size"-concerns, the greatest amount of
  * clauses this method is able to generate is 2^32-1 (i.e. 31 literals).
  * A clause is represented as an integer, where each integer should be seen
  * as a bit-vector.
  * The clause-set is in clausal normal form (CNF).
  *
  * @param literals Number of distinct literals
  * @return int[] Unsatisfiable clause-set
  */
  public int[] allClauses(int literals){
    int[] clauses = null;
    if (literals < 0){
      throw new IndexOutOfBoundsException("Number of clauses cannot be negative.");
    }
    if (literals >= 31){
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

  private int[] convertList(List<Integer> ints){
    int[] ret = new int[ints.size()];
    Iterator<Integer> iter = ints.iterator();
    for (int i = 0; i < ret.length; i++){
        ret[i] = iter.next().intValue();
    }
    return ret;
  }

  public List<String> literals(String formula){
    String[] stringClauses = formula.split(AND);
    List<String> stringLiterals = new ArrayList<>();
    for (String s : stringClauses){
      // Iterate through all literals of the clause.
      String[] literals = s.split(OR);
      for (String literal : literals){
        literal = literal.replace("(", "");
        literal = literal.replace(")", "");
        literal = literal.replace("-", "");
        // Check if literal has been seen before
        // If literal has not been seen before,
        // add it to the list and retreieve its index.
        int check = stringLiterals.indexOf(literal);
        if (check < 0){
          stringLiterals.add(literal);
          check = stringLiterals.indexOf(literal);
        }
      }
    }
    return stringLiterals;
  }

}
