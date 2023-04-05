import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.FileWriter;
import java.util.Scanner;

        public class Main {
            static Scanner scanner = new Scanner(System.in);
            static String[] products = {"Хлеб ", "Яблоки ", "Молоко "};
            static int[] prices = {100, 200, 300};

             static File saveFile = new File("basket.txt");

            public static void main(String[] args) throws IOException {

                Basket basket = null; //new Basket(products, prices);
                if (saveFile.exists()) {
                    basket =Basket.loadFromTxtFile(saveFile);
                } else {
                    basket = new Basket(products, prices);
                }

                while (true) {
                    showPrice();
                    System.out.println("Выберите товар и количество через пробел или введите 'end'");
                    String input = scanner.nextLine();
                    if ("end".equals(input)) {
                        break;
                    }

                    String[] parts = input.split(" ");// "номер количество" -> ["номер", "количество"]
                    int productNumber = Integer.parseInt(parts[0]) - 1;
                    int productCont = Integer.parseInt(parts[1]);
                    basket.addToCart(productNumber, productCont);
                    basket.saveTxt(saveFile);
                }
                basket.printCart();
            }
                //вывод корзины
            public static void showPrice() {
                System.out.println("Список возможных товаров для покупки");
                int sum = 0;
                for (int i = 0; i < products.length; i++) {
                        System.out.println(products[i] + " " + prices[i] + " руб/шт " );

                    }
                }
        }
