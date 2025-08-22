import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Construct shopping cart
        ShoppingCart cart = new ShoppingCart(new ArrayList<>());

        cart.addItem(new Item("apples", 3, new BigDecimal("0.50"), "grocery"));
        cart.addItem(new Item("jeans", 1, new BigDecimal("40.00"), "clothing"));
        cart.addItem(new Item("laptop", 1, new BigDecimal("999.99"), "electronics"));

        // Construct receipt
        System.out.println("--- Receipt ---");
        cart.printReceiptLines();
        System.out.println();
        cart.printSubtotalLine();
        cart.printDiscountsLine();
        cart.printSubtotalAfterDiscountsLine();
        cart.printTotalTaxLine();
        cart.printGrandTotal();
        System.out.println("-----------------");
        System.out.println("Thank you for shopping!");
    }
}
