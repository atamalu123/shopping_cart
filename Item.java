import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Item {
    public String name;
    public int quantity;
    public BigDecimal price_per_unit;
    public String category;
    private HashMap<String, BigDecimal> taxRates;
    
    // Constructor
    public Item(String name, int quantity, BigDecimal price_per_unit, String category){
        this.name = name;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.category = category;
        // For tax rates
        this.taxRates = new HashMap<>();
        this.taxRates.put("grocery", new BigDecimal("0.00"));
        this.taxRates.put("clothing", new BigDecimal("0.05"));
        this.taxRates.put("electronics", new BigDecimal("0.15"));
    }

    // Total for item
    public BigDecimal computeLineTotal() {
        BigDecimal total = price_per_unit
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
        return total;
    }

    // Compute tax
    public BigDecimal computeTax() {
        BigDecimal taxRate = taxRates.get(category);
        BigDecimal tax = taxRate
                .multiply(computeLineTotal())
                .setScale(2, RoundingMode.HALF_UP);
        return tax;
    }

    public BigDecimal getTaxRate() {
        return taxRates.get(category);
    }

}
