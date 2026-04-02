package org.example.renderer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RendererTest {
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
    void testHtmlRenderer() {
        IRenderer renderer = new RendererHTML();
        renderer.renderFile("<h1>Title</h1>", "/docs/file");

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("<body>"));
        assertTrue(output.contains("</body>"));
        assertTrue(output.contains("<h1>Title</h1>"));
        assertTrue(output.contains("/docs/file.html"));
    }

    @Test
    void testPdfRenderer() {
        IRenderer renderer = new RendererPDF();
        renderer.renderFile("Title Content", "/docs/file");

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Title Content"));
        assertTrue(output.contains("/docs/file.pdf"));
    }

    @Test
    void testMarkdownRenderer() {
        IRenderer renderer = new RendererMarkdown();
        renderer.renderFile("# Title", "/docs/file");

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("# Title"));
        assertTrue(output.contains("/docs/file.md"));
    }
}