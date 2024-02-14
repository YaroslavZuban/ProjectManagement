package zuban.jaroslav.test.develonica.ru.service;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final String heading;
    private List<Task> tasks;

    public Project(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task newTask) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        tasks.add(newTask);
    }

    public Task completedTask(int numberTask) {
        if (tasks == null) {
            throw new NullPointerException("Задач не существует.");
        }

        checkIndex(numberTask);

        Task task = tasks.get(numberTask);
        task.isCompleted();

        return task;
    }

    @Override
    public String toString() {
        if (tasks == null) {
            return heading;
        }

        StringBuilder info = new StringBuilder(heading).append(System.lineSeparator());

        getTaskList(info, false);
        getTaskList(info, true);

        return String.valueOf(info);
    }

    private void getTaskList(StringBuilder info, boolean isCompleted) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (isCompleted == task.getIsCompleted()) {
                info.append(task);

                if (i + 1 != tasks.size() || !isCompleted) {
                    info.append(System.lineSeparator());
                }
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Переданный индекс вне допустимого диапазона.");
        }
    }
}