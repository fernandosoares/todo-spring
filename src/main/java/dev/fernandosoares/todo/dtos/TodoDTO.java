package dev.fernandosoares.todo.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TodoDTO {

    @NotEmpty()
    @Size(max = 255)
    private String title;

    private Boolean done;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

}
