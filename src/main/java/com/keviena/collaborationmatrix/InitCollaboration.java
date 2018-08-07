package com.keviena.collaborationmatrix;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author Kevien Aqbar
 */

@ManagedBean(name = "user")
@SessionScoped
public class InitCollaboration implements Serializable {
    
    public static Timestamp lastexportimedb = Timestamp.valueOf("2000-01-01 00:01:01.0");



//    public static void main(String[] args) {
    public void AuthorKeyword(Timestamp lastexportimedb) {
        this.lastexportimedb = lastexportimedb;

        //Deklarasi arraylist utama yang akan bertambah terus nilainya
        ArrayList<String> arrComb = new ArrayList<String>();
        ArrayList<Integer> arrCombVal = new ArrayList<Integer>();
        ArrayList<String> arrCombKey = new ArrayList<String>();
        //Deklarasi arraylist untuk yang data tampil di matriks
        ArrayList<String> authortampil = new ArrayList<String>();
        ArrayList<Integer> sourcelisttampil = new ArrayList<Integer>();
        ArrayList<Integer> targetlisttampil = new ArrayList<Integer>();
        ArrayList<Integer> valuelisttampil = new ArrayList<Integer>();
        ArrayList<String> keywordlisttampil = new ArrayList<String>();
        ArrayList<String> keywordlisttampilfix = new ArrayList<String>();
        
        
        
                //Deklarasi arraylist utama yang akan bertambah terus nilainya
        ArrayList<String> arrCombAff = new ArrayList<String>();
        ArrayList<Integer> arrCombAffVal = new ArrayList<Integer>();
        ArrayList<String> arrCombAffKey = new ArrayList<String>();
        //Deklarasi arraylist untuk yang data tampil di matriks
        ArrayList<String> affiliationtampilAff = new ArrayList<String>();
        ArrayList<Integer> sourcelisttampilAff = new ArrayList<Integer>();
        ArrayList<Integer> targetlisttampilAff = new ArrayList<Integer>();
        ArrayList<Integer> valuelisttampilAff = new ArrayList<Integer>();
        ArrayList<String> keywordlisttampilAff = new ArrayList<String>();
        ArrayList<String> keywordlisttampilfixAff = new ArrayList<String>();

        System.out.println("INI TETAP jalan kok tanpa koneksi ke jdbc !");


        Connection connection;
        Statement statement;
        ResultSet resultSet;

        //
        //
        String queryAut_Key_Aff_Trim = "SELECT t_auth.dataset_id, t_auth.author, t_aff.affiliation, t_auth.keyword\n"
                + "FROM (\n"
                + "\n"
                + "\n"
                + "SELECT * \n"
                + "FROM (\n"
                + "\n"
                + "SELECT tauthor.*\n"
                + "FROM	(\n"
                + "	--Utama\n"
                + "	SELECT DISTINCT t2.string_agg AS author, t2.datasetversion_id AS datasetversion_id, t2.parentdatasetfield_id AS parentdatasetfield_id, datasetversion.dataset_id AS dataset_id, datasetversion.lastupdatetime AS lastupdatetime\n"
                + "	FROM	\n"
                + "		(\n"
                + "		SELECT t1.string_agg, t1.parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "		FROM 	\n"
                + "			(\n"
                + "			SELECT  string_agg(TRIM(datasetfieldvalue.value), ';') AS string_agg, datasetfieldcompoundvalue.parentdatasetfield_id AS parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "			FROM datasetfieldvalue, datasetfield, datasetfieldcompoundvalue\n"
                + "			GROUP BY datasetfieldcompoundvalue.parentdatasetfield_id  , datasetfieldvalue.datasetfield_id = datasetfield.id , datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id , datasetfield.datasetfieldtype_id = 9, datasetfield.datasetversion_id\n"
                + "			HAVING datasetfieldvalue.datasetfield_id = datasetfield.id \n"
                + "				AND datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id  \n"
                + "				AND datasetfieldcompoundvalue.parentdatasetfield_id IN (SELECT datasetfield.id FROM datasetfield WHERE datasetfield.datasetfieldtype_id = 8)\n"
                + "				AND datasetfield.datasetfieldtype_id = 9\n"
                + "			ORDER BY datasetfieldcompoundvalue.parentdatasetfield_id\n"
                + "			)	\n"
                + "			AS t1\n"
                + "			LEFT OUTER JOIN datasetfield ON (t1.parentdatasetfield_id = datasetfield.id) \n"
                + "		)\n"
                + "		AS t2\n"
                + "		LEFT OUTER JOIN datasetversion ON (t2.datasetversion_id = datasetversion.id)\n"
                + "	--/Utama\n"
                + "	) tauthor\n"
                + "\n"
                + "INNER JOIN\n"
                + "(SELECT tauthor2.dataset_id, MAX(tauthor2.lastupdatetime) AS maxlastupdatetime FROM\n"
                + "	(\n"
                + "	--Utama2\n"
                + "	SELECT DISTINCT t2.string_agg AS string_agg, t2.datasetversion_id AS datasetversion_id, t2.parentdatasetfield_id AS parentdatasetfield_id, datasetversion.dataset_id AS dataset_id, datasetversion.lastupdatetime AS lastupdatetime\n"
                + "	FROM\n"
                + "		(\n"
                + "		SELECT t1.string_agg, t1.parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "		FROM \n"
                + "			(\n"
                + "			SELECT  string_agg(TRIM(datasetfieldvalue.value), ';') AS string_agg, datasetfieldcompoundvalue.parentdatasetfield_id AS parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "			FROM datasetfieldvalue, datasetfield, datasetfieldcompoundvalue\n"
                + "			GROUP BY datasetfieldcompoundvalue.parentdatasetfield_id  , datasetfieldvalue.datasetfield_id = datasetfield.id , datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id , datasetfield.datasetfieldtype_id = 9, datasetfield.datasetversion_id\n"
                + "			HAVING datasetfieldvalue.datasetfield_id = datasetfield.id \n"
                + "				AND datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id  \n"
                + "				AND datasetfieldcompoundvalue.parentdatasetfield_id IN (SELECT datasetfield.id FROM datasetfield WHERE datasetfield.datasetfieldtype_id = 8)\n"
                + "				AND datasetfield.datasetfieldtype_id = 9\n"
                + "			ORDER BY datasetfieldcompoundvalue.parentdatasetfield_id\n"
                + "			)	\n"
                + "			AS t1\n"
                + "			LEFT OUTER JOIN datasetfield ON (t1.parentdatasetfield_id = datasetfield.id) \n"
                + "		)\n"
                + "		AS t2\n"
                + "		LEFT OUTER JOIN datasetversion ON (t2.datasetversion_id = datasetversion.id)\n"
                + "	--/Utama2\n"
                + "	) \n"
                + "	AS tauthor2 GROUP BY tauthor2.dataset_id\n"
                + "	\n"
                + ") groupedtauthor ON tauthor.dataset_id = groupedtauthor.dataset_id AND tauthor.lastupdatetime = groupedtauthor.maxlastupdatetime ORDER BY tauthor.dataset_id\n"
                + "\n"
                + "\n"
                + ")AS tableauthor LEFT OUTER JOIN (\n"
                + "\n"
                + "\n"
                + "SELECT DISTINCT TRIM(string_agg(TRIM(datasetfieldvalue.value), ';')) AS keyword, t2.v_id AS datasetversion_id\n"
                + "FROM (	\n"
                + "	SELECT t1.v_id, t1.c_id, t1.f_id, datasetfield.id AS df_id\n"
                + "	FROM 	(		\n"
                + "			SELECT  datasetfield.datasetversion_id AS v_id, datasetfieldcompoundvalue.id AS c_id, datasetfield.id AS f_id\n"
                + "			FROM datasetfield, datasetfieldcompoundvalue\n"
                + "			WHERE datasetfield.datasetfieldtype_id = 21 AND datasetfield.id = datasetfieldcompoundvalue.parentdatasetfield_id\n"
                + "		) \n"
                + "		AS t1 \n"
                + "		LEFT OUTER JOIN  datasetfield ON (t1.c_id = datasetfield.parentdatasetfieldcompoundvalue_id)\n"
                + "     ) \n"
                + "     AS t2 \n"
                + "     LEFT OUTER JOIN  datasetfieldvalue ON (t2.df_id = datasetfieldvalue.datasetfield_id)\n"
                + "GROUP BY t2.v_id\n"
                + "ORDER BY t2.v_id\n"
                + "\n"
                + ")AS tablekeyword ON (tableauthor.datasetversion_id = tablekeyword.datasetversion_id)\n"
                + "     \n"
                + "ORDER BY tableauthor.dataset_id\n"
                + "\n"
                + "--JOIN ATH & AFF\n"
                + "\n"
                + ")AS t_auth LEFT OUTER JOIN (\n"
                + "\n"
                + "\n"
                + "\n"
                + "SELECT string_agg(tableaffiliation.affiliation, ';') AS affiliation, tableaffiliation.dataset_id, tableaffiliation.lastupdatetime\n"
                + "FROM (\n"
                + "\n"
                + "SELECT taffiliation.*\n"
                + "FROM	(\n"
                + "	--Utama\n"
                + "	SELECT DISTINCT TRIM(t2.string_agg) AS affiliation, t2.datasetversion_id AS datasetversion_id, datasetversion.dataset_id AS dataset_id, datasetversion.lastupdatetime AS lastupdatetime\n"
                + "	FROM	\n"
                + "		(\n"
                + "		SELECT TRIM(t1.string_agg) AS string_agg, datasetfield.datasetversion_id\n"
                + "		FROM 	\n"
                + "			(\n"
                + "			SELECT  TRIM(datasetfieldvalue.value) AS string_agg, datasetfieldcompoundvalue.parentdatasetfield_id AS parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "			FROM datasetfieldvalue, datasetfield, datasetfieldcompoundvalue\n"
                + "			GROUP BY datasetfieldcompoundvalue.parentdatasetfield_id  , datasetfieldvalue.datasetfield_id = datasetfield.id , datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id , datasetfield.datasetfieldtype_id = 10, datasetfield.datasetversion_id, datasetfieldvalue.value\n"
                + "			HAVING datasetfieldvalue.datasetfield_id = datasetfield.id \n"
                + "				AND datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id  \n"
                + "				AND datasetfieldcompoundvalue.parentdatasetfield_id IN (SELECT datasetfield.id FROM datasetfield WHERE datasetfield.datasetfieldtype_id = 8)\n"
                + "				AND datasetfield.datasetfieldtype_id = 10\n"
                + "			)	\n"
                + "			AS t1\n"
                + "			LEFT OUTER JOIN datasetfield ON (t1.parentdatasetfield_id = datasetfield.id) \n"
                + "		)\n"
                + "		AS t2\n"
                + "		LEFT OUTER JOIN datasetversion ON (t2.datasetversion_id = datasetversion.id)\n"
                + "	--/Utama\n"
                + "	) taffiliation\n"
                + "\n"
                + "INNER JOIN\n"
                + "(SELECT taffiliation2.dataset_id, MAX(taffiliation2.lastupdatetime) AS maxlastupdatetime FROM\n"
                + "	(\n"
                + "	--Utama2\n"
                + "	SELECT DISTINCT TRIM(t2.string_agg) AS string_agg, t2.datasetversion_id AS datasetversion_id, t2.parentdatasetfield_id AS parentdatasetfield_id, datasetversion.dataset_id AS dataset_id, datasetversion.lastupdatetime AS lastupdatetime\n"
                + "	FROM\n"
                + "		(\n"
                + "		SELECT TRIM(t1.string_agg) AS string_agg, t1.parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "		FROM \n"
                + "			(\n"
                + "			SELECT TRIM(datasetfieldvalue.value) AS string_agg, datasetfieldcompoundvalue.parentdatasetfield_id AS parentdatasetfield_id, datasetfield.datasetversion_id\n"
                + "			FROM datasetfieldvalue, datasetfield, datasetfieldcompoundvalue\n"
                + "			GROUP BY datasetfieldcompoundvalue.parentdatasetfield_id  , datasetfieldvalue.datasetfield_id = datasetfield.id , datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id , datasetfield.datasetfieldtype_id = 10, datasetfield.datasetversion_id, datasetfieldvalue.value\n"
                + "			HAVING datasetfieldvalue.datasetfield_id = datasetfield.id \n"
                + "				AND datasetfield.parentdatasetfieldcompoundvalue_id = datasetfieldcompoundvalue.id  \n"
                + "				AND datasetfieldcompoundvalue.parentdatasetfield_id IN (SELECT datasetfield.id FROM datasetfield WHERE datasetfield.datasetfieldtype_id = 8)\n"
                + "				AND datasetfield.datasetfieldtype_id = 10\n"
                + "			ORDER BY datasetfieldcompoundvalue.parentdatasetfield_id\n"
                + "			)	\n"
                + "			AS t1\n"
                + "			LEFT OUTER JOIN datasetfield ON (t1.parentdatasetfield_id = datasetfield.id) \n"
                + "		)\n"
                + "		AS t2\n"
                + "		LEFT OUTER JOIN datasetversion ON (t2.datasetversion_id = datasetversion.id)\n"
                + "	--/Utama2\n"
                + "	) \n"
                + "	AS taffiliation2 GROUP BY taffiliation2.dataset_id\n"
                + "	\n"
                + ") groupedtaffiliation ON taffiliation.dataset_id = groupedtaffiliation.dataset_id AND taffiliation.lastupdatetime = groupedtaffiliation.maxlastupdatetime ORDER BY taffiliation.dataset_id\n"
                + "\n"
                + "\n"
                + ")AS tableaffiliation LEFT OUTER JOIN (\n"
                + "\n"
                + "\n"
                + "SELECT DISTINCT string_agg(datasetfieldvalue.value, ';') AS keyword, t2.v_id AS datasetversion_id\n"
                + "FROM (	\n"
                + "	SELECT t1.v_id, t1.c_id, t1.f_id, datasetfield.id AS df_id\n"
                + "	FROM 	(		\n"
                + "			SELECT  datasetfield.datasetversion_id AS v_id, datasetfieldcompoundvalue.id AS c_id, datasetfield.id AS f_id\n"
                + "			FROM datasetfield, datasetfieldcompoundvalue\n"
                + "			WHERE datasetfield.datasetfieldtype_id = 21 AND datasetfield.id = datasetfieldcompoundvalue.parentdatasetfield_id\n"
                + "		) \n"
                + "		AS t1 \n"
                + "		LEFT OUTER JOIN  datasetfield ON (t1.c_id = datasetfield.parentdatasetfieldcompoundvalue_id)\n"
                + "     ) \n"
                + "     AS t2 \n"
                + "     LEFT OUTER JOIN  datasetfieldvalue ON (t2.df_id = datasetfieldvalue.datasetfield_id)\n"
                + "GROUP BY t2.v_id\n"
                + "ORDER BY t2.v_id\n"
                + "\n"
                + ")AS tablekeyword ON (tableaffiliation.datasetversion_id = tablekeyword.datasetversion_id)\n"
                + "\n"
                + "GROUP BY tableaffiliation.dataset_id, tableaffiliation.datasetversion_id, tableaffiliation.dataset_id, tableaffiliation.lastupdatetime\n"
                + "\n"
                + "-- Sebagai table alias t_aff, diJOIN pada kolom dataset_id\n"
                + ")AS t_aff ON (t_auth.dataset_id = t_aff.dataset_id);";

//      
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
            ResultSet rs = st.executeQuery(queryAut_Key_Aff_Trim);
            while (rs.next()) {
//               System.out.println(rs.getString(1));

                String dataset_id = rs.getString("dataset_id");
                String authors = rs.getString("author");
//               String keyword = resultSet.getString("keyword");
                String affiliations = rs.getString("affiliation");
//                String author = rs.getString("affiliation");
                String keywords = rs.getString("keyword");

//                System.out.println("*\nID       : "+dataset_id+ "\nPenulis  : "+authors+ "\nAfiliasi : "+affiliation+ "\nKeyword  : " + keyword);
//                System.out.println("ID     : "+dataset_id);
                if (keywords == null) {
                    keywords = ""; //Ganti agar nilai keyword yg kosong tidak null
                }

                String[] arrAuthors = authors.trim().split("\\s*;\\s*");
                String[] arrAffiliations = affiliations.trim().split("\\s*;\\s*");
//                List<String> items = Arrays.asList(authors.trim().split("\\s*;\\s*"));  //Pisahkan author berdasarkan semicolon dan simpan menjadi arrAuthor (idx0,idx1,idx2,...)
//                //Selain Java8
//                String[] arrAuthor = items.toArray(new String[0]); // Convert List<String> menjadi String[]
//                System.out.println("\nAmbil :-" + stringArray[1] + "-\n");
//                 System.out.println("Tokenisasi Frasa :\n" + arrAuthors[0] +"\n");
//                int k=0; //Tokenisasi Frasa
//                for( String x : arrAuthors){
//                    k++;
//                    System.out.println("Token"+k+" : " +x+"\n");    
//                 }
//                 System.out.println("*");

                OperationColaboration collabAut = new OperationColaboration();
                collabAut.setDataPerJurnal(arrAuthors, keywords, arrComb, arrCombVal, arrCombKey);
                OperationColaboration collabAff = new OperationColaboration();
                collabAff.setDataPerJurnal(arrAffiliations, keywords, arrCombAff, arrCombAffVal, arrCombAffKey);

            }

        } catch (SQLException es) {
            es.printStackTrace();
        }

        //         ArrayList<String> str = new ArrayList<String>();
//         str = Arrays.asList(str)
//        String str = "Aqbar, Kevien;Teharudin, Ridwan;Algani, Thomi";
//        List<String> items = Arrays.asList(str.split("\\s*;\\s*"));
//        System.out.println(items.get(1));
//      
//        //Java8
//        String[] myArray = items.stream().toArray(String[]::new);
//        System.out.println(myArray[2]);
//        
//        //Selain Java8
//        String[] stringArray = items.toArray(new String[0]);
//        System.out.println(stringArray[0]);
        int conval = 0; 
        for (int i = 0; i < arrComb.size(); i++) {  //Cetak kombinasi yang di dapat
//        for (int i = 16000; i < 16930; i++) {
//            System.out.println("Idx-" + i + " : " + arrComb.get(i)); //***BUKA
            if(arrComb.get(i).contains("Praptisih")){
                conval = conval+arrCombVal.get(i);
                System.out.println("Contains  'Praptisih' Idx-" +i+ "Jml di Idx: " +arrCombVal.get(i)+ " Jumlah : "+conval);
            }
        }
        System.out.println("\n");
//            Collections.sort(arrlistJml); Tetapi index arraynya tidak ikut ke sort (tdk ikut berubah)
//        for (int i = 0; i < arrCombVal.size(); i++) { //Cetak nilai yang didapat
//        for (int i = 16000; i < 16930; i++) {
//            System.out.println("Val Idx-" + i + " : " + arrCombVal.get(i)); //***BUKA
//        }
//        System.out.println("\n");
//        for (int i = 0; i < arrCombKey.size(); i++) {
//            System.out.println("Keyword ke-" + i + " : " + arrCombKey);
//        }



//        String[] stringArray = items.toArray(new String[0]);
//        System.out.println(stringArray[0]);
        for (int i = 0; i < arrCombAff.size(); i++) {  //Cetak kombinasi yang di dapat
            System.out.println("IdxAff-" + i + " : " + arrCombAff.get(i)); //***BUKA
        }
//        System.out.println("\n");
//            Collections.sort(arrlistJml); Tetapi index arraynya tidak ikut ke sort (tdk ikut berubah)
        for (int i = 0; i < arrCombAffVal.size(); i++) { //Cetak nilai yang didapat
            System.out.println("Val IdxAff-" + i + " : " + arrCombAffVal.get(i)); //***BUKA
        }
//        System.out.println("\n");
//        for (int i = 0; i < arrlistKeyword.size(); i++) {
//            System.out.println("Keyword ke-" + i + " : " + arrlistKeyword);
//        }



//
//
//
//
////////////////////////////////////////////////////////////////////////////////
//Tagging-Sorting-Filter 10 kolaborasi-Sublist-Persiapan Node dan Link json
//
         TaggingSortFilter tsf = new TaggingSortFilter();
         tsf.setData(arrComb, arrCombVal, arrCombKey, authortampil, sourcelisttampil, targetlisttampil, valuelisttampil, keywordlisttampil);
         
         TaggingSortFilter tsfAff = new TaggingSortFilter();
         tsfAff.setData(arrCombAff, arrCombAffVal, arrCombAffKey, affiliationtampilAff, sourcelisttampilAff, targetlisttampilAff, valuelisttampilAff, keywordlisttampilAff);

//        // Init the element list
//        List<Element> elements = new ArrayList<Element>();
//        for (int i = 0; i < arrCombVal.size(); i++) {
//            elements.add(new Element(i, arrCombVal.get(i))); //Buat elemen list sebanyak arrCombVal, indexnya, sama valuenya di tagging
//        }
//
//        // Sort and print
////        Collections.sort(elements); //Urutkan list elements, default ASC
////        Collections.reverse(elements); // Membalikkan urutan, jadi yang nilai besar pertama (DESC)
////        /* Sorting in decreasing order*/
//        Collections.sort(elements, Collections.reverseOrder());
//
//        // Sublist to List
//        List<Element> filter = new ArrayList<Element>(elements.subList(0, 10)); //Membatasi hanya 10 data teratas
//        System.out.println("\nHasil Limit 10  teratas");
//        System.out.println("NilDESC - IdxBef");
//        for (Element element : filter) {
//             //System.out.println(element.value + "      -  " + element.index);  //###BUKA
//            System.out.println("Value : "+ element.value + " || Idx-" + element.index);  //###BUKA
//            //Jalankan Method getConvert (persiapan data json. Rubah array ke node dan link
//            PreArrToJSON preArrToJSON = new PreArrToJSON();
//            preArrToJSON.getConvert(element.value, element.index, arrComb, arrCombKey,
//                    authortampil, sourcelisttampil, targetlisttampil, valuelisttampil, keywordlisttampil);
//        }
//
//
////////////////////////////////////////////////////////////////////////////////     
//
//
//
//
        //Cetak Node Name Author yang akan tampil di matriks
        System.out.println("\n\nNode Name Author : \n");
        //        for (String authtampil : authortampil) {  
        //            System.out.println("Name = " + authtampil);
        //        }
        for (int i = 0; i < authortampil.size(); i++) {
            System.out.println("Node-(" + i + ") : " + authortampil.get(i));
        }
        //Cetak Node (Source, Target, Value) yang akan tampil di matriks
//        System.out.println("\nSource   = " + String.valueOf(sourcelisttampil) + "\nTarget   = " + String.valueOf(targetlisttampil) + "\nValue    = " + String.valueOf(valuelisttampil));
        for (int x : sourcelisttampil){
            System.out.println("Source : " + String.valueOf(x));
        }
        for (int x : targetlisttampil){
            System.out.println("Target : " + String.valueOf(x));
        }
        for (int x : valuelisttampil){
            System.out.println("Value : " + String.valueOf(x));
        }
        
        
        //Cetak Keyword yang akan tampil di matriks
//        System.out.println("Keyword  = " + keywordlisttampil);
        for (String x : keywordlisttampil){
            System.out.println("Keyword : " + String.valueOf(x));
        }
        
//
//
        //Cetak Node Name Affiliation yang akan tampil di matriks
        System.out.println("\n\nNode Name Affiliation : \n");
        //        for (String authtampil : authortampil) {  
        //            System.out.println("Name = " + authtampil);
        //        }
        for (int i = 0; i < affiliationtampilAff.size(); i++) {
            System.out.println("Name(" + i + ") = " + affiliationtampilAff.get(i));
        }
        //Cetak Node (Source, Target, Value) yang akan tampil di matriks
//        System.out.println("\nSource   = " + String.valueOf(sourcelisttampilAff) + "\nTarget   = " + String.valueOf(targetlisttampilAff) + "\nValue    = " + String.valueOf(valuelisttampilAff));
        for (int x : sourcelisttampilAff){
            System.out.println("Source : " + String.valueOf(x));
        }
        for (int x : targetlisttampilAff){
            System.out.println("Target : " + String.valueOf(x));
        }
        for (int x : valuelisttampilAff){
            System.out.println("Value : " + String.valueOf(x));
        }
        //Cetak Keyword yang akan tampil di matriks
//        System.out.println("Keyword  = " + keywordlisttampilAff);
        for (String x : keywordlisttampilAff){
            System.out.println("Keyword : " + String.valueOf(x));
        }



//
//
//
//
//
//
        //Perhitungan Kemunculan Keyword
        CountSortKeyword csk = new CountSortKeyword();
        csk.setCountSortingKeyword(keywordlisttampil, keywordlisttampilfix);
        CountSortKeyword cskAff = new CountSortKeyword();
        cskAff.setCountSortingKeyword(keywordlisttampilAff, keywordlisttampilfixAff);

        //Cetak KeywordFix yang akan tampil di matriks
        System.out.println("KeywordF = " + keywordlisttampilfix);
        System.out.println("KeywordFAff = " + keywordlisttampilfixAff);
        
        //System.out.println("\nKeyword FIX 9 = " + keywordlisttampilfix.get(9));

        //Membuat file json
        ConvertJson cj = new ConvertJson();
        cj.setConvertJson(1, authortampil, sourcelisttampil, targetlisttampil, valuelisttampil, keywordlisttampilfix);
        ConvertJson cjAff = new ConvertJson();
        cjAff.setConvertJson(2, affiliationtampilAff, sourcelisttampilAff, targetlisttampilAff, valuelisttampilAff, keywordlisttampilfixAff);

        System.out.println("");

    }

//
//
//
//
////////////////////////////////////////////////////////////////////////////////

//
   

   

}
