import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ShoppingCart {
    
    private ArrayList<Item> items = new ArrayList<>();
    private final TaxService taxService;
    private final DiscountService discountService;

    // Constructor
    public ShoppingCart(TaxService taxService, DiscountService discountService) {
        this.taxService = taxService;
        this.discountService = discountService;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    // Calculation methods
    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = new BigDecimal("0");

        // Loop across all items in cart
        for(Item item : items) {
            subtotal = subtotal.add(item.computeLineTotal());
        }

        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotalDiscount() {
        BigDecimal discount = new BigDecimal("0");

        // Loop across all items in cart
        for(Item item : items) {
            discount = discount.add(discountService.calculateDiscount(item));
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateSubtotalAfterDiscounts() {
        BigDecimal subtotal = calculateSubtotal().subtract(calculateTotalDiscount()); // subtotal - total discount
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotalTax() {
        BigDecimal totalTax = new BigDecimal("0");

        // Loop across all items in cart
        for(Item item : items){
            totalTax = totalTax.add(item.computeTax());
        }

        return totalTax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateGrandTotal() {
        BigDecimal subtotal = calculateSubtotal();
        BigDecimal totalTax = calculateTotalTax();
        BigDecimal totalDiscount = calculateTotalDiscount();
        BigDecimal grand = subtotal.add(totalTax).subtract(totalDiscount);
        return grand.setScale(2, RoundingMode.HALF_UP);
    }

}
