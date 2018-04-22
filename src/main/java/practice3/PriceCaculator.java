package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    private BigDecimal subTotal;
    private BigDecimal taxTotal;
    private BigDecimal grandTotal;

    public PriceCaculator(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts, BigDecimal tax) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = tax;

        subTotal = new BigDecimal(0);
    }

    public BigDecimal compute() {
        totalUpLineItems();

        subtractDiscounts();

        calculateTax();

        calculateGrandTotal();

        return grandTotal;
    }

    private void totalUpLineItems() {
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }

    private void subtractDiscounts() {
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private void calculateTax() {
        taxTotal = subTotal.multiply(tax);
    }

    private void calculateGrandTotal() {
        grandTotal = subTotal.add(taxTotal);
    }
}
