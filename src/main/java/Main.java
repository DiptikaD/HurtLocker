import org.apache.commons.io.IOUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String readRawDataToString() throws Exception{
        ClassLoader classLoader = Main.class.getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(splitEntries());


        System.out.println(toStringData(separateToCategories(splitEntries())));
    }

    public static ArrayList<String> splitEntries() throws Exception {
        ArrayList<String> groceries = new ArrayList<>(List.of(readRawDataToString().split("##")));
        return groceries;
    }

    public static Map<String, Map<Double, Integer>> separateToCategories(ArrayList<String> groceries){
        // map< name , map < price, count of price> >
        Map<String, Map<Double, Integer>> weirdMap = new HashMap<>();

        for (String grocery : groceries) {
            String[] splited = grocery.split("[^:.a-zA-Z0-9_]");
            String name = "";
            double price = 0.0;
            int err = 0;
            //
            for (String string : splited) {

                if (splitFurther(string).length>1){
                     if (splitFurther(string)[1] != null && nameMatches(string)) {
                        name = splitFurther(string)[1];
                    } else if (splitFurther(string)[1] != null && priceMatches(string)) {
                        price = Double.parseDouble(splitFurther(string)[1]);
                    }
                }

            }
            Map<Double, Integer> defaultInnerMap = new HashMap<>();
            Map<Double, Integer> realInnerMap = weirdMap.getOrDefault(name, defaultInnerMap);

            Integer currentPriceCount = realInnerMap.getOrDefault(price, 0);
            currentPriceCount++;
            realInnerMap.put(price, currentPriceCount);
            weirdMap.put(name, realInnerMap);
        }
        return weirdMap;
    }

    public static String[] splitFurther(String str){
        return str.split(":");
    }

    public static boolean nameMatches(String str){
        Pattern patter = Pattern.compile("[nNaAmMeE]{4}.");

        Matcher matcher = patter.matcher(str);


        if(matcher.find()){
        return true;
        }
        return false;
    }

    public static boolean priceMatches(String str){
        Pattern patter2 = Pattern.compile("[PpRrIiCcEe]{5}.");
        Matcher matcher2 = patter2.matcher(str);
        if(matcher2.find()){
            return true;
        }
        return false;
    }

    public static String toStringData(Map<String, Map<Double, Integer>> weirdMap){

        for (Map.Entry<String,Map<Double, Integer>> entry : weirdMap.entrySet()){
            System.out.println("\n \n name = "+ entry.getKey() + "\n=========");
            System.out.println("price & occurance = " + entry.getValue());
        }
        return null;
    }

}
