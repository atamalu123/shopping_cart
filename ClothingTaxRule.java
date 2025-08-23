import java.math.BigDecimal;
import java.math.RoundingMode;

public class ClothingTaxRule implements TaxRule {

    private final String ITEM_TYPE = "clothing";
    private final BigDecimal RATE = new BigDecimal("0.05");

    @Override
    public boolean appliesTo(Item item) {
        return item.getCategory().equalsIgnoreCase(ITEM_TYPE);
    }

    @Override
    public BigDecimal calculateTax(Item item) {
        return item.computeLineTotal()
                   .multiply(RATE)
                   .setScale(2, RoundingMode.HALF_UP);
    }
}
