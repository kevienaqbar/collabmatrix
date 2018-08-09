package com.keviena.collaborationmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kevien Aqbar
 */

public class CountSortKeyword {
     //Keyword Count-Sort-ToOneString-Addto keywordlisttampilfix
     void setCountSortingKeyword(ArrayList<String> keywordlisttampil, ArrayList<String> keywordlisttampilfix) {

        for (int i = 0; i < keywordlisttampil.size(); i++) {
            //Creating wordCountMap which holds words as keys and their occurrences as values
            HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
            //Keywordlisttampil ada 10 Idx List String
            List<String> tmp = Arrays.asList(keywordlisttampil.get(i).split("\\s*;\\s*"));
            //splitting the currentLine into words
            String[] tmp2 = tmp.toArray(new String[0]);
            //RemoveNullValue Keyword dari array (Khusus Java 8)
            tmp2 = Arrays.stream(tmp2)
                    .filter(s -> (s != null && s.length() > 0))
                    .toArray(String[]::new);

            //Iterating each word
            for (String word : tmp2) {
                //if word is already present in wordCountMap, updating its count
                if (wordCountMap.containsKey(word)) {
                    wordCountMap.put(word, wordCountMap.get(word) + 1);
                } //otherwise inserting the word as key and 1 as its value
                else {
                    wordCountMap.put(word, 1);
                }
            }

            //Getting all the entries of wordCountMap in the form of Set
            Set<Map.Entry<String, Integer>> entrySet = wordCountMap.entrySet();
            //Creating a List by passing the entrySet
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entrySet);
            //Sorting the list in the decreasing order of values 
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    return (e2.getValue().compareTo(e1.getValue()));
                }
            });

            String kalimattmp = "";

            for (Map.Entry<String, Integer> entry : list) {  //Cetak semua entrynya
                if (entry.getValue() >= 1) {
                    kalimattmp = kalimattmp + entry.getKey() + " {" + entry.getValue() + "} | "; //Gabung entrynya, jadi satu string    
                }
            }
//            int ij=0; //ij Sudah membatasi hanya 10 keyword saja
//            for (Entry<String, Integer> entry : list) {  //Cetak semua entrynya 
//                if (entry.getValue() >= 1 && ij<10) {
//                    kalimattmp = kalimattmp + entry.getKey() + " {" + entry.getValue() + "} | "; //Gabung entrynya, jadi satu string    
//                }
//                ij++;
//            }
//            System.out.print(kalimattmp + "-----------------");
            System.out.print("Idx-" + (i) + " || Keyword : " +kalimattmp +"-");
            keywordlisttampilfix.add(kalimattmp);  //Tiap string dimasukkan ke keywordlisttampilfix
        }
    }
}
