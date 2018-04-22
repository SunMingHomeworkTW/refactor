package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public PriceCaculator(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts, BigDecimal tax) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = tax;
    }

    public BigDecimal compute() {
        BigDecimal total=new BigDecimal(0);
        total=totalUpLineItems(total);
        total=subtractDiscounts(total);
        total=addTax(total);

        return total;
    }

    private BigDecimal totalUpLineItems(BigDecimal total) {
        for (OrderLineItem lineItem : orderLineItemList) {
            total=total.add(lineItem.getPrice());
        }
        return total;
    }

    private BigDecimal subtractDiscounts(BigDecimal total) {
        for (BigDecimal discount : discounts) {
            total=total.subtract(discount);
        }
        return total;
    }

    private BigDecimal addTax(BigDecimal total) {
        total=total.add(total.multiply(tax));
        return total;
    }
}
