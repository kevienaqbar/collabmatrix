/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keviena.collaborationmatrix;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kevien Aqbar
 */
@ManagedBean(name = "CompareDateTime")
@SessionScoped
public class MainComparisonDateTime implements Serializable {

    static Timestamp lastexportimedb, datetimejson;

    public void Compare() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
//            String URL = "jdbc:postgresql://localhost:5432/rin_lipi.sqlnew";
//            String USER = "postgres";
//            String PASS = "root";
            String URL = "jdbc:postgresql://127.0.0.1:5432/rin_lipi";
            String USER = "dvndb";
            String PASS = "pd11l1p12016";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(lastexporttime) FROM dataset");
            while (rs.next()) {
                lastexportimedb = rs.getTimestamp(1);
//                System.out.println("isBefore : " + LocalDateTime.now().isBefore(expiredDate)); //Apakah DateTime saat ini kurang dari DateTime pada db?
//                System.out.println("isAfter : " + LocalDateTime.now().isAfter(expiredDate)); //Apakah DateTime pada lastexporttime (DB) lebih dari yg tercantum pada File .JSON ? --true : Maka lakukan pembaruan file JSON.
//
                //Ini dimasukkan ke Main, posisi setelah mengambil timestamp db
                Gson gson = new Gson();
                BufferedReader br = null;
                try {
//                    br = new BufferedReader(new FileReader("..\\..\\..\\..\\webapp\\resources\\data\\data_auth.json"));
//                    br = new BufferedReader(new FileReader("E:\\ModulCollaborationMatrixRIN\\Coba3\\CollaborationMatrix\\src\\main\\webapp\\resources\\data\\data_auth.json"));
                    br = new BufferedReader(new FileReader("/usr/local/glassfish4/glassfish/domains/domain1/applications/dataverse-4.8.6/RIN/src/main/webapp/resources/data/data_auth.json"));
                            
                    Result result = gson.fromJson(br, Result.class);
                    datetimejson = Timestamp.valueOf(result.getDatejson());
                    System.out.println("\n\nDate pada Database  : " + lastexportimedb);
                    System.out.println("Read dari JSON File : " + datetimejson);
                    boolean a = lastexportimedb.after(datetimejson); //Apakah DateTime pada lastexporttime (DB) lebih dari yg tercantum pada File .JSON ? --true : Maka lakukan pembaruan file JSON.
                    System.out.println("isAfter : " + a); //Jika true, maka jalankan Main java
                    //

                    if (a) {
                        InitCollaboration main = new InitCollaboration();
                        main.AuthorKeyword(lastexportimedb);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (SQLException es) {
            es.printStackTrace();
        }
    }
}
