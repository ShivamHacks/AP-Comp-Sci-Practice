import java.util.*;

public class Problem2 { 

   public static void main(String[] args) { 
      new Problem2().start();
   }

   public void start() {
      List p = getBlankPositions("The cat sat on the mat.");
      for (int i = 0; i < p.size(); i++) {
         System.out.print(p.get(i) + " ");
      }
     
      System.out.println("\n---------------------");
      System.out.println("Words: " + countWords("The cat sat on the mat."));
      System.out.println("---------------------");
      
      String[] words = getWords("The cat sat on the mat.");
      for (int i = 0; i < words.length; i++) {
         System.out.println(words[i]);
      }
   }

   public List<Integer> getBlankPositions(String s) {
      List<Integer> positions = new ArrayList<Integer>();
      String changer = s;
      int adjuster = 0;
      while (changer.indexOf(" ") > -1) {
         int index = changer.indexOf(" ");
         positions.add(adjuster + index);
         adjuster += index + 1;
         changer = changer.substring(index + 1);
      }
      return positions;
   }

   public int countWords(String s) {
      String[] words = s.split(" ");
      return words.length;
   }

   public String[] getWords(String s) {
      // MUCH easier method: 
      // String[] words = s.split(" "); that's it!
      // But required to use previous two methods

      String[] words = new String[countWords(s)];
      List<Integer> blanks = getBlankPositions(s);
      words[0] = s.substring(0, blanks.get(0));
      for (int i = 1; i < blanks.size(); i++) {
         words[i] = s.substring(blanks.get(i-1) + 1, blanks.get(i)); // plus 1 to remove space
      }
      words[words.length-1] = s.substring(blanks.get(blanks.size()-1) + 1, s.length()-1); // remove the punctuation w/ the minus 1
      return words;
   }

}

// My Problem

/*

I can very easily solve any of the problems, but I need a lot of testing before I can finally get to the answer.
I need to learn to debug much faster and much more accurately (hopefully even first try).

*/

/* JUNK



      // take string, find first index of space, then cut string to what's left.

     List positions = new ArrayList<Integer>();
      char[] broken = s.toCharArray();
      for (int i = 0; i < broken.length; i++) {
         if (broken[i].equals(" "))
            positions.add(i);
      }



      */

