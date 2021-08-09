import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String BLANK_SPACE = "\\s+";
    public static final Integer ONE = 1;

    public String getResult(String sentence){
        try {
            List<WordInfo> wordInfoList = getWordInfos(sentence);
            wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo word : wordInfoList) {
                String resultString = word.getValue() + " " +word.getWordCount();
                joiner.add(resultString);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }


    private List<WordInfo> getWordInfos(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord, count);
            wordInfos.add(wordInfo);
        });
        return wordInfos;
    }

    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
