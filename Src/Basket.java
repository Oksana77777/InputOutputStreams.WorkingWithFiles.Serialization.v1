import java.io.*;
import java.util.Arrays;

public class Basket {
 private String[] goods;
 private int[] prices;
 private int[] quantities;

 public Basket() {

 }

 public Basket(String[] goods, int[] prices) {
  this.goods = goods;
  this.prices = prices;
  this.quantities = new int[goods.length];
 }

 public void addToCart(int productNum, int amount) {
  quantities[productNum] += amount;
 }

 public void printCart() {

  int totalPrice = 0;
  System.out.println("Список покупок:");
  for (int i = 0; i < goods.length; i++) {
   if (quantities[i] > 0) {
    int currentPrice = prices[i] * quantities[i];
    totalPrice += currentPrice;
    System.out.printf("%15s%4d р/шт%4d шт%6d руб%n", goods[i], prices[i], quantities[i], currentPrice);
   }
  }
  System.out.printf("Итого: %d " + "рублей", totalPrice);
 }

 public void saveTxt(File textFile) throws FileNotFoundException {
  try (PrintWriter out = new PrintWriter(textFile);) {
   for (String good : goods) {
    out.print(good + " ");
   }
   out.println();

   for (int price : prices) {
    out.print(price + " ");
   }
   out.println();

   for (int quantity : quantities) {
    out.print(quantity + " ");
   }
  }
 }
 public static Basket loadFromTxtFile(File textFile) {
  Basket basket = new Basket();
  try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
   String goodsStr = bufferedReader.readLine();
   String pricesStr = bufferedReader.readLine();
   String quantitiesStr = bufferedReader.readLine();

   basket.goods = goodsStr.split( " ");
   basket.prices = Arrays.stream(pricesStr.split( " "))
           .map(Integer::parseInt)
           .mapToInt(Integer::intValue)
           .toArray();

   basket.quantities = Arrays.stream(quantitiesStr.split( " "))
           .map(Integer::parseInt)
           .mapToInt(Integer::intValue)
           .toArray();

  } catch (IOException e) {
   throw new RuntimeException(e);
  }
  return basket;
 }
}
