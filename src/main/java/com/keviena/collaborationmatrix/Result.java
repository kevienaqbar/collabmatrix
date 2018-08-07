
package com.keviena.collaborationmatrix;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.keviena.collaborationmatrix.KonversiObjJson.Link;
import com.keviena.collaborationmatrix.KonversiObjJson.Node;

public class Result {

@SerializedName("datejson")
@Expose
private String datejson;
@SerializedName("nodes")
@Expose
private List<Node> nodes = null;
@SerializedName("links")
@Expose
private List<Link> links = null;

public String getDatejson() {
return datejson;
}

public void setDatejson(String datejson) {
this.datejson = datejson;
}

public List<Node> getNodes() {
return nodes;
}

public void setNodes(List<Node> nodes) {
this.nodes = nodes;
}

public List<Link> getLinks() {
return links;
}

public void setLinks(List<Link> links) {
this.links = links;
}

}