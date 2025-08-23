import java.math.BigDecimal;
import java.util.ArrayList;

public class TaxService {
    private ArrayList<TaxRule> rules;

    public TaxService() {
        rules = new ArrayList<>();
        rules.add(new GroceryTaxRule());
        rules.add(new ClothingTaxRule());
        rules.add(new EletronicsTaxRule());
    }

    public BigDecimal calculateTax(Item item) {
        for (TaxRule rule : rules) {
            if (rule.appliesTo(item)){
                return rule.calculateTax(item);
            }
        }
        return BigDecimal.ZERO;
    }
}