package org.codecool.ccms.modules;

public class Assignment implements Displayable {

    private final String content;
    private final Boolean isPassed;
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

    @Override
    public String[] toStringList() {
        return new String[]{Integer.toString(this.id), this.name, this.module.toString(), this.isPassed.toString()};
    }
}
