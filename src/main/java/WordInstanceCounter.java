import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kevin Lau on 4/24/2016.
 */
public class WordInstanceCounter {
    private ConcurrentHashMap<String, Number> counter;

    public WordInstanceCounter() {
        counter = new ConcurrentHashMap<String, Number>();
    }

    public int logWord(String word) {
        word = word.toLowerCase();
        Number count = counter.get(word);
        if (count == null) {
            count = 1;
        } else {
            count = count.intValue() + 1;
        }
        counter.put(word, count);
        return count.intValue();
    }
}
