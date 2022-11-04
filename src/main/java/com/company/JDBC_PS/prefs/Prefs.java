package com.company.JDBC_PS.prefs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prefs {
    public final static String DB_JDBC_CONNECTION_URL="dbUrl";
    public final static String DEFAULT_PREFS_FILENAME="prefs.json";
    public final static String INIT_DB_SQL_FILE_PATH="initDbSql";
    public final static String QUERY_FIND_MAX_SALARY_WORKER_SQL_FILE_PATH="QueryFindMaxSalaryWorkerSql";
    public final static String QUERY_FIND_MAX_PROJECTS_CLIENT_SQL_FILE_PATH="QueryFindMaxProjectsClientSql";
    public final static String QUERY_FIND_LONGEST_PROJECTS_SQL_FILE_PATH="QueryFindLongestProjectSql";
    public final static String QUERY_FIND_YOUNGEST_ELDEST_WORKERS_SQL_FILE_PATH="QueryFindYoungestEldestWorkersSql";
    public final static String QUERY_FIND_PROJECT_PRICES_SQL_FILE_PATH="QueryFindProjectPricesSql";
    public final static String POPULATE_WORKER_DB_FILE_PATH="populateWorkerDbCsv";
    public final static String POPULATE_CLIENT_DB_FILE_PATH="populateClientDbCsv";
    public final static String POPULATE_PROJECT_DB_FILE_PATH="populateProjectDbCsv";
    public final static String POPULATE_PROJECT_WORKER_DB_FILE_PATH="populateProjectWorkerDbCsv";
    private Map<String, Object> prefs=new HashMap<>();

    public Prefs(){
        this(DEFAULT_PREFS_FILENAME);
    }
    public Prefs (String filename){
        try {
            String json = String.join("\n", Files.readAllLines(Paths.get(filename)));
            TypeToken<?> typeToken = TypeToken.getParameterized(
                    Map.class,
                    String.class,
                    Object.class
            );
            prefs = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public String getString(String key){
        return getPref(key).toString();
    }

    public Object getPref(String key){
        return prefs.get(key);
    }
}
