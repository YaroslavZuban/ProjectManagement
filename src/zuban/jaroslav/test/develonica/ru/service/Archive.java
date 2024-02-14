package zuban.jaroslav.test.develonica.ru.service;

import java.util.ArrayList;
import java.util.List;

public class Archive {
    private List<Project> projects;

    public Project getProjectIndex(int index) {
        checkIndex(index);

        return projects.get(index - 1);
    }

    public Task completeTaskInProject(int taskNumber, int projectNumber) {
        checkIndex(taskNumber);
        checkIndex(projectNumber);

        return projects.get(projectNumber-1).completedTask(taskNumber-1);
    }

    public void addProject(Project newProject) {
        if (projects == null) {
            projects = new ArrayList<>();
        }

        projects.add(newProject);
    }

    public void addTaskToProject(int index, String description) {
        if (projects == null || description == null) {
            throw new NullPointerException("Переданный объект равен: null.");
        }

        checkIndex(index);

        projects.get(index - 1).addTask(new Task(description));
    }

    @Override
    public String toString() {
        if (projects == null) {
            return "";
        }

        StringBuilder projectList = new StringBuilder();

        for (int i = 0; i < projects.size(); i++) {
            projectList.append((i + 1)).append(" ").append(projects.get(i));

            if (i + 1 != projects.size()) {
                projectList.append(System.lineSeparator());// рассматриваю все OS
            }
        }

        return String.valueOf(projectList);
    }

    private void checkIndex(int index) {
        if (0 > index - 1 || index - 1 >= projects.size()) {
            throw new IndexOutOfBoundsException("Переданный индекс вне допустимого диапазона.");
        }
    }
}