package com.example.mysqljdbcdriver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
@Testcontainers
class MysqlJdbcDriverApplicationWithTimeZoneParameterTests {

  @Container
  private static final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:5.7"))
      .withUsername("devuser")
      .withPassword("devuser")
      .withDatabaseName("devdb");

  @Autowired
  JdbcTemplate jdbcTemplate;

  @DynamicPropertySource
  static void setup(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> mysql.getJdbcUrl() + "?forceConnectionTimeZoneToSession=true");
  }

  @Test
  void contextLoads() throws SQLException {
    try (Connection c = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword()); Statement s = c.createStatement()) {
      s.executeUpdate("DELETE FROM DATE_TEST");
      s.executeUpdate("INSERT INTO DATE_TEST VALUES(current_timestamp(3), current_timestamp(3))");
      ResultSet rs = s.executeQuery("SELECT * FROM DATE_TEST");
      if (rs.next()) {
        System.out.println(rs.getObject(1, ZonedDateTime.class));
        System.out.println(rs.getObject(2, ZonedDateTime.class));
      }
      rs.close();
    }
    System.out.println(TimeZone.getDefault());
    System.out.println("timestamp type:");
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", LocalDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", ZonedDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", OffsetDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", Date.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", Timestamp.class));
    System.out.println("datetime type:");
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", LocalDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", ZonedDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", OffsetDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", Date.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", Timestamp.class));
  }

  @Test
  void contextLoads2() throws SQLException {
    jdbcTemplate.update("DELETE FROM DATE_TEST");
    jdbcTemplate.update("INSERT INTO DATE_TEST VALUES(current_timestamp(3), current_timestamp(3))");
    try (Connection c = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword()); Statement s = c.createStatement()) {
      ResultSet rs = s.executeQuery("SELECT * FROM DATE_TEST");
      if (rs.next()) {
        System.out.println(rs.getObject(1, ZonedDateTime.class));
        System.out.println(rs.getObject(2, ZonedDateTime.class));
      }
      rs.close();
    }
    System.out.println(TimeZone.getDefault());
    System.out.println("timestamp type:");
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", LocalDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", ZonedDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", OffsetDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", Date.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", Timestamp.class));
    System.out.println("datetime type:");
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", LocalDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", ZonedDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", OffsetDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", Date.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", Timestamp.class));
  }

  @Test
  void contextLoads3() throws SQLException {
    LocalDateTime now = LocalDateTime.now();
    jdbcTemplate.update("DELETE FROM DATE_TEST");
    jdbcTemplate.update("INSERT INTO DATE_TEST VALUES(?, ?)", now, now);
    try (Connection c = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword()); Statement s = c.createStatement()) {
      ResultSet rs = s.executeQuery("SELECT * FROM DATE_TEST");
      if (rs.next()) {
        System.out.println(rs.getObject(1, ZonedDateTime.class));
        System.out.println(rs.getObject(2, ZonedDateTime.class));
      }
      rs.close();
    }
    System.out.println(TimeZone.getDefault());
    System.out.println("timestamp type:");
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", LocalDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", ZonedDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", OffsetDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", Date.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_timestamp FROM DATE_TEST", Timestamp.class));
    System.out.println("datetime type:");
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", LocalDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", ZonedDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", OffsetDateTime.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", Date.class));
    System.out.println(jdbcTemplate.queryForObject("SELECT item_datetime FROM DATE_TEST", Timestamp.class));
  }
}
