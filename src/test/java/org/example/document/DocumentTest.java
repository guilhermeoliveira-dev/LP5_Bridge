package org.example.document;


import org.example.entity.Sale;
import org.example.renderer.RendererHTML;
import org.example.renderer.RendererPDF;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testCurriculumWithHtmlRenderer() {
        Curriculum cv = new Curriculum("/docs/cv", new RendererHTML(), "Guilherme", 20, "Java Developer");
        cv.render();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Guilherme"));
        assertTrue(output.contains("20"));
        assertTrue(output.contains("Java Developer"));

        assertTrue(output.contains("<body>") && output.contains("</body>"));
        assertTrue(output.contains(".html"));
    }

    @Test
    void testSalesReportWithPdfRenderer() {
        List<Sale> sales = Arrays.asList(
                new Sale("Keyboard", 150.00),
                new Sale("Mouse", 80.00)
        );

        SalesReport report = new SalesReport("/reports/sales", new RendererPDF(), sales);
        report.render();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Product: \"Keyboard\" - $150.0"));
        assertTrue(output.contains("Product: \"Mouse\" - $80.0"));
        assertTrue(output.contains("Total: $230.0"));
        assertTrue(output.contains(".pdf"));
    }

    @Test
    void testReceiptWithMultipleRenderers() {
        List<Sale> items = List.of(new Sale("Monitor", 1200.00));

        Receipt receiptPdf = new Receipt("/docs/receipt", new RendererPDF(), items, 0.2);
        receiptPdf.render();
        String output = outputStreamCaptor.toString();

        assertTrue(output.contains(".pdf"));
        assertTrue(output.contains("Product: \"Monitor\" - $1200.0"));
        assertTrue(output.contains("Total: $1200.0"));
        assertTrue(output.contains("Total w/ discounts: $960.0"));

        tearDown();
        setUp();

        Receipt receiptHtml = new Receipt("/docs/receipt", new RendererHTML(), items, 0.2);
        receiptHtml.render();
        output = outputStreamCaptor.toString();

        assertTrue(output.contains("<body>") && output.contains("</body>"));
        assertTrue(output.contains(".html"));
        assertTrue(output.contains("Product: \"Monitor\" - $1200.0"));
        assertTrue(output.contains("Total: $1200.0"));
        assertTrue(output.contains("Total w/ discounts: $960.0"));
    }
}