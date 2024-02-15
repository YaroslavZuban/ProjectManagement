package zuban.jaroslav.test.develonica.ru.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final ProjectController projectController = new ProjectController();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Добро пожаловать в приложение \"Управление проектами\"!");
        System.out.println("1. Создать проект");
        System.out.println("2. Посмотреть список проектов");
        System.out.println("3. Добавить задачу к проекту");
        System.out.println("4. Отметить задачу как выполненную");
        System.out.println("5. Выход");

        try {
            projectController.write();

            while (true) {
                System.out.print("Выберите действие (ввидете номер): ");

                int numberTask = scanner.nextInt();
                scanner.nextLine();

                switch (numberTask) {
                    case (1) -> {
                        projectController.addProject(scanner);
                    }
                    case (2) -> {
                        projectController.printProject();
                    }
                    case (3) -> {
                        projectController.addTask(scanner);
                    }
                    case (4) -> {
                        projectController.completeTask(scanner);
                    }
                    case (5) -> {
                        System.out.println("До свидания!");
                        projectController.save();

                        System.exit(0);
                    }
                    default -> System.out.println("Такой команды не существует!");
                }
            }
        } catch (InputMismatchException e) {
            projectController.save();

            System.out.println("Извините, что-то пошло не так.");
        }
    }
}