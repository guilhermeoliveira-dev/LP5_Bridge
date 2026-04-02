package org.example.document;

import org.example.renderer.IRenderer;

public class Curriculum extends Document {

    private final String name;
    private final int age;
    private final String competency;

    public Curriculum(String path, IRenderer renderer, String name, int age, String competency) {
        super(path, renderer);
        this.name = name;
        this.age = age;
        this.competency = competency;
    }

    @Override
    public void render() {

        StringBuilder content;
        content = new StringBuilder();
        content.append("Name: ").append(name).append("\n");
        content.append("Age: ").append(age).append("\n");
        content.append(competency);

        renderer.renderFile(content.toString(), path);
    }
}

