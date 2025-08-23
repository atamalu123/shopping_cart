import java.math.BigDecimal;
import java.math.RoundingMode;

// If 5 or more items of the same type, apply 5% discount
public class BulkDiscountRule implements DiscountRule {
    private static final int THRESHOLD_QUANTITY = 5;
    private static final BigDecimal RATE = new BigDecimal("0.05");

    // Decide if discount applies to item
    @Override
    public boolean appliesTo(Item item) {
        return item.getQuantity() >= THRESHOLD_QUANTITY;
    }

    // Calculate discount
    @Override
    public BigDecimal calculateDiscount(Item item) {
        BigDecimal discount = item.computeLineTotal().multiply(RATE);
        return discount.setScale(2, RoundingMode.HALF_UP);
    }
}