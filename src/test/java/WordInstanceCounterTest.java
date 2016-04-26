import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin Lau on 4/25/2016.
 */
public class WordInstanceCounterTest {
    @Test
    public void logWord() throws Exception {
        WordInstanceCounter counter = new WordInstanceCounter();
        for (int i=1; i<10; i++) {
            Assert.assertEquals(counter.logWord("Hello"), i);
        }
        Assert.assertEquals(counter.logWord("hello"), 10);

        Assert.assertEquals(counter.logWord("another"), 1);
    }

}