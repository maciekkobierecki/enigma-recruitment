package com.enigma.recruitment.task1.tree.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeciduousTree extends Tree {

    private List<Leaf> leaves;

    public DeciduousTree(String name) {
        super(name);
        leaves = new ArrayList<>();
    }

    @Override
    public void grow() {
        super.grow();

        leaves.addAll(
                List.of(
                        new Leaf(1L, 1L, Color.DEEP_GREEN),
                        new Leaf(2L, 3L, Color.DEEP_GREEN),
                        new Leaf(1L, 5L, Color.DEEP_GREEN),
                        new Leaf(4L, 2L, Color.DEEP_GREEN)
                )
        );
    }

    @Override
    public void drop() {
        leaves = new ArrayList<>();
    }

    public List<Leaf> getLeaves() {
        return Collections.unmodifiableList(leaves);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DeciduousTree)) return false;
        final DeciduousTree other = (DeciduousTree) o;
        if (!other.canEqual(this)) return false;
        if (!super.equals(o)) return false;
        final Object thisLeaves = this.getLeaves();
        final Object otherLeaves = other.getLeaves();
        return Objects.equals(thisLeaves, otherLeaves);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        result = result * PRIME + (leaves == null ? 43 : leaves.hashCode());
        return result;
    }

    private boolean canEqual(Object other) {
        return other instanceof DeciduousTree;
    }

    public String toString() {
        return "DeciduousTree(super=" + super.toString() + ", leaves=" + this.getLeaves() + ")";
    }
}
