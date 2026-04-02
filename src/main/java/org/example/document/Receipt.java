package org.example.document;

import org.example.entity.Sale;
import org.example.renderer.IRenderer;

import java.util.List;

public class Receipt extends Document{

    private final List<Sale> items;
    private final double discount;

    public Receipt(String path, IRenderer renderer, List<Sale> items, double discount) {
        super(path, renderer);
        this.items = items;
        this.discount = discount;
    }

    @Override
    public void render() {
        StringBuilder content;
        content = new StringBuilder();
        for(Sale sale: items){
            content.append("Product: \"")
                    .append(sale.product())
                    .append("\" - $")
                    .append(sale.price())
                    .append("\n");
        }

        double total = calculateTotal();

        content.append("\nTotal: $").append(total);
        content.append("\nTotal w/ discounts: $").append(applyDiscount(total));

        renderer.renderFile(content.toString(), path);
    }

    private double calculateTotal(){
        double total = 0;
        for (Sale sale: items){
            total += sale.price();
        }
        return total;
    }

    private double applyDiscount(double value){
        return value * (1 - discount);
    }
}
