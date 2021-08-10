import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String sentence){
        try {
            List<WordInfo> wordInfoList = getWordInfos(sentence);
            wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            return getResultString(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String getResultString(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfoList.forEach(word -> joiner.add(word.getWord() + " " + word.getWordCount()));
        return joiner.toString();
    }

    private List<WordInfo> getWordInfos(String sentence) {
        List<String> words = getWords(sentence);
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int count = Collections.frequency(words, distinctWord);
            wordInfos.add(new WordInfo(distinctWord, count));
        });
        return wordInfos;
    }

    private List<String> getWords(String sentence) {
        return Arrays.asList(sentence.split(BLANK_SPACE));
    }
}
