package Process;

import List.LinkedList;

public class WordManager {
    private static LinkedList<Word> wordList = new LinkedList<Word>();

    // Method to count all words in wordsList - O(n)
    public static int countWords() {
        int count = 0;
        wordList.findFirst();
        while (!wordList.last()) {
            count++;
            wordList.findNext();
        }
        count++;
        return count;
    }

    // display all words in wordsList - O(n)
    public static void displayWords() {
        int count = 0;
        wordList.findFirst();
        while (!wordList.last()) {
            Word word = wordList.retrieve();
            System.out.println(word.getWord());
            count++;
            wordList.findNext();
        }
        Word word = wordList.retrieve();
        System.out.println(word.getWord());
        count++;
        System.out.println("Total words: " + count);
    }

    // Method to get or create a Word object - O(n)
    public static Word getOrCreateWord(String word, Document doc) {
        if (wordList.empty()) {
            Word newWord = new Word(word, doc);
            wordList.insert(newWord);
            return newWord;
        }

        wordList.findFirst();
        while (!wordList.last()) {
            Word existingWord = wordList.retrieve();
            if (existingWord.getWord().equals(word)) {
                existingWord.addDoc(doc);
                return existingWord;
            }
            wordList.findNext();
        }
        Word existingWord = wordList.retrieve();
        if (existingWord.getWord().equals(word)) {
            existingWord.addDoc(doc);
            return existingWord;
        }

        Word newWord = new Word(word, doc);
        wordList.insert(newWord);
        return newWord;
    }
}