import java.math.BigDecimal;

public interface DiscountRule {
    boolean appliesTo(Item item);
    BigDecimal calculateDiscount(Item item);
}