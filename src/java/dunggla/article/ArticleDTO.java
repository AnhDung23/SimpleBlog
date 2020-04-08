/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.article;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class ArticleDTO implements Serializable{
    private String title;
    private String shortDescription;
    private String content;
    private String author;
    private Timestamp postingDate;
    private String status;
    private String oldStatus;

    public ArticleDTO() {
    }

    public ArticleDTO(String title, String shortDescription, String content, String author, Timestamp postingDate, String status) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.author = author;
        this.postingDate = postingDate;
        this.status = status;
    }

    public ArticleDTO(String title, String shortDescription, String content, String author, Timestamp postingDate, String status, String oldStatus) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.author = author;
        this.postingDate = postingDate;
        this.status = status;
        this.oldStatus = oldStatus;
    }

    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the postingDate
     */
    public Timestamp getPostingDate() {
        return postingDate;
    }

    /**
     * @param postingDate the postingDate to set
     */
    public void setPostingDate(Timestamp postingDate) {
        this.postingDate = postingDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the oldStatus
     */
    public String getOldStatus() {
        return oldStatus;
    }

    /**
     * @param oldStatus the oldStatus to set
     */
    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }
    
}
