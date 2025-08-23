import java.math.BigDecimal;
import java.util.ArrayList;

public class DiscountService {
    private ArrayList<DiscountRule> rules;

    // Constructor
    public DiscountService() {
        rules = new ArrayList<>();
        // Add rules
        rules.add(new BulkDiscountRule());
    }

    // Calculate discount for item
    public BigDecimal calculateDiscount(Item item) {
        for (DiscountRule rule : rules){
            if(rule.appliesTo(item)){
                return rule.calculateDiscount(item);
            }
        }
        return BigDecimal.ZERO;
    }

}