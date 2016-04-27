package com.example.ryanj11_tech.appetite;

/**
 * Created by Jace on 4/27/2016.
 */
public class Reviews {
    private String id;
    private String content;
    private String reviewer;

    public Reviews(String id, String content, String reviewer)
    {
        this.setID(id);
        this.setContent(content);
        this.setReviewer(reviewer);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
