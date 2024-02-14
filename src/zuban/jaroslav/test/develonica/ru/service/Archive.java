package zuban.jaroslav.test.develonica.ru.service;

import java.util.ArrayList;
import java.util.List;

public class Archive {
    private List<Project> projects;

    public Project getProjectIndex(int index) {
        checkIndex(index);

        return projects.get(index - 1);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Task completeTaskInProject(int taskNumber, int projectNumber) {
        try {
            checkIsNotNull(projects);

            checkIndex(projectNumber);

            if (0 > taskNumber - 1 || taskNumber - 1 >= projects.get(projectNumber-1).getTasks().size()) {
                throw new IndexOutOfBoundsException("Переданный индекс вне допустимого диапазона.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Вышли за придел списка.");
            return null;
        }catch (NullPointerException e) {
            System.out.println("Обнаружен null объект.");
            return null;
        }

        try {
            return projects.get(projectNumber - 1).completedTask(taskNumber - 1);
        } catch (NullPointerException e) {
            System.out.println("Извините, что-то пошло не так.");
            return null;
        }
    }

    public void addProject(Project newProject) {
        if (projects == null) {
            projects = new ArrayList<>();
        }

        projects.add(newProject);
    }

    public boolean addTaskToProject(int index, String description) {
        try {
            checkIsNotNull(projects);
            checkIsNotNull(description);

            checkIndex(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс за пределами списка.");
            return false;
        } catch (NullPointerException e) {
            System.out.println("Обнаружен null объект.");
            return false;
        }

        projects.get(index - 1).addTask(new Task(description));
        return true;
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

    private static void checkIsNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("Переданное значение в равно null.");
        }
    }

    private void checkIndex(int index) {
        if (0 > index - 1 || index - 1 >= projects.size()) {
            throw new IndexOutOfBoundsException("Переданный индекс вне допустимого диапазона.");
        }
    }
}