package com.company.JDBC.services;

import com.company.JDBC.prefs.Prefs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DatabasePopulateService {
    private final PreparedStatement insertWorker;
    private final PreparedStatement insertClient;
    private final PreparedStatement insertProjet;
    private final PreparedStatement insertProjectWorker;

    private static  Connection connection;

    public DatabasePopulateService(Database database) throws Exception {
        connection = database.getConnection();
        insertWorker = connection.prepareStatement(
                "INSERT INTO worker (name, birthday, level, salary) VALUES (?,?,?,?)"
        );
        insertClient = connection.prepareStatement(
                "INSERT INTO client (name) VALUES (?)"
        );
        insertProjet = connection.prepareStatement(
                "INSERT INTO project (client_id, start_date, finish_date) VALUES (?,?,?)"
        );
        insertProjectWorker = connection.prepareStatement(
                "INSERT INTO project_worker (project_id, worker_id) VALUES (?,?)"
        );
    }

    public static void main(String[] args) throws Exception {
        Database database = Database.getInstance();
        DatabasePopulateService databasePopulateService = new DatabasePopulateService(database);


        String populateWorkerDbFilename = new Prefs().getString(Prefs.POPULATE_WORKER_DB_FILE_PATH);
        List<String> csvWorker = Files.readAllLines(Path.of(populateWorkerDbFilename));
        for (String str : csvWorker) {
            String[] strArray = str.split(",");
            databasePopulateService.createNewWorker(
                    strArray[0],
                    strArray[1],
                    strArray[2],
                    strArray[3]
            );
        }
        databasePopulateService.insertWorker.close();


        String populateClientDbFilename = new Prefs().getString(Prefs.POPULATE_CLIENT_DB_FILE_PATH);
        List<String> csvClient = Files.readAllLines(Path.of(populateClientDbFilename));
        for (String str : csvClient) {
            databasePopulateService.createNewClient(str);
        }
        databasePopulateService.insertClient.close();


        String populateProjectDbFilename = new Prefs().getString(Prefs.POPULATE_PROJECT_DB_FILE_PATH);
        List<String> csvProject = Files.readAllLines(Path.of(populateProjectDbFilename));
        for (String str : csvProject) {
            String[] strArray = str.split(",");
            databasePopulateService.createNewProject(
                    strArray[0],
                    strArray[1],
                    strArray[2]
            );
        }
        databasePopulateService.insertProjet.close();


        String populateProjectWorkerDbFilename = new Prefs().getString(Prefs.POPULATE_PROJECT_WORKER_DB_FILE_PATH);
        List<String> csvProjectWorker = Files.readAllLines(Path.of(populateProjectWorkerDbFilename));
        for (String str : csvProjectWorker) {
            String[] strArray = str.split(",");
            databasePopulateService.createNewProjectWorker(
                    strArray[0],
                    strArray[1]
            );
        }
        databasePopulateService.insertProjectWorker.close();

        connection.close();
    }

    public void createNewWorker(String name, String birthday, String level, String salary) {
        try {
            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday);
            insertWorker.setString(3, level);
            insertWorker.setString(4, salary);
            insertWorker.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void createNewClient(String name) {
        try {
            insertClient.setString(1, name);
            insertClient.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void createNewProject(String client_id, String start_date, String finish_date) {
        try {
            insertProjet.setString(1, client_id);
            insertProjet.setString(2, start_date);
            insertProjet.setString(3, finish_date);
            insertProjet.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void createNewProjectWorker(String project_id, String worker_id) {
        try {
            insertProjectWorker.setString(1, project_id);
            insertProjectWorker.setString(2, worker_id);
            insertProjectWorker.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
