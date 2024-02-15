package zuban.jaroslav.test.develonica.ru.controller;

import zuban.jaroslav.test.develonica.ru.file_job.DataSave;
import zuban.jaroslav.test.develonica.ru.file_job.DataWrite;
import zuban.jaroslav.test.develonica.ru.service.Container;
import zuban.jaroslav.test.develonica.ru.service.Project;
import zuban.jaroslav.test.develonica.ru.service.Task;

import java.util.Scanner;

public class ProjectController {
    private Container container;
    private final DataSave dataSave;

    public ProjectController() {
        this.dataSave = new DataSave();
    }

    public void addProject(Scanner scanner) {
        System.out.print("Введите название проекта: ");
        String project = scanner.nextLine();

        container.addProject(new Project(project));
        save();

        System.out.println("Проект успешно создан.");
    }

    public void printProject() {
        System.out.println("Список проектов:");
        System.out.println(container);
    }

    public void addTask(Scanner scanner) {
        System.out.print("Введите номер проекта для добавления задачи: ");
        int projectAddTaskNumber = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();

        try {
            boolean isCompleted = container.addTaskToProject(projectAddTaskNumber, taskDescription);

            if (isCompleted) {
                System.out.println("Задача успешно добавлена к проекту \"" + container.getProjectIndex(projectAddTaskNumber).getHeading() + "\"");

                save();
            }
        } catch (NullPointerException e) {
            System.out.println("Упс, что-то пошло не так.");
        }
    }

    public void completeTask(Scanner scanner) {
        System.out.print("Введите номер проекта: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите номер задачи для отметки как выполненной: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        Task task = container.completeTaskInProject(taskNumber, projectNumber);

        if (task != null) {
            System.out.println("Задача " + task.getDescription() + " выполнена");
        }

        save();
    }

    public void save() {
        dataSave.save(container);
    }

    public void write() {
        container = new DataWrite().write();
    }
}