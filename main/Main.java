
package main;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import solvers.*;
import generators.*;

class Main{

  // Follows convetion for command line arguments
  // @see http://journals.ecs.soton.ac.uk/java/tutorial/java/cmdLineArgs/conventions.html

  public static void main(String[] args){

    if(args.length == 0){
      printUsage();
      System.exit(1);
    }

    int[] clauses = null;
    int literals = 0;

    for (String s : args){
      if (s.charAt(0) == '-'){
        switch(s){
        case "-help":
          printHelp();
          break;
        default:
          System.out.printf("command '%s' not recognized.\n", s);
        }
      } else {
        try {
          literals = Integer.parseInt(s);
          FormulaGenerator fg = new FormulaGenerator();
          clauses = fg.allClauses(literals);
        } catch (Exception e){
          e.printStackTrace();
        }
      }
    }
    System.out.println("Number of clauses: " + clauses.length);
    solveClauseSet(clauses, literals);
    int[] satisfiable = Arrays.copyOf(clauses, clauses.length-1);
    System.out.println("Number of clauses: " + satisfiable.length + " (last clause removed, best-case)");
    solveClauseSet(satisfiable, literals);
    System.out.println("Number of clauses: " + satisfiable.length + " (first clause removed, worst-case)");
    satisfiable = Arrays.copyOfRange(clauses, 1, clauses.length);
    solveClauseSet(satisfiable, literals);
    System.out.println("Number of clauses: " + satisfiable.length + " (random clause removed)");
    int randomNum = ThreadLocalRandom.current().nextInt(0, clauses.length);
    satisfiable = concatArrays(Arrays.copyOf(clauses, randomNum-1), Arrays.copyOfRange(clauses, randomNum, clauses.length));
    solveClauseSet(satisfiable, literals);
    clauses = null;
  }

  private static void solveClauseSet(int[] clauseSet, int literals){
    Solver bf = new BruteForce();
    int i = bf.solve(clauseSet, literals);
    if (i < 0){
      System.out.println("Not satisfiable.");
    } else {
      System.out.println("Satisfiable! " + "Satisfying interpretation: " + toBinary(i, literals));
    }
  }

  private static boolean isInteger(String s){
    try{
      Integer.parseInt(s);
      return true;
    } catch(Exception e){
      return false;
    }
  }

  private static void printUsage(){
    System.out.println("usage:\tjava Main [<options>] <integer>");
    System.out.println("\n'java Main -help' list available options.");
  }

  private static void printHelp(){
    //TODO
  }

  /**
  * Creates a string representing a given signed integer in binary.
  *
  * @param arg Assumed signed integer to be represented in binary
  * @return String Binary representation of provided integer.
  */
  private static String toBinary(int arg){
    // Initialize string and add signed bit if arg is negative
    String s = (arg >= 0) ? "0" : "1";
    int i = (1 << 30);
    while (i > 0 ){
      // Check if bit is set by bitmasking
      if ((i & arg) > 0){
        s = s + "1";
      } else {
        s = s + "0";
      }
      i >>= 1;
    }
    return s;
  }

  private static String toBinary(int arg, int highestIndex){
    // Initialize string and add signed bit if arg is negative
    String s = "";
    int i = (1 << highestIndex-1);
    while (i > 0 ){
      // Check if bit is set by bitmasking
      if ((i & arg) > 0){
        s = s + "1";
      } else {
        s = s + "0";
      }
      i >>= 1;
    }
    return s;
  }

  private static int[] concatArrays(int[] first, int[] second){
    int[] result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return result;
  }

}
