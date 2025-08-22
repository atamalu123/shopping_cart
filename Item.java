import java.util.HashMap;

public class Item {
    public String name;
    public int quantity;
    public double price_per_unit;
    public String category;
    private HashMap<String, Double> taxRates;
    
    public Item(String name, int quantity, double price_per_unit, String category){
        this.name = name;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.category = category;

        this.taxRates = new HashMap<>();
        this.taxRates.put("grocery", 0.00);
        this.taxRates.put("clothing", 0.05);
        this.taxRates.put("electronics", 0.15);
    }

    // Total for item
    public double computeLineTotal() {
        double total = this.quantity * this.price_per_unit;
        // extra steps for rounding
        total *= 100;
        total = (double)((int) total);
        total /= 100;
        return total;
    }

    // Compute tax
    public double computeTax() {
        double taxRate = this.taxRates.get(this.category);
        double tax = taxRate * this.computeLineTotal();
        tax *= 100;
        tax = (double)((int) tax);
        tax /= 100;
        return tax;
    }

    public double getTaxRate() {
        return this.taxRates.get(this.category);
    }

}
