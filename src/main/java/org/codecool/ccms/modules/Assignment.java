package org.codecool.ccms.modules;

public class Assignment implements Displayable {

    private String content;
    private Boolean isPassed;
    private String name;
    private Module module;
    private int id;

    public Assignment(int id, String content, String name, Module module, boolean isPassed){
        this.id = id;
        this.content = content;
        this.name = name;
        this.module = module;
        this.isPassed = isPassed;
    }

    public int getId() {
        return id;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }

    @Override
    public String[] toStringList() {
        return new String[]{Integer.toString(this.id), this.name, this.module.toString(), this.isPassed.toString()};
    }
}
