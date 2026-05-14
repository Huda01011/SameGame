import java.io.*;
import java.util.*;

/**
 * HighScores - Manages the high score list for SameGame.
 *
 * Stores player names and scores, and saves/loads them using
 * Java serialization so scores persist between game sessions.
 */
public class HighScores
{
   private static List<String> names;
   private static List<Integer> scores;

   static
   {
      names = new ArrayList<>();
      scores = new ArrayList<>();
   }

   /**
    * Returns the score at a given index.
    * @param index the position in the high score list
    * @return the score at that position
    */
   public static int getScore(int index)
   {
      return scores.get(index);
   }

   /**
    * Returns a copy of all scores.
    * @return list of all scores
    */
   public static List getScores()
   {
      List<Integer> ret = new ArrayList<>();
      for (int i = 0; i < scores.size(); i++)
         ret.add(scores.get(i));
      return ret;
   }

   /**
    * Returns the player name at a given index.
    * @param index the position in the high score list
    * @return the player name at that position
    */
   public static String getName(int index)
   {
      return names.get(index);
   }

   /**
    * Removes a score entry at a given index.
    * @param index the position to remove
    */
   public static void remove(int index)
   {
      scores.remove(index);
      names.remove(index);
   }

   /**
    * Returns the number of scores in the list.
    * @return the number of high score entries
    */
   public static int getSize()
   {
      return names.size();
   }

   /**
    * Prints all high scores to the console.
    */
   public static void printScores()
   {
      for (int i = 0; i < getSize(); i++)
      {
         System.out.print(getName(i) + " ");
         System.out.println(getScore(i));
      }
   }

   /**
    * Adds a new score to the high score list.
    * @param name the player name
    * @param score the score to add
    */
   public static void addScore(String name, int score)
   {
      if (scores == null) scores = new ArrayList<Integer>();
      if (names == null) names = new ArrayList<String>();
      int i = 0;
      while (i < names.size() && scores.get(i) > score)
         i++;
      names.add(name);
      scores.add(score);
   }

   /**
    * Saves all scores to a file using Java serialization.
    * The file is named "s.dat".
    */
   public static void saveScores()
   {
      List<String[]> newScores = new ArrayList<>();
      for (int i = 0; i < scores.size(); i++)
         newScores.add(new String[]{getName(i), "" + getScore(i)});
      try
      {
         new ObjectOutputStream(new FileOutputStream("s.dat")).writeObject(newScores);
      }
      catch (Exception e)
      {
         System.out.println("Failed to save scores.");
      }
   }

   /**
    * Loads scores from the saved file.
    * If no file exists, the list is cleared.
    */
   public static void load()
   {
      try
      {
         Object obj = new ObjectInputStream(new FileInputStream("s.dat")).readObject();
         List<String[]> lista = (List<String[]>) obj;
         scores.clear();
         names.clear();
         for (String[] text : lista)
         {
            names.add(text[0]);
            scores.add(Integer.parseInt(text[1]));
         }
      }
      catch (Exception e)
      {
         scores.clear();
         names.clear();
      }
   }

   /**
    * Main method for testing the high score system.
    * @param args unused
    */
   public static void main(String[] args)
   {
      load();
      saveScores();
      printScores();
   }
}
