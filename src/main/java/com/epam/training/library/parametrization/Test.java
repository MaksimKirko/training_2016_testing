package com.epam.training.library.parametrization;

import com.epam.training.library.inheritance.shapes.Rectangle;

public class Test {

    public static void main(String[] args) {
        ContainerForShapes<Rectangle> container = new ContainerForShapes<Rectangle>();

        container.setObject(new Rectangle());

        Rectangle rFromContainer = container.getObject();

    }

}
