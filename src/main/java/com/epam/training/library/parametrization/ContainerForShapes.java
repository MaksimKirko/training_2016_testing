package com.epam.training.library.parametrization;

import com.epam.training.library.inheritance.shapes.Shape;

public class ContainerForShapes<T extends Shape> {

    private T object;

    public T getObject() {

        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

}
