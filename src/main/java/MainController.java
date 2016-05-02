/**
 * Created by Kevin Lau on 4/20/2016.
 */
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static spark.Spark.get;

public class MainController {
    public static void main(String[] args) throws Exception {
        WordHolder words = new WordHolder();
        boolean success = words.loadData();

        WordInstanceCounter count = new WordInstanceCounter();

        Lock mainLock = new ReentrantLock();

        get("/", (req, res) -> {
            mainLock.lock();
            String name = req.queryParams("name");
            Gson gson = new Gson();
            String result;
            if (name == null) {
                result = "{\"error\": \"No Name Supplied\"}";
            } else if (success) {
                HashMap<String, Number> response = new HashMap<String, Number>();
                response.put("timesCalled", count.logWord(name));
                response.put("foundInstances", words.getCount(name));
                result = gson.toJson(response);
            } else {
                result = "{\"error\": \"Data Loading Failure\"}";
            }
            mainLock.unlock();
            return result;
        });
    }
}
