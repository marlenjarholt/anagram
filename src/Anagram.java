import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* Anagram takes a file of words an give the user a list of anagrams. Special characters such as 'é' is not compared to
its counterpart 'e'. */
public class Anagram {
    private List<String> words = new ArrayList<>();
    private LinkedList<LinkedList<String>> sortedAnagrams = new LinkedList<>();

    public static void main(String[] args) {
        Anagram anagram = new Anagram();
    }

    public Anagram(){
        readFromFile("resources/eventyr.txt");
        createAnagrams();
        printResult();
    }

    // Reads each line from a file in to arraylist words.
    private void readFromFile(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e){
            System.out.println("Can't find the file");
            System.exit(1);
        }

        while(scanner.hasNextLine()){
            words.add(scanner.nextLine());
        }
    }

    // Finds all anagrams for each word in words, and adds it to sortedAnagrams
    private void createAnagrams(){
        for(int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            LinkedList<String>  anagrams = findAnagrams(word, i);

            if(!anagrams.isEmpty()) {
                anagrams.add(word);
                sortedAnagrams.add(anagrams);
            }
        }
    }

    /* Finds the anagrams to a word. Duplicates of word are removed. Index is the
    index for the word in words*/
    private LinkedList<String> findAnagrams(String word, int index){
        String sortedWord = sortWord(word);
        LinkedList<String> anagrams = new LinkedList<>();

        for(int i = index + 1; i < words.size(); i ++){
            String compareWord = words.get(i);
            if(word.equals(compareWord)){
                words.remove(i--);
                continue;
            }

            if(sortedWord.equals(sortWord(compareWord))){
                anagrams.add(compareWord);
                words.remove(i--);
            }
        }
        return anagrams;
    }

    // Sorts a word letter by letter, alphabetically.
    private String sortWord(String word){
        char[] charOfWord = word.toCharArray();
        Arrays.sort(charOfWord);
        return new String(charOfWord);
    }

    // Prints each anagram on one line
    private void printResult(){
        for (int i = 0; i < sortedAnagrams.size(); i++) {
            System.out.print(i + ":");
            for(String word : sortedAnagrams.get(i)){
                System.out.print(word + ", ");
            }
            System.out.println();
        }
    }
}
