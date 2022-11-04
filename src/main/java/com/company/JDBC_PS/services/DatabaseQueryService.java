package com.company.JDBC_PS.services;

import com.company.JDBC_PS.model.*;
import com.company.JDBC_PS.prefs.Prefs;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxSalaryWorker> findMaxSalaryWorker (Database database) throws Exception {
        List<MaxSalaryWorker> listMaxSalaryWorker = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findMaxSalaryWorkerFilename = new Prefs().getString(Prefs.QUERY_FIND_MAX_SALARY_WORKER_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(findMaxSalaryWorkerFilename)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(name, salary);
                listMaxSalaryWorker.add(maxSalaryWorker);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return listMaxSalaryWorker;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient(Database database) throws Exception {
        List<MaxProjectCountClient> listMaxProjectCountClients = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findMaxProjectsClientFilename = new Prefs().getString(Prefs.QUERY_FIND_MAX_PROJECTS_CLIENT_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(findMaxProjectsClientFilename)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("client.name");
                int id = rs.getInt("PROJECT_COUNT");
                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient(name, id);
                listMaxProjectCountClients.add(maxProjectCountClient);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return listMaxProjectCountClients;
    }

    public List<LongestProject> findLongestProject(Database database) throws Exception {
        List<LongestProject> listLongestProject = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findLongestProjectFilename = new Prefs().getString(Prefs.QUERY_FIND_LONGEST_PROJECTS_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(findLongestProjectFilename)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME");
                int monthCount = rs.getInt("MONTH_COUNT");
                LongestProject longestProject = new LongestProject(name, monthCount);
                listLongestProject.add(longestProject);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return listLongestProject;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers(Database database) throws Exception {
        List<YoungestEldestWorkers> listYoungestEldestWorkers = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findYoungestEldestWorkersFilename = new Prefs().getString(Prefs.QUERY_FIND_YOUNGEST_ELDEST_WORKERS_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(findYoungestEldestWorkersFilename)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String type = rs.getString("TYPE");
                String name = rs.getString("NAME");
                Date birthday = rs.getDate("BIRTHDAY");
                YoungestEldestWorkers youngestEldestWorkers = new YoungestEldestWorkers(type, name, birthday);
                listYoungestEldestWorkers.add(youngestEldestWorkers);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return listYoungestEldestWorkers;
    }

    public List<ProjectPrices> findProjectPrices (Database database) throws Exception {
        List<ProjectPrices> listProjectPrices = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String findProjectPricesFilename = new Prefs().getString(Prefs.QUERY_FIND_PROJECT_PRICES_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Path.of(findProjectPricesFilename)));

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("NAME");
                long price = rs.getLong("PRICE");
                ProjectPrices projectPrices = new ProjectPrices(name, price);
                listProjectPrices.add(projectPrices);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return listProjectPrices;
    }
}
