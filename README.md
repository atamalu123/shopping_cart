# Interview question

Design a shopping receipt system

# Requirements

Business Rules:
  * Compute line_total = quantity * price_per_unit for each item.
  * Compute the subtotal (sum of line totals).
  * Category-specific tax rules: grocery: exempt from tax, clothing: 5% tax, electronics: 15% tax
  * Apply tax 

Discounts:
  * If subtotal > 1000: apply 10% discount on the subtotal before tax.
  * If buying more than 5 units of the same item, apply “bulk discount”: 5% off that line item.

# Input 

A list of purchased items, each with:
  * name (string)
  * quantity (int)
  * price_per_unit (float)
  * category (string: "grocery", "electronics", "clothing", etc.)

```
[
  {"name": "apple", "quantity": 3, "price_per_unit": 0.5, "category": "grocery"},
  {"name": "jeans", "quantity": 1, "price_per_unit": 40.0, "category": "clothing"},
  {"name": "laptop", "quantity": 1, "price_per_unit": 999.99, "category": "electronics"}
]
```

# Output

Print a formatted receipt that includes:
  * Header: "--- Receipt ---"
  * Each item in the format: jeans x 1 @ 40.0 = 40.00  (Tax 5%: 2.00)
  * Bulk discount lines (if applied).
  * Subtotal before discounts, total discounts, subtotal after discounts.
  * Total tax.
  * Grand total.
  * Footer: "Thank you for shopping!"

Example output (shortened):

```
--- Receipt ---
apple x 3 @ 0.50 = 1.50  (Tax Exempt)
jeans x 1 @ 40.00 = 40.00  (Tax 5%: 2.00)
laptop x 1 @ 999.99 = 999.99  (Tax 15%: 150.00)

Subtotal: 1041.49
Discounts: -104.15
Subtotal after Discounts: 937.34
Tax: 152.00
Grand Total: 1089.34
-----------------
Thank you for shopping!
```