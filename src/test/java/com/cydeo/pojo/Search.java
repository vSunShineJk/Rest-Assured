package com.cydeo.pojo;

import java.util.List;

public class Search {
    private List<Spartan> content;

    private int totalElement;

    public List<Spartan> getContent() {
        return content;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }
}
