import java.io.*;
import java.util.*;

public class Highscores{
    // Write java Highscores for the program to print the highscores

    private static List<String> names;
    private static List<Integer> scores;

    static {
        names = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public static int getScore(int index){
        return scores.get(index);
    }

    public static List getScores(){
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < scores.size(); i++){
            ret.add(scores.get(i));
        }
        return ret;
    }

    public static String getName(int index){
        return names.get(index);
    }

    public static void remove(int index){
        int dscore = scores.remove(index);
        String dname = names.remove(index);
    }

    public static int getSize(){
        return names.size();
    }

    public static void printScores(){
        for(int i = 0; i < getSize(); i++){
            System.out.print(getName(i)+" ");
            System.out.println(getScore(i));
        }
    }

    public static void addScore(String name, int score){
        if (scores == null) scores = new ArrayList<Integer>();
        if (names == null) names = new ArrayList<String>();
        int index = 0;

        // Find correct position
        while (index < scores.size() && score <= scores.get(index)) {
            index++;
        }

        // Insert at correct place
        names.add(index, name);
        scores.add(index, score);
        
    }

    public static void saveScores() {
        List<String[]> newScores = new ArrayList<>();
        for(int i = 0; i < scores.size(); i++){
            newScores.add(new String[]{getName(i), ""+getScore(i)});
        }
        //while(getSize() > 0){ remove(0) }
        try {
            new ObjectOutputStream(new FileOutputStream("s.dat")).writeObject(newScores);
        } catch (Exception e) {
            System.out.println("Fail");
        }
    }

    public static void load() {
        try {
            Object obj = new ObjectInputStream(new FileInputStream("s.dat")).readObject();
            List<String[]> lista = (List<String[]>) obj;

            scores.clear();
            names.clear();

            for(String[] text : lista){
                names.add(text[0]);
                scores.add(Integer.parseInt(text[1]));
            }

        } catch (Exception e) {
            scores.clear();
            names.clear();
            //return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        
        //addScore("A", 5);
         
        //addScore("B", 7);
        //addScore("T", 1);
        //addScore("C", 3);
        
        load();
        //remove(0);
        
        printScores();
        saveScores();

    }
}

