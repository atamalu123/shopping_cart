import java.math.BigDecimal;

public interface TaxRule {
    boolean appliesTo(Item item);
    BigDecimal calculateTax(Item item);
}
