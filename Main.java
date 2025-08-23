import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // Construct shopping cart
        ShoppingCart cart = new ShoppingCart(new TaxService(), new DiscountService());

        cart.addItem(new Item("apples", 8, new BigDecimal("0.50"), "grocery"));
        cart.addItem(new Item("jeans", 1, new BigDecimal("40.00"), "clothing"));
        cart.addItem(new Item("laptop", 1, new BigDecimal("999.99"), "electronics"));

        // Print receipt
        ShoppingCartPrinter printCart = new ShoppingCartPrinter(cart);
        printCart.printFullReceipt();

    }
}
