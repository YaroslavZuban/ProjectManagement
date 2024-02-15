package zuban.jaroslav.test.develonica.ru.file_job;

import zuban.jaroslav.test.develonica.ru.service.Container;
import zuban.jaroslav.test.develonica.ru.service.Project;
import zuban.jaroslav.test.develonica.ru.service.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataSave {
    public void save(Container archive) {
        try (FileWriter writer = new FileWriter("data_save.txt")) {
            List<Project> projectList = archive.getProjects();

            if (projectList != null) {
                writeProject(writer, projectList);
            }
        } catch (IOException e) {
            System.out.println("Файл не получилось открыть!");
        }
    }

    private void writeProject(FileWriter writer, List<Project> projects) throws IOException {
        for (Project project : projects) {
            writer.write("Project:");
            writer.write(System.lineSeparator());

            writer.write(project.getHeading());
            writer.write(System.lineSeparator());

            List<Task> tasks = project.getTasks();

            if (tasks != null) {
                writeTask(writer, tasks);
                writer.write(System.lineSeparator());
            }
        }
    }

    private void writeTask(FileWriter writer, List<Task> tasks) throws IOException {
        for (int j = 0; j < tasks.size(); j++) {
            writer.write("Task:");
            writer.write(System.lineSeparator());

            Task task = tasks.get(j);

            writer.write(task.getDescription());
            writer.write(System.lineSeparator());

            int taskCompleted = task.getIsCompleted() ? 1 : 0;

            writer.write(String.valueOf(taskCompleted));

            if (j + 1 != tasks.size()) {
                writer.write(System.lineSeparator());
            }
        }
    }
}