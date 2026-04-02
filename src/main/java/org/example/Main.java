package org.example;

import org.example.document.Receipt;
import org.example.entity.Sale;
import org.example.renderer.RendererPDF;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Sale sale = new Sale("Samsung Galaxy A20", 2000.0);

        Receipt receipt = new Receipt("/output/receipt1", new RendererPDF(), List.of(sale), 0);

        receipt.render();

    }
}