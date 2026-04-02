package org.example.renderer;

public class RendererHTML implements IRenderer{
    @Override
    public void renderFile(String content, String path) {
        StringBuilder builder;
        builder = new StringBuilder();
        builder.append("Saving file to path: \"").append(path).append(".html\"\n");
        builder.append("<body>\n").append(content).append("\n</body>");

        System.out.println(builder);
    }
}
