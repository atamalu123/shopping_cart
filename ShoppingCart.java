import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ShoppingCart {
    
    private ArrayList<Item> items = new ArrayList<>();

    // Constructor
    public ShoppingCart(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    // Calculation methods
    public BigDecimal calculateTotalTax() {
        BigDecimal totalTax = new BigDecimal("0");
        for(Item item : items){
            totalTax = totalTax.add(item.computeTax());
        }
        return totalTax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = new BigDecimal("0");
        for(Item item : items) {
            subtotal = subtotal.add(item.computeLineTotal());
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateDiscounts() {
        
        BigDecimal subtotal = calculateSubtotal();
        BigDecimal discount = new BigDecimal("0");

        // If subtotal > 1000: apply 10% discount on the subtotal before tax.
        final BigDecimal DISCOUNT_THRESHOLD = new BigDecimal("1000.00");
        
        if(subtotal.compareTo(DISCOUNT_THRESHOLD) > 0) { // compareTo returns 0 if left > right
            discount = discount.add(subtotal.multiply(new BigDecimal("0.10")));
        }
        
        // If buying more than 5 units of the same item, apply “bulk discount”: 5% off that line item.
        for(Item item : items) {
            if(item.quantity >= 5) {
                discount = item.computeLineTotal()
                        .multiply(new BigDecimal("0.05"));
            }
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    // Methods for printing to console
    public void printReceiptLines() {
        for(Item item : items){
            StringBuilder line = new StringBuilder();
            line.append(item.name);
            line.append(" x ");
            line.append(item.quantity);
            line.append(" @ $");
            line.append(String.format("%.2f", item.price_per_unit));
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
        System.out.println("Subtotal: $" + calculateSubtotal());
    }

    public void printDiscountsLine() {
        System.out.println("Discounts: $-" + calculateDiscounts());
    }

    public void printSubtotalAfterDiscountsLine() {
        BigDecimal subtotalAfterDiscounts = calculateSubtotal().subtract(calculateDiscounts());
        System.out.println("Subtotal after Discounts: $" + subtotalAfterDiscounts);
    }

    public void printTotalTaxLine() {
        System.out.println("Tax: $" + calculateTotalTax());
    }

    public void printGrandTotal() {
        BigDecimal grandTotal = calculateSubtotal().subtract(calculateDiscounts());
        grandTotal = grandTotal.add(calculateTotalTax());
        System.out.println("Grand total: $" + String.format("%.2f", grandTotal));
    }

}
