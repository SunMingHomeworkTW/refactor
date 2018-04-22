package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double calculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);

        BigDecimal taxTotal=getTaxTotal(subTotal);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);

            BigDecimal itemTotal = getItemTotal(product, item);
            subTotal = subTotal.add(itemTotal);

            BigDecimal reducedPrice = calculateReducedPrice(product, item);
            subTotal = subTotal.subtract(reducedPrice);
        }
        return subTotal;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                return item;
            }
        }
        return null;
    }

    private BigDecimal getItemTotal(Product product, OrderItem item) {
        return product.getPrice().multiply(new BigDecimal(item.getCount()));
    }

    private BigDecimal calculateReducedPrice(Product product, OrderItem curItem) {
        return product.getPrice()
            .multiply(product.getDiscountRate())
            .multiply(new BigDecimal(curItem.getCount()));
    }

    private BigDecimal getTaxTotal(BigDecimal subTotal) {
        return subTotal.multiply(tax);
    }
}
