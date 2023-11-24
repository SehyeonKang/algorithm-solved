import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer;
        int wordsIndex = 0;
        int peopleCheck = 0;
        int countCheck = 1;
        ArrayList<String> wordList = new ArrayList<>();
        char lastWord = ' ';

        while (wordsIndex < words.length) {
            for (int i = 1; i <= n; i++) {
                String word = words[wordsIndex];
                if (wordsIndex == 0) {
                    wordList.add(word);
                    lastWord = word.charAt(word.length() - 1);
                    wordsIndex++;
                    continue;
                }

                if (lastWord != word.charAt(0) || wordList.contains(word)) {
                    peopleCheck = i;
                    break;
                }
                wordList.add(word);
                lastWord = word.charAt(word.length() - 1);
                wordsIndex++;
            }
            if (peopleCheck > 0) {
                break;
            }
            countCheck++;
        }

        if (peopleCheck == 0) {
            countCheck = 0;
        }

        answer = new int[]{peopleCheck, countCheck};

        return answer;
    }
}