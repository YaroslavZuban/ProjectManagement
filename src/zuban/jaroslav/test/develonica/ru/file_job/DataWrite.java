package zuban.jaroslav.test.develonica.ru.file_job;

import zuban.jaroslav.test.develonica.ru.service.Container;
import zuban.jaroslav.test.develonica.ru.service.Project;
import zuban.jaroslav.test.develonica.ru.service.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataWrite {
    private final static String DATA_FILE = "data_save.txt";

    public Container write() {
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;

            List<Project> projects = new ArrayList<>();
            Project project;

            while ((line = br.readLine()) != null) {
                if (line.equals("Project:")) {
                    line = br.readLine();

                    project = new Project(line);
                    projects.add(project);
                }

                if (line.equals("Task:")) {
                    String description = br.readLine();
                    String executionStatus = br.readLine();

                    boolean isCompleted = executionStatus.equals("1");

                    projects.get(projects.size() - 1).addTask(new Task(isCompleted, description));
                }
            }

            return new Container(projects);
        } catch (IOException e) {
            System.out.println("Данные не получилось считать.");
        }

        return null;
    }
}
