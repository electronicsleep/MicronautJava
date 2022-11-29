package com.imgidea.micronaut_java;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreRepo {

    private static Logger logger = LoggerFactory.getLogger(ScoreRepo.class);

    ServiceConfig serviceConfig = new ServiceConfig();
    String datasource_connection = serviceConfig.getConfig("datasource_connection");
    String datasource_password = serviceConfig.getConfig("datasource_password");
    String datasource_user = serviceConfig.getConfig("datasource_user");

    public String ScoreAdd(String name, String score, String datetime) {
        logger.debug("AddEvent: name " + name + " score " + score + " datetime " + datetime);
        String result = "";

        //logger.debug("datasource_connection:" + datasource_connection);

        if (datasource_connection != null) {

            try (Connection connection = DriverManager.getConnection(datasource_connection, datasource_user, datasource_password)) {
                //logger.debug("Database connected");
                String query = "";
                PreparedStatement stmt = null;
                int insertResult;

                if (datetime == null) {
                    query = "INSERT INTO topscore (topscore_id, name, score, datetime) values (UUID(), ?, ?, NOW())";
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, score);
                    insertResult = stmt.executeUpdate();
                    //logger.debug(query);
                    result = "Inserting score row";
                    logger.debug("Executed query insertResult" + insertResult);
                } else {
                    query = "SELECT * FROM topscore WHERE name=? AND score=? AND datetime=?";
                    logger.debug(query);
                    stmt = connection.prepareStatement(query);

                    stmt.setString(1, name);
                    stmt.setString(2, score);
                    stmt.setString(3, datetime);

                    ResultSet rs = stmt.executeQuery();
                    int insert_record = 0;

                    while (rs.next()) {
                        insert_record = 1;
                        logger.info("Not adding score, already exists");
                        return "Duplicate score";
                    }
                    query = "INSERT INTO topscore (topscore_id, name, score, datetime) values (UUID(), ?, ?, ?)";
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, score);
                    stmt.setString(4, datetime);
                    insertResult = stmt.executeUpdate();
                    logger.debug(query);
                    result = "Inserting score";

                    logger.debug("Executed insertResult", insertResult);
                }

            } catch (SQLException e) {
                logger.error("ERROR: AddScore dt: " + e);
                result = "ERROR inserting";
            }
        } else {
            result = "No database configured";
        }
        return result;
    }

    public List<ScoreData> SelectScores() {
        //logger.debug("SelectScores");

        List<ScoreData> scoreData = new ArrayList<>();

        if (datasource_connection != null) {
            //logger.debug("Connecting database...");

            try (Connection connection = DriverManager.getConnection(datasource_connection, datasource_user, datasource_password)) {
                //logger.debug("Database connected");
                Statement statement = null;
                statement = connection.createStatement();
                String query = "SELECT * FROM topscore ORDER BY score LIMIT 100";
                //logger.debug("query: " + query);
                ResultSet rs = statement.executeQuery(query);


                while (rs.next()) {
                    String topscore_id = rs.getString("topscore_id");
                    String name = rs.getString("name");
                    String score = rs.getString("score");
                    Date datetime = rs.getTimestamp("datetime");
                    String datetime2 = datetime.toString();
                    scoreData.add(new ScoreData(topscore_id, name, score, datetime2));
                }
                statement.close();
                return scoreData;
            } catch (SQLException e) {
                logger.error("ERROR: SelectScores: " + e);
                throw new IllegalStateException("ERROR: SQL SelectScores Query failed", e);
            }

        } else {
            logger.error("ERROR: No database endpoint configured");
        }
        return null;
    }

    public List<ScoreData> SearchScores(String search) {
        //logger.debug("SearchScores");
        //logger.debug("search: " + search);

        if (datasource_connection != null) {
            //logger.debug("Connecting database...");

            try (Connection connection = DriverManager.getConnection(datasource_connection, datasource_user, datasource_password)) {
                PreparedStatement stmt = null;

                List<ScoreData> scoreData = new ArrayList<>();

                //logger.debug("Database connected");
                String query = "SELECT * FROM topscore where name LIKE ? OR score LIKE ? ORDER BY score LIMIT 100";
                search = "%" + search + "%";
                //logger.info("DEBUG query" + query);
                stmt = connection.prepareStatement(query);
                stmt.setString(1, search);
                stmt.setString(2, search);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String topscore_id = rs.getString("topscore_id");
                    String name = rs.getString("name");
                    String score = rs.getString("score");
                    Date datetime = rs.getTimestamp("datetime");
                    String datetime2 = datetime.toString();
                    scoreData.add(new ScoreData(topscore_id, name, score, datetime2));
                }
                stmt.close();
                return scoreData;
            } catch (SQLException e) {
                throw new IllegalStateException("ERROR SQL SearchScores:" + e);
            }

        } else {
            logger.error("ERROR: no database endpoint configured");
        }
        return null;
    }
}

