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
            if(item.getQuantity() >= 5) {
                discount = item.computeLineTotal()
                        .multiply(new BigDecimal("0.05"));
            }
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }

}
