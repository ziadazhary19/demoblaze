import java.util.Random;

public class CartElements {

    // generate random number for picking a random item
    Random random = new Random();
    int randomNumber = random.nextInt(9) + 1;
    public String randomItem = "/html/body/div[5]/div/div[2]/div/div["+randomNumber+"]/div/div/h4/a";
    public String addToCartButton = "/html/body/div[5]/div/div[2]/div[2]/div/a";

    public String cart = "//*[@id=\"cartur\"]";
    public String checkProductRow = "//tbody[@id='tbodyid']/tr/td[4]";
    public String deleteButton = "/html/body/div[6]/div/div[1]/div/table/tbody/tr/td[4]/a";


    public String placeOrderButton = "/html/body/div[6]/div/div[2]/button";
    public String purchaseButton = "/html/body/div[3]/div/div/div[3]/button[2]";

    public String totalBeforePlaceOrder = "//*[@id=\"totalp\"][1]";
    public String totalAfterPlaceOrder = "//*[@id=\"totalm\"][1]";

    public String name ="//*[@id=\"name\"]";
    public String country ="//*[@id=\"country\"]";
    public String city ="//*[@id=\"city\"]";
    public String card ="//*[@id=\"card\"]";
    public String month ="//*[@id=\"month\"]";
    public String year ="//*[@id=\"year\"]";

    public String tickLogo = "/html/body/div[10]/div[4]/div[1]";
    public String okButton = "/html/body/div[10]/div[7]/div/button";

}
