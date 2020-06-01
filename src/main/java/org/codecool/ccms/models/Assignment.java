package org.codecool.ccms.models;

public class Assignment implements Displayable {

    private final String content;
    private Boolean isPassed;
    private final String name;
    private final Module module;
    private final int id;

    public Assignment(int id, String content, String name, Module module, boolean isPassed){
        this.id = id;
        this.content = content;
        this.name = name;
        this.module = module;
        this.isPassed = isPassed;
    }

    public Boolean getPassed() {
        return isPassed;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }

    public String getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }

    @Override
    public String[] toStringList() {
        return new String[]{Integer.toString(this.id), this.name, this.content, this.module.toString(), this.getPassed().toString()};
    }
}
