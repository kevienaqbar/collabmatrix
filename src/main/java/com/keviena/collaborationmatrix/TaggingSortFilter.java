package com.keviena.collaborationmatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kevien Aqbar
 */

public class TaggingSortFilter {

    void setData(ArrayList<String> arrComb, ArrayList<Integer> arrCombVal, ArrayList<String> arrCombKey, ArrayList<String> authortampil, ArrayList<Integer> sourcelisttampil, ArrayList<Integer> targetlisttampil, ArrayList<Integer> valuelisttampil, ArrayList<String> keywordlisttampil) {
        // Init the element list
        List<ElementTag> elements = new ArrayList<ElementTag>();
        for (int i = 0; i < arrCombVal.size(); i++) {
            elements.add(new ElementTag(i, arrCombVal.get(i))); //Buat elemen list sebanyak arrCombVal, indexnya, sama valuenya di tagging
        }

        // Sort and print
//        Collections.sort(elements); //Urutkan list elements, default ASC
//        Collections.reverse(elements); // Membalikkan urutan, jadi yang nilai besar pertama (DESC)
//        /* Sorting in decreasing order*/
        Collections.sort(elements, Collections.reverseOrder());

        // Sublist to List
        List<ElementTag> filter = new ArrayList<ElementTag>(elements.subList(0, 10)); //Membatasi hanya 10 data teratas
        System.out.println("\nHasil Limit 10  teratas");
        System.out.println("NilDESC - IdxBef");
        for (ElementTag element : filter) {
            //System.out.println(element.value + "      -  " + element.index);  //###BUKA
            System.out.println("Value : " + element.value + " || Idx-" + element.index);  //###BUKA
            //Jalankan Method getConvert (persiapan data json. Rubah array ke node dan link
            SetNodesLinks preArrToJSON = new SetNodesLinks();
            preArrToJSON.getConvert(element.value, element.index, arrComb, arrCombKey,
                    authortampil, sourcelisttampil, targetlisttampil, valuelisttampil, keywordlisttampil);
        }
    }
}
