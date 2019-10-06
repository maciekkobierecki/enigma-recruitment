package com.enigma.recruitment.task1.tree.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Leaf {

    private Long width;

    private Long height;

    private Color color;
}
