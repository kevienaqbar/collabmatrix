package com.keviena.collaborationmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import javax.json.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevien Aqbar
 */

public class SetNodesLinks {
    // Algoritma Array to data JSON (Tentukan Node 
// 1. Dalam perulangan FOR {
//        a. Ambil JmlCollaborasinya (element.value) & IndexBefore (element.index) dari setiap baris 'element'.
//           System.out.println(arrlist.get(element.index));
//           List<String> items2 = Arrays.asList(arrlist.get(element.index).split("\\s~~\\s*"));
//           System.out.println("Ambil Kiri : (" + items2.get(0)+")");
//           System.out.println("Ambil Kanan: (" + items2.get(1)+")");
//        b. Cek di arraylist 'authortampil' jika nmAuthor authortampil.indexOf(item2.get(0)) TIDAK ADA?
//                T : Add nmAuthor item2.get(0) di arraylist 'authortampil' idx0
//                    Cek juga collab partenernya nmAuthorP, authortampil.indexOf(item2.get(1)) TIDAK ADA?
//                        T: Add nmAuthorP item2.get(1) di arraylist.next 'authortampil' idx1 
//           Buat link antar author:
//                Add nilai index authortampil.indexOf(items2.get(0)) -> ke arraylist 'sourcelisttampil'
//                Add nilai index authortampil.indexOf(items2.get(1)) -> ke arraylist 'targetlisttampil'
//                Add nilai jmlcollab -> ke arraylist 'valuelisttampil'

    int index, value;
    ArrayList<String> arrlist;
    ArrayList<String> arrlistKeyword;
    ArrayList<String> authortampil;
    ArrayList<Integer> sourcelisttampil;
    ArrayList<Integer> targetlisttampil;
    ArrayList<Integer> valuelisttampil;
    ArrayList<String> keywordlisttampil;

    public void getConvert(int value, int index, ArrayList<String> arrComb, ArrayList<String> arrCombKey,
            ArrayList<String> authortampil, ArrayList<Integer> sourcelisttampil,
            ArrayList<Integer> targetlisttampil, ArrayList<Integer> valuelisttampil, ArrayList<String> keywordlisttampil) {

        this.value = value;
        this.index = index;
        this.arrlist = arrComb;
        this.arrlistKeyword = arrCombKey;
        this.authortampil = authortampil;
        this.sourcelisttampil = sourcelisttampil;
        this.targetlisttampil = targetlisttampil;
        this.valuelisttampil = valuelisttampil;
        this.keywordlisttampil = keywordlisttampil;

//        System.out.println("Method get Convert dijalankan " + index + " ~~ " + value); //###BUKA
//        System.out.println(arrComb.get(index)); //###BUKA
        //Membagi Author Kiri dan Kanan  (Regex Split)
//        List<String> items = Arrays.asList(arrComb.get(index).split("\\s~~\\s*")); ///Ini aslinya
        String[] items = arrComb.get(index).split("\\s~\\s*");
        // Selain Java8 pake dibawah ini
        //String[] stringArray2 = items2.toArray(new String[0]);
        //System.out.println("Ambil Kanan : " + stringArray2[1]);
//        System.out.println("Ambil Kiri : (" + items.get(0) + ")"); //Nanti dihapus //###BUKA
//        System.out.println("Ambil Kanan: (" + items.get(1) + ")"); //Nanti dihapus //###BUKA

//        if (authortampil.indexOf(items.get(0)) == -1) { //Jika pada array authortampil belum ada namanya, maka add
//            authortampil.add(items.get(0));
//        }
//        if (authortampil.indexOf(items.get(1)) == -1) { //Jika pada array authortampil belum ada namanya, maka add pada index selanjutnya lastindex
//            authortampil.add(items.get(1));
//        }
        
        if (authortampil.indexOf(items[0]) == -1) { //Jika pada array authortampil belum ada namanya, maka add
            authortampil.add(items[0]);
        }
        if (authortampil.indexOf(items[1]) == -1) { //Jika pada array authortampil belum ada namanya, maka add pada index selanjutnya lastindex
            authortampil.add(items[1]);
        }
//        sourcelisttampil.add(authortampil.indexOf(items.get(0)); //Tambahkan source link item
//        targetlisttampil.add(authortampil.indexOf(items.get(1))); //Tambahkan target link item
        
        sourcelisttampil.add(authortampil.indexOf(items[0])); //Tambahkan source link item
        targetlisttampil.add(authortampil.indexOf(items[1])); //Tambahkan target link item
        valuelisttampil.add(value); //Tambahkan value
        keywordlisttampil.add(arrCombKey.get(index)); //Tambahkan keyword 
        

    }

}
