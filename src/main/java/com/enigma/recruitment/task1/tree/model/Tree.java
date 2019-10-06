package com.enigma.recruitment.task1.tree.model;

public abstract class Tree {

    private static final Long TRUNK_GROW_UNIT = 10L;
    private static final Long TREE_GROW_UNIT = 5L;
    private static final Long GROW_TIME_UNIT = 1L;

    private static final Long INITIAL_HEIGHT = 1L;
    private static final Long INITIAL_TRUNK_PERIMETER = 1L;

    private Long age;

    private Long height;

    private String name;

    private Long trunkPerimeter;

    private boolean isAlive;

    Tree(String name) {
        this.age = 0L;
        this.isAlive = true;
        this.height = INITIAL_HEIGHT;
        this.name = name;
        this.trunkPerimeter = INITIAL_TRUNK_PERIMETER;
    }

    public void grow() {
        age += GROW_TIME_UNIT;
        trunkPerimeter += TRUNK_GROW_UNIT;
        height += TREE_GROW_UNIT;
    }

    public abstract void drop();

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return this.age;
    }

    public Long getHeight() {
        return this.height;
    }

    public String getName() {
        return this.name;
    }

    public Long getTrunkPerimeter() {
        return this.trunkPerimeter;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public String toString() {
        return "Tree(age=" + this.getAge() + ", height=" + this.getHeight() + ", name=" + this.getName() + ", trunkPerimeter=" + this.getTrunkPerimeter() + ", isAlive=" + this.isAlive() + ")";
    }
}
