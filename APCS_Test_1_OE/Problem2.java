import java.util.*;

public class Problem2 { 

   public static void main(String[] args) { 
      String arg = args[0];
      new Problem2().start(arg);
   }

   public void start(String arg) {

      List blankPositions = getBlankPositions(arg);
      int wordCount = countWords(arg);
      String[] words = getWords(arg);

      System.out.print("Blank Positions: ");
      for (int i = 0; i < blankPositions.size(); i++)
         System.out.print(blankPositions.get(i) + " ");

      System.out.print("Word Count: " + wordCount);

      System.out.print("Words: ");
      for (int i = 0; i < words.length; i++) {
         System.out.print(words[i] + " ");

   }

   // SOLUTION BEGINS ---------------------------------------

   public List<Integer> getBlankPositions(String s) {
      // take string, find first index of space, then cut string to what's left.
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

   // SOLUTION ENDS ---------------------------------------

}