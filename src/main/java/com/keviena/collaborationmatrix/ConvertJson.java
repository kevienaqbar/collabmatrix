package com.keviena.collaborationmatrix;

import com.keviena.collaborationmatrix.KonversiObjJson.Link;
import com.keviena.collaborationmatrix.KonversiObjJson.Node;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.keviena.collaborationmatrix.InitCollaboration.lastexportimedb;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevien Aqbar
 */
public class ConvertJson {

    //Membuat string json dan Write file
    void setConvertJson(int jenis, ArrayList<String> authortampil, ArrayList<Integer> sourcelisttampil, ArrayList<Integer> targetlisttampil, ArrayList<Integer> valuelisttampil, ArrayList<String> keywordlisttampilfix) {

        Gson gson = new Gson();
        Result r = new Result();
        List<Node> listnode = new ArrayList();
        List<Link> listlink = new ArrayList();

        for (int i = 0; i < authortampil.size(); i++) {
            Node n = new Node();
            n.setName(authortampil.get(i));
            listnode.add(n);
        }
        for (int i = 0; i < sourcelisttampil.size(); i++) {
            Link link = new Link();
            link.setSource(sourcelisttampil.get(i));
            link.setTarget(targetlisttampil.get(i));
            link.setValue(valuelisttampil.get(i));
            link.setKeyword(keywordlisttampilfix.get(i));
            listlink.add(link);
        }

        r.setNodes(listnode);
        r.setLinks(listlink);
        r.setDatejson(String.valueOf(lastexportimedb));

        // used to pretty print result
        gson = new GsonBuilder().setPrettyPrinting().create();
        String strJson = gson.toJson(r);
//        //
//        String content = "Hello World !!";
//        Files.write(Paths.get("c:/output.txt"), content.getBytes());
//        //
//        //Get the file reference
//        Path path = Paths.get("C:/Users/User/Documents/NetBeansProjects/CollaborationMatrixRIN/src/main/webapp/resources/data/d1ata_auth_test.json");
//        //Use try-with-resource to get auto-closeable writer instance
//        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
//            writer.write(strJson);
//        }
//
//
        //Write file json
        FileWriter writer = null;
        try {
            if (jenis == 1) {
//                getServletContext().getRealPath("/")
//                writer = new FileWriter("E:\\ModulCollaborationMatrixRIN\\Coba3\\CollaborationMatrix\\src\\main\\webapp\\resources\\data\\data_auth.json");
                writer = new FileWriter("/usr/local/glassfish4/glassfish/domains/domain1/applications/dataverse-4.8.6/RIN/src/main/webapp/resources/data/data_auth.json");
            } else {
//                writer = new FileWriter("E:\\ModulCollaborationMatrixRIN\\Coba3\\CollaborationMatrix\\src\\main\\webapp\\resources\\data\\data_aff.json");
                writer = new FileWriter("/usr/local/glassfish4/glassfish/domains/domain1/applications/dataverse-4.8.6/RIN/src/main/webapp/resources/data/data_aff.json");
            }
//            writer = new FileWriter("/src/main/webapp/resources/data/data_aff_test.json");
            writer.write(strJson);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
