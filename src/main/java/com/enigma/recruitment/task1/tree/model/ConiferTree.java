package com.enigma.recruitment.task1.tree.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConiferTree extends Tree {

    private List<Needle> needles;

    ConiferTree(String name) {
        super(name);
        needles = new ArrayList<>();
    }

    @Override
    public void grow() {
        super.grow();

        needles.addAll(
                List.of(
                        new Needle(1L, Color.GREEN),
                        new Needle(1L, Color.GREEN),
                        new Needle(1L, Color.GREEN),
                        new Needle(1L, Color.GREEN)
                )
        );
    }

    @Override
    public void drop() {
        needles = new ArrayList<>();
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ConiferTree)) return false;
        final ConiferTree other = (ConiferTree) o;
        if (!super.equals(o)) return false;
        final Object thisNeedles = this.needles;
        final Object otherNeedles = other.needles;
        return Objects.equals(thisNeedles, otherNeedles);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        result = result * PRIME + (needles == null ? 43 : needles.hashCode());
        return result;
    }


    public String toString() {
        return "ConiferTree(super=" + super.toString() + ", needles=" + this.needles + ")";
    }
}
