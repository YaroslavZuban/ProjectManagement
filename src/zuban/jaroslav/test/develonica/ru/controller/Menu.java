package zuban.jaroslav.test.develonica.ru.controller;

import zuban.jaroslav.test.develonica.ru.service.Archive;
import zuban.jaroslav.test.develonica.ru.service.Project;
import zuban.jaroslav.test.develonica.ru.service.Task;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Archive archive = new Archive();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в приложение \"Управление проектами\"!");
        System.out.println("1. Создать проект");
        System.out.println("2. Посмотреть список проектов");
        System.out.println("3. Добавить задачу к проекту");
        System.out.println("4. Отметить задачу как выполненную");
        System.out.println("5. Выход");

        try {
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
                        System.exit(0);
                    }
                    default -> System.out.println("Такой команды не существует!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Извините, что-то пошло не так.");
        }
    }

    private void addProject(Scanner scanner) {
        System.out.print("Введите название проекта: ");
        String project = scanner.nextLine();

        archive.addProject(new Project(project));

        System.out.println("Проект успешно создан.");
    }

    private void printProject(Scanner scanner) {
        System.out.println("Список проектов:");
        System.out.println(archive);
    }

    private void addTask(Scanner scanner) {
        System.out.print("Введите номер проекта для добавления задачи: ");
        int projectAddTaskNumber = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();

        archive.addTaskToProject(projectAddTaskNumber, taskDescription);

        System.out.println("Задача успешно добавлена к проекту \"" +
                archive.getProjectIndex(projectAddTaskNumber).getHeading() + "\"");
    }

    private void completeTask(Scanner scanner) {
        System.out.print("Введите номер проекта: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите номер задачи для отметки как выполненной: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        Task task = archive.completeTaskInProject(taskNumber, projectNumber);

        System.out.println("Задача " + task.getDescription() + " выполнена");
    }
}