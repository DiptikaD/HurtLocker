import org.apache.commons.io.IOUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception {
        String[] entries = (new Main().readRawDataToString().split("##|\\^|%|\\*|@|!"));
        System.out.println(Arrays.toString(Arrays.stream(entries).toArray()));

        Map<String, Integer> nameCounter = new HashMap<>();
        Map<String, Integer> priceCounter = new HashMap<>();
        int errorCounter = 0;

        for (String element : entries) {
            String[] eachEntry = element.split(";");
            String name = "";
            String price = "";

            for (String each : eachEntry) {
                if (each.startsWith("naMe:") || each.startsWith("NAME")) {
                    name = each.split(":")[1].trim();
                } else if (each.startsWith("price:")) {
                    price = each.split(":")[1].trim();
                }
            }

            if (name.isEmpty() || price.isEmpty()) {
                errorCounter++;
                continue;
            }

            nameCounter.put(name, nameCounter.getOrDefault(name, 0) + 1);
            priceCounter.put(price, priceCounter.getOrDefault(price, 0) + 1);

            for (Map.Entry<String, Integer> nameEntry : nameCounter.entrySet()) {
                System.out.println("Name: " + nameEntry.getKey() + "\t\tseen:" + nameEntry.getValue() + " times");
                System.out.println("============== \t\t ==============");

                for (Map.Entry<String, Integer> priceEntry : priceCounter.entrySet()) {
                    if (nameEntry.getValue() == priceEntry.getValue()) {
                        System.out.println("Price: \t" + priceEntry.getKey() + "\t\tseen: " + priceEntry.getValue() + " times");
                    }
                }
                System.out.println("------------ \t\t --------------");
            }
            System.out.println("Errors \t\t seen: " + errorCounter + " times");
        }
    }
}
