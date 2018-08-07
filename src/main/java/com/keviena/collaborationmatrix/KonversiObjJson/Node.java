package com.keviena.collaborationmatrix.KonversiObjJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Node {

@SerializedName("name")
@Expose
private String name;
@SerializedName("group")
@Expose
private Integer group;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getGroup() {
return group;
}

public void setGroup(Integer group) {
this.group = group;
}

}