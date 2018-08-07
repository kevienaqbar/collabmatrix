package com.keviena.collaborationmatrix;

/**
 *
 * @author Kevien Aqbar
 */

public class ElementTag implements Comparable<ElementTag> {

    int index, value;

    ElementTag(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int compareTo(ElementTag e) {
        return this.value - e.value;
    }
}
