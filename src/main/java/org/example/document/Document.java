package org.example.document;

import org.example.renderer.IRenderer;

public abstract class Document {

    protected String path;
    protected IRenderer renderer;

    public Document(String path, IRenderer renderer) {
        this.path = path;
        this.renderer = renderer;
    }

    public abstract void render();

}
