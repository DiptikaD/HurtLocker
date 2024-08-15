import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HurtTest {

    @Test
    public void testSplitFurther(){
      String weirdString = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";
      String[] expected = {"naMe","Milk;price", "3.23;type", "Food;expiration" , "1/25/2016"};
      String[] actual = Main.splitFurther(weirdString);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNameMatches(){
        String weirdString = "naMe:Milk";
        boolean expected = true;
        boolean actual = Main.nameMatches(weirdString);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPriceMatches(){
        String weirdString = "prIce:0390392";
        boolean expected = true;
        boolean actual = Main.priceMatches(weirdString);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSplitEntries() throws Exception {

       int expected = 28;
        int actual = Main.splitEntries().size();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSeparateToCategories() throws Exception {

        int expected = 12;
        int actual = Main.separateToCategories(Main.splitEntries()).size();

        Assert.assertEquals(expected, actual);

    }
}
