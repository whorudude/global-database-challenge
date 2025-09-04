package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Anagram {

    private static final String SAMPLE_PATH = "src/main/resources/anagrams.txt";

    public static void main(String[] args) {
        List<List<String>> anagramGroups = new ArrayList<>();

        try (Stream<String> words = Files.lines(Paths.get(SAMPLE_PATH))) {
            words.forEach(word -> {
                boolean added = false;

                for (List<String> group : anagramGroups) {
                    if (isAnagram(word, group.get(0))) {
                        group.add(word);
                        added = true;
                        break;
                    }
                }

                if (!added) {
                    List<String> newGroup = new ArrayList<>();
                    newGroup.add(word);
                    anagramGroups.add(newGroup);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (List<String> group : anagramGroups) {
            System.out.println(String.join(" ", group));
        }
    }

    public static boolean isAnagram(String firstStr, String secondStr) {
        if (firstStr.length() != secondStr.length()) {
            return false;
        }

        char[] firstCharArray = firstStr.toCharArray();
        char[] secondCharArray = secondStr.toCharArray();

        Arrays.sort(firstCharArray);
        Arrays.sort(secondCharArray);

        return Arrays.equals(firstCharArray, secondCharArray);
    }
}
