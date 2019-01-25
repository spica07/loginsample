package com.spring.login;

import java.sql.Connection;
import java.sql.DriverManager;


import org.junit.Test;

public class DBTest {
@Test
public void test() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver"); // 마리아DB
    // Class.forName("com.mysql.jdbc.Driver"); MySQL

    Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test", "ncddb", "ncddb");// 마리아DB
    System.out.println(con);
    }
}
