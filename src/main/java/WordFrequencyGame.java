import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public static final String BLANK_SPACE = "\\s+";
    public static final Integer ONE = 1;

    public String getResult(String sentence){
        if (sentence.split(BLANK_SPACE).length==1) {
            return sentence + " " + ONE;
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(BLANK_SPACE);

                List<Input> wordList = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, ONE);
                    wordList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(wordList);

                List<Input> wordMapList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()){
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordMapList.add(input);
                }
                wordList = wordMapList;
                wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input word : wordList) {
                    String resultString = word.getValue() + " " +word.getWordCount();
                    joiner.add(resultString);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }
            else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
