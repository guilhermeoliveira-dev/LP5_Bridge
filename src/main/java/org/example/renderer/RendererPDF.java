package org.example.renderer;

public class RendererPDF implements IRenderer{
    @Override
    public void renderFile(String content, String path) {
        StringBuilder builder;
        builder = new StringBuilder();
        builder.append("Saving file to path: \"").append(path).append(".pdf\"\n");
        builder.append(content);

        System.out.println(builder);
    }
}
