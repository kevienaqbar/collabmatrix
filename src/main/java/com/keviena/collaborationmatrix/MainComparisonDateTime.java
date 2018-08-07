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
//        public Date Compare() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String URL = "jdbc:postgresql://localhost:5432/rin_lipi.sqlnew";
            String USER = "postgres";
            String PASS = "root";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(lastexporttime) FROM dataset");
            while (rs.next()) {
//               if(rs.next()) {
                lastexportimedb = rs.getTimestamp(1);

////                //
////                //Compare 2 timestamp
////                if(mytime.after(fromtime) && mytime.before(totime))
////                //mytime is in between
//                System.out.println("Date pada Database : " + lastexportimedb);
//                LocalDateTime expiredDate = lastexportimedb.toLocalDateTime();
//                System.out.println("Var expiredDate : " + expiredDate);
//                System.out.println("Date pada Computer : " + java.time.LocalDateTime.now());
//                System.out.println("isBefore : " + LocalDateTime.now().isBefore(expiredDate)); //Apakah DateTime saat ini kurang dari DateTime pada db?
//                System.out.println("isAfter : " + LocalDateTime.now().isAfter(expiredDate)); //Apakah DateTime pada lastexporttime (DB) lebih dari yg tercantum pada File .JSON ? --true : Maka lakukan pembaruan file JSON.
//
//                //Merubah dari date ke TimeStamps
//                //Get standard date and time
//                java.util.Date javaDate = new java.util.Date();
//                long javaTime = javaDate.getTime();
//                System.out.println("The Java Date is:" + javaDate.toString());
//                //Get and display SQL TIMESTAMP
//                Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
//                System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());
                //Ini dimasukkan ke Main, posisi setelah mengambil timestamp db
                Gson gson = new Gson();
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader("E:\\ModulCollaborationMatrixRIN\\Coba2\\CollaborationMatrix\\src\\main\\webapp\\resources\\data\\d1ata_auth_test.json"));
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
//                        MainAffkey main1 = new MainAffkey();
//                        main1.AffiliationKeyword(lastexportimedb);
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (SQLException es) {
            es.printStackTrace();
        }
    }
    
    
    
    
//        public Date getDatenow() {
//        Date date=new Date(lastexportimedb.getTime());  
//        return date;
////        return GregorianCalendar.getInstance().getTime();
//    }
}
