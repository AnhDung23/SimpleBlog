/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationError implements Serializable{
    private String emailSyntaxError;
    private String emailLengthError;
    private String emailExistedError;
    private String nameLengthError;
    private String passwordLengthError;
    private String confirmNotMatchError;

    public RegistrationError() {
    }

    public boolean checkSyntaxEmail(String email){
        return email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?");
    }
    /**
     * @return the emailSyntaxError
     */
    public String getEmailSyntaxError() {
        return emailSyntaxError;
    }

    /**
     * @param emailSyntaxError the emailSyntaxError to set
     */
    public void setEmailSyntaxError(String emailSyntaxError) {
        this.emailSyntaxError = emailSyntaxError;
    }

    /**
     * @return the emailLengthError
     */
    public String getEmailLengthError() {
        return emailLengthError;
    }

    /**
     * @param emailLengthError the emailLengthError to set
     */
    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    /**
     * @return the emailExistedError
     */
    public String getEmailExistedError() {
        return emailExistedError;
    }

    /**
     * @param emailExistedError the emailExistedError to set
     */
    public void setEmailExistedError(String emailExistedError) {
        this.emailExistedError = emailExistedError;
    }

    /**
     * @return the nameLengthError
     */
    public String getNameLengthError() {
        return nameLengthError;
    }

    /**
     * @param nameLengthError the nameLengthError to set
     */
    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmNotMatchError
     */
    public String getConfirmNotMatchError() {
        return confirmNotMatchError;
    }

    /**
     * @param confirmNotMatchError the confirmNotMatchError to set
     */
    public void setConfirmNotMatchError(String confirmNotMatchError) {
        this.confirmNotMatchError = confirmNotMatchError;
    }
    
    
}
