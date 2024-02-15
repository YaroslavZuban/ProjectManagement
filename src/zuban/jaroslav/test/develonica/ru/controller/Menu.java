package zuban.jaroslav.test.develonica.ru.controller;

import zuban.jaroslav.test.develonica.ru.file_job.DataSave;
import zuban.jaroslav.test.develonica.ru.file_job.DataWrite;
import zuban.jaroslav.test.develonica.ru.service.Container;
import zuban.jaroslav.test.develonica.ru.service.Project;
import zuban.jaroslav.test.develonica.ru.service.Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Container container;
    private final DataSave dataSave = new DataSave();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в приложение \"Управление проектами\"!");
        System.out.println("1. Создать проект");
        System.out.println("2. Посмотреть список проектов");
        System.out.println("3. Добавить задачу к проекту");
        System.out.println("4. Отметить задачу как выполненную");
        System.out.println("5. Выход");

        try {
            container = new DataWrite().write();

            while (true) {
                System.out.print("Выберите действие (ввидете номер): ");

                int numberTask = scanner.nextInt();
                scanner.nextLine();

                switch (numberTask) {
                    case (1) -> {
                        addProject(scanner);
                    }
                    case (2) -> {
                        printProject(scanner);
                    }
                    case (3) -> {
                        addTask(scanner);
                    }
                    case (4) -> {
                        completeTask(scanner);
                    }
                    case (5) -> {
                        System.out.println("До свидания!");
                        dataSave.save(container);

                        System.exit(0);
                    }
                    default -> System.out.println("Такой команды не существует!");
                }
            }
        } catch (InputMismatchException e) {
            dataSave.save(container);

            System.out.println("Извините, что-то пошло не так.");
        }
    }

    private void addProject(Scanner scanner) {
        System.out.print("Введите название проекта: ");
        String project = scanner.nextLine();

        container.addProject(new Project(project));
        dataSave.save(container);

        System.out.println("Проект успешно создан.");
    }

    private void printProject(Scanner scanner) {
        System.out.println("Список проектов:");
        System.out.println(container);
    }

    private void addTask(Scanner scanner) {
        System.out.print("Введите номер проекта для добавления задачи: ");
        int projectAddTaskNumber = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();

        try {
            boolean isCompleted = container.addTaskToProject(projectAddTaskNumber, taskDescription);

            if (isCompleted) {
                System.out.println("Задача успешно добавлена к проекту \"" +
                        container.getProjectIndex(projectAddTaskNumber).getHeading() + "\"");

                dataSave.save(container);
            }
        } catch (NullPointerException e) {
            System.out.println("Упс, что-то пошло не так.");
        }
    }

    private void completeTask(Scanner scanner) {
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

        dataSave.save(container);
    }
}