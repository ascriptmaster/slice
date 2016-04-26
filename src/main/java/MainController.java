/**
 * Created by Kevin Lau on 4/20/2016.
 */
import com.google.gson.Gson;

import java.util.HashMap;

import static spark.Spark.get;

public class MainController {
    public static void main(String[] args) throws Exception {
        WordHolder words = new WordHolder();
        boolean success = words.loadData();

        WordInstanceCounter count = new WordInstanceCounter();

        get("/", (req, res) -> {
            String name = req.queryParams("name");
            Gson gson = new Gson();
            if (name == null) {
                return "{\"error\": \"No Name Supplied\"}";
            } else if (success) {
                HashMap<String, Number> response = new HashMap<String, Number>();
                response.put("timesCalled", count.logWord(name));
                response.put("foundInstances", words.getCount(name));
                return gson.toJson(response);
            } else {
                return "{\"error\": \"Data Loading Failure\"}";
            }
        });
    }
}
