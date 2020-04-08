/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.article;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ArticleError implements Serializable{
    private String titleIsExisted;
    private String titleLengthErr;
    private String shortDescriptionLengthErr;
    private String contentFullFieldErr;

    public ArticleError() {
    }

    /**
     * @return the titleIsExisted
     */
    public String getTitleIsExisted() {
        return titleIsExisted;
    }

    /**
     * @param titleIsExisted the titleIsExisted to set
     */
    public void setTitleIsExisted(String titleIsExisted) {
        this.titleIsExisted = titleIsExisted;
    }

    /**
     * @return the titleLengthErr
     */
    public String getTitleLengthErr() {
        return titleLengthErr;
    }

    /**
     * @param titleLengthErr the titleLengthErr to set
     */
    public void setTitleLengthErr(String titleLengthErr) {
        this.titleLengthErr = titleLengthErr;
    }

    /**
     * @return the shortDescriptionLengthErr
     */
    public String getShortDescriptionLengthErr() {
        return shortDescriptionLengthErr;
    }

    /**
     * @param shortDescriptionLengthErr the shortDescriptionLengthErr to set
     */
    public void setShortDescriptionLengthErr(String shortDescriptionLengthErr) {
        this.shortDescriptionLengthErr = shortDescriptionLengthErr;
    }

    /**
     * @return the contentFullFieldErr
     */
    public String getContentFullFieldErr() {
        return contentFullFieldErr;
    }

    /**
     * @param contentFullFieldErr the contentFullFieldErr to set
     */
    public void setContentFullFieldErr(String contentFullFieldErr) {
        this.contentFullFieldErr = contentFullFieldErr;
    }
    
    
}
