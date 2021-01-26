
package com.era.smsGatewayV1;

/**
 *
 * @author Roman Sarker
 */
public class Modal {
    private String url,user,pass,sid,mblNo,text,smsCod;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    private String ttlString;

    public String getTtlString() {
        return ttlString;
    }

    public void setTtlString(String ttlString) {
        this.ttlString = ttlString;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSmsCod() {
        return smsCod;
    }

    public void setSmsCod(String smsCod) {
        this.smsCod = smsCod;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
