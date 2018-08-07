package com.keviena.collaborationmatrix;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Kevien Aqbar
 */

class OperationColaboration {

    static String keyword, arrAuthor[];
    ArrayList<String> arrComb, arrCombKey; // Deklarasi arraylist utama yang akan bertambah terus nilainya
    ArrayList<Integer> arrCombVal;

    public void setDataPerJurnal(String a[], String b, ArrayList<String> c, ArrayList<Integer> d, ArrayList<String> e) {
        this.arrAuthor = a;
        this.keyword = b;
        this.arrComb = c;
        this.arrCombVal = d;
        this.arrCombKey = e;

        //System.out.println("setdataperjurnal : " + AuthorJurnal[1]);
//         System.out.println("setdataperjurnal : " + KeywordJurnal);
        //Tentukan kombinasi 2 dari N (length AuthorJurnal)
        int r = 2;
        int n = arrAuthor.length;
        printCombination(arrAuthor, n, r, arrComb, arrCombVal, arrCombKey);
    }

    static void printCombination(String[] arrAuthor, int n, int r, ArrayList<String> arrlist, ArrayList<Integer> arrlistJml, ArrayList<String> arrlistKeyword) {
        // Sort arrAuthor secara ASC (agar Amin idx0, Budi idx1)
        Arrays.sort(arrAuthor);

        // Array sementara untuk menyimpan semua kombinasi satu per satu
        String data[] = new String[r]; //Panjang array data[] adalah 2, String[2]

        // Cetak semua kombinasi menggunakan array sementara 'data[]' 
        combinationUtil(arrAuthor, data, 0, n - 1, 0, r, arrlist, arrlistJml, arrlistKeyword); // n-1 ---> end
    }

    /* 
    arrAuthor[]  ---> Input Array Author (idx0,idx1,idx2,...)
    data[] ---> Array sementara untuk menyimpan kombinasi saat ini
    start & end ---> Memulai dan Mengakhiri indeks dalam arrAuthor[]
    index  ---> Indeks saat ini dalam data[]
    r ---> Ukuran kombinasi yang akan dicetak, yaitu 2 */
    static void combinationUtil(String[] arrAuthor, String data[], int start,
            int end, int index, int r, ArrayList<String> arrComb, ArrayList<Integer> arrCombVal, ArrayList<String> arrCombKey) {

        String tempComb = "";

        // Kombinasi saat ini siap untuk dicetak, cetak
        if (index == r) { // 0 = 0
            for (int j = 0; j < r; j++) { //g ---> hasil looping Budi~~Anto, Budi~~Rina, Rina~~Anto
//                System.out.print(data[j] + " ");
                if (tempComb == "") {
                    tempComb = data[j];
                } else {
                    tempComb = tempComb + " ~ " + data[j];  
                }
            }

//            System.out.println("\nGab:" + g);

            //Cek jika arrlist SUDAH ADA Gabungan Kombinasi A1 ~~ A2 sebelumnya
            //Maka increment nilai arrlistJml tsb berdasarkan index yg ditemukan
            if (arrComb.contains(tempComb)) {
                int idxArrComb = arrComb.indexOf(tempComb);
//                System.out.println("Update nilai ke- " + retval);
                arrCombVal.set(idxArrComb, arrCombVal.get(idxArrComb) + 1); //Jika ditemukan, maka tambahkan nilai jumlah kolaboraasinya, kolaborasi++
//                for (int i = 0; i < arrComb.size(); i++) {
////                    System.out.println("Elemen ke-" + i + " : " + arrComb.get(i));
//                }
//                for (int i = 0; i < arrCombVal.size(); i++) {
////                    System.out.println("Nilai ke-" + i + " : " + arrCombVal.get(i));
//                }
                //Jika ditemukan, maka tambahkan keywordnya
                if (arrCombKey.get(idxArrComb) == "") {  //Jika isi arraylistKeyword pada index yg ditemukan Bernilai Kosong
                    arrCombKey.set(idxArrComb, keyword);  // Ini agar isi array tetap dikosong saja, lalu ditambah keyword baru
                } else {
                    arrCombKey.set(idxArrComb, arrCombKey.get(idxArrComb) + ";" + keyword);  //Jika sudah ada keyword sebelumnya, maka ditambahkan keyword baru
                }
//                System.out.println("KEYWORD DITAMBAHKAN YANG SUDAH ADA : " + KeywordJurnal);
//
            } else {      //Jika arrlist BELUM ADA Gabungan Kombinasi A1 ~~ A2, maka tambah baru ADD ke arrlist idx terakhir
                arrComb.add(tempComb);
                arrCombVal.add(1);
                //Save Add New keyword
                //Jika ada yg sama gak usah tambah lagi
//                if(arrlistKeyword.indexOf(KeywordJurnal) == -1){arrlistKeyword.add(KeywordJurnal);}
   
                arrCombKey.add(keyword);
//                System.out.println("KEYWORD DITAMBAHKAN BARU: " + arrlistKeyword);
//                System.out.println("Panjang deret : " + arrlist.size());
//                System.out.println("Panjang deret nilai : " + arrlistJml.size());
//            for (String value : arrlist) {
//                System.out.println("Value = " + value);
//            }
//                for (int i = 0; i < arrComb.size(); i++) {
////                    System.out.println("Elemen ke-" + i + " : " + arrlist.get(i));
//                }
//                for (int i = 0; i < arrCombVal.size(); i++) {
////                    System.out.println("Nilai ke-" + i + " : " + arrlistJml.get(i));
//                }
//                System.out.println("");
            }
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arrAuthor[i];
            combinationUtil(arrAuthor, data, i + 1, end, index + 1, r, arrComb, arrCombVal, arrCombKey);
        }
    }

}
