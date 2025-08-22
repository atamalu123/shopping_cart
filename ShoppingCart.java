import java.util.ArrayList;

public class ShoppingCart {
    
    private ArrayList<Item> items = new ArrayList<>();

    public ShoppingCart(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);

    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public double calculateTotalTax() {
        double totalTax = 0;
        for(Item item : items){
            totalTax += item.computeTax();
        }
        // extra steps for rounding
        totalTax *= 100;
        totalTax = (double)((int) totalTax);
        totalTax /= 100;
        return totalTax;
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for(Item item : items) {
            subtotal += item.computeLineTotal();
        }
        // extra steps for rounding
        subtotal *= 100;
        subtotal = (double)((int) subtotal);
        subtotal /= 100;
        return subtotal;
    }

    public double calculateDiscounts() {
        double subtotal = calculateSubtotal();
        double discount = 0;
        // If subtotal > 1000: apply 10% discount on the subtotal before tax.
        if(subtotal > 1000){
            discount += .10 * subtotal;
        }
        // If buying more than 5 units of the same item, apply “bulk discount”: 5% off that line item.
        for(Item item : items){
            if(item.quantity >= 5){
                discount += item.computeLineTotal() * .05;
            }
        }
        // Extra steps for rounding
        discount *= 100;
        discount = (double)((int) discount);
        discount /= 100;

        return discount;
    }

    public void printReceiptLines() {
        for(Item item : items){
            StringBuilder line = new StringBuilder();
            line.append(item.name);
            line.append(" x ");
            line.append(item.quantity);
            line.append(" @ $");
            line.append(String.format("%.2f", item.price_per_unit));
            line.append(" = $");
            line.append(String.format("%.2f", item.computeLineTotal()));
            line.append(" ");
            line.append("(Tax ");
            line.append(String.format("%.0f", item.getTaxRate() * 100));
            line.append("%: ");
            line.append(String.format("$%.2f", item.computeTax()));
            line.append(")");
            System.out.println(line.toString());
        }
    }

    public void printSubtotalLine() {
        System.out.println("Subtotal: $" + calculateSubtotal());
    }

    public void printDiscountsLine() {
        System.out.println("Discounts: $-" + calculateDiscounts());
    }

    public void printSubtotalAfterDiscountsLine() {
        double subtotalAfterDiscounts = calculateSubtotal() - calculateDiscounts();
        System.out.println("Subtotal after Discounts: $" + subtotalAfterDiscounts);
    }

    public void printTotalTaxLine() {
        System.out.println("Tax: $" + calculateTotalTax());
    }

    public void printGrandTotal() {
        double grandTotal = (calculateSubtotal() - calculateDiscounts()) + calculateTotalTax();
        System.out.println("Grand total: $" + String.format("%.2f", grandTotal));
    }

}
