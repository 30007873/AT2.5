package ToDo;

import java.io.Serializable;

public class Task implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String task;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return task;
    }
}