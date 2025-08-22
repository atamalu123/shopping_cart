import java.math.BigDecimal;

// Methods for printing to console
public class ShoppingCartPrinter {

    private ShoppingCart cart;
    
    // Constructor
    public ShoppingCartPrinter(ShoppingCart cart) {
        this.cart = cart;
    }

    public void printReceiptLines() {
        for(Item item : cart.getItems()){
            StringBuilder line = new StringBuilder();
            line.append(item.getName());
            line.append(" x ");
            line.append(item.getQuantity());
            line.append(" @ $");
            line.append(String.format("%.2f", item.getPricePerUnit()));
            line.append(" = $");
            line.append(String.format("%.2f", item.computeLineTotal()));
            line.append(" ");
            line.append("(Tax ");
            line.append(String.format("%.0f", item.getTaxRate().multiply(new BigDecimal("100"))));
            line.append("%: ");
            line.append(String.format("$%.2f", item.computeTax()));
            line.append(")");
            System.out.println(line.toString());
        }
    }

    public void printSubtotalLine() {
        System.out.println("Subtotal: $" + cart.calculateSubtotal());
    }

    public void printDiscountsLine() {
        System.out.println("Discounts: $-" + cart.calculateDiscounts());
    }

    public void printSubtotalAfterDiscountsLine() {
        BigDecimal subtotalAfterDiscounts = cart.calculateSubtotal().subtract(cart.calculateDiscounts());
        System.out.println("Subtotal after Discounts: $" + subtotalAfterDiscounts);
    }

    public void printTotalTaxLine() {
        System.out.println("Tax: $" + cart.calculateTotalTax());
    }

    public void printGrandTotal() {
        BigDecimal grandTotal = cart.calculateSubtotal().subtract(cart.calculateDiscounts());
        grandTotal = grandTotal.add(cart.calculateTotalTax());
        System.out.println("Grand total: $" + String.format("%.2f", grandTotal));
    }

    public void printFullReceipt() {
        System.out.println("--- Receipt ---");
        printReceiptLines();
        System.out.println();
        printSubtotalLine();
        printDiscountsLine();
        printSubtotalAfterDiscountsLine();
        printTotalTaxLine();
        printGrandTotal();
        System.out.println("-----------------");
        System.out.println("Thank you for shopping!");
    }
}
