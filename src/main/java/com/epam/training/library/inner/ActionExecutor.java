package com.epam.training.library.inner;

public class ActionExecutor {

    public void execute(Action action, Object object) {
        System.out.println("Action will be executed");
        action.execute(object);
        System.out.println("Executed!!!!");
    }

}
