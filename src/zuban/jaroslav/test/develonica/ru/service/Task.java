package zuban.jaroslav.test.develonica.ru.service;

public class Task {
    private boolean isCompleted;
    private final String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void isCompleted() {
        isCompleted = true;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        if (!isCompleted) {
            return " - " + description;
        }

        return " -[X] " + description;
    }
}