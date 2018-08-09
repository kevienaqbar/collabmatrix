package com.keviena.collaborationmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
