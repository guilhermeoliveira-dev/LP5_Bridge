package org.example.document;

import org.example.entity.Sale;
import org.example.renderer.IRenderer;

import java.util.List;

public class SalesReport extends Document{

    private final List<Sale> sales;

    public SalesReport(String path, IRenderer renderer, List<Sale> sales) {
        super(path, renderer);
        this.sales = sales;
    }

    @Override
    public void render() {
        StringBuilder content;
        content = new StringBuilder();
        for(Sale sale: sales){
            content.append("Product: \"")
                    .append(sale.product())
                    .append("\" - $")
                    .append(sale.price())
                    .append("\n");
        }

        content.append("\nTotal: $").append(calculateTotal());

        renderer.renderFile(content.toString(), path);
    }

    private double calculateTotal(){
        double total = 0;
        for (Sale sale: sales){
            total += sale.price();
        }
        return total;
    }

}
