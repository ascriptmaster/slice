import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.net.URLDecoder;

/**
 * Created by Kevin Lau on 4/23/2016.
 */
public class WordHolder {
    private HashMap<String, Number> wordCount;
    private static final String TARGET_PATH = "txtfiles";

    public WordHolder() {
        wordCount = new HashMap<String, Number>();
    }

    public boolean loadData() throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File folder;
        try {
            String path = classLoader.getResource(TARGET_PATH).getPath();
            folder = new File(URLDecoder.decode(path, "UTF-8"));
        } catch (NullPointerException e) {
            throw new IOException("Folder load failed");
        }
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IOException("Folder could not be loaded");
        }

        File [] files = folder.listFiles();
        for (File file : files) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String [] words = line.split("[^A-Za-z]+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        Number count = wordCount.get(word);
                        if (count != null) {
                            wordCount.put(word, count.intValue() + 1);
                        } else {
                            wordCount.put(word, 1);
                        }
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    public int getCount(String word) {
        Number count = wordCount.get(word.toLowerCase());
        if (count == null) {
            return 0;
        } else {
            return count.intValue();
        }
    }
}
