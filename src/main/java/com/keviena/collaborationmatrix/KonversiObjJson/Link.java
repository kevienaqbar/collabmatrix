package com.keviena.collaborationmatrix.KonversiObjJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

@SerializedName("source")
@Expose
private Integer source;
@SerializedName("target")
@Expose
private Integer target;
@SerializedName("value")
@Expose
private Integer value;
@SerializedName("keyword")
@Expose
private String keyword;

public Integer getSource() {
return source;
}

public void setSource(Integer source) {
this.source = source;
}

public Integer getTarget() {
return target;
}

public void setTarget(Integer target) {
this.target = target;
}

public Integer getValue() {
return value;
}

public void setValue(Integer value) {
this.value = value;
}

public String getKeyword() {
return keyword;
}

public void setKeyword(String keyword) {
this.keyword = keyword;
}

}