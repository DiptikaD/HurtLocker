import java.util.ArrayList;
import java.util.List;

public class StoreItems {



    private List milk;
    private List bread;
    private List cookies;
    private List apples;

    public List getMilk() {
        return milk;
    }

    public void setMilk(List milk) {
        this.milk = milk;
    }

    public List getBread() {
        return bread;
    }

    public void setBread(List bread) {
        this.bread = bread;
    }

    public List getCookies() {
        return cookies;
    }

    public void setCookies(List cookies) {
        this.cookies = cookies;
    }

    public List getApples() {
        return apples;
    }

    public void setApples(List apples) {
        this.apples = apples;
    }

    public StoreItems(){
        milk = new ArrayList<>();
        bread = new ArrayList<>();
        cookies = new ArrayList<>();
        apples = new ArrayList<>();
    }




    public static void main(String[] args) {

    }



}
