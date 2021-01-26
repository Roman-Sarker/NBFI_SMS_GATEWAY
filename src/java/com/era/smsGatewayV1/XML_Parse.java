package com.era.smsGatewayV1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.StringReader;
import org.xml.sax.InputSource;

public class XML_Parse {

    public Modal xmlToString(String xmlData){
        String vTtlAsString = null;
        String vUrl, vMsisdn, vSms, vUser, vPass, vSid, vCsmsid;
        Modal modal = new Modal();
        try {
/*            String xml = "<SMS_INFO>\n"
                    + "	<SMS_PARAM>\n"
                    + "		<URL>http://sms.sslwireless.com/pushapi/dynamic/server.php?</URL>\n"
                    + "		<msisdn>8801723374234</msisdn>\n"
                    + "		<sms>Test SMS For Postman GET Method</sms>\n"
                    + "		<user>Cvcflbulk</user>\n"
                    + "		<pass>cvcfL@26@20</pass>\n"
                    + "		<sid>CVCFLbulkbng</sid>\n"
                    + "		<csmsid>88484</csmsid>\n"
                    + "	</SMS_PARAM>\n"
                    + "</SMS_INFO>";
*/
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xmlData)));

            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            
            // Parse specific value to parameter
            vUrl = doc.getElementsByTagName("URL").item(0).getTextContent();
            vMsisdn = doc.getElementsByTagName("msisdn").item(0).getTextContent();
            vSms = doc.getElementsByTagName("sms").item(0).getTextContent();
            vUser = doc.getElementsByTagName("user").item(0).getTextContent();
            vPass = doc.getElementsByTagName("pass").item(0).getTextContent();
            vSid = doc.getElementsByTagName("sid").item(0).getTextContent();
            vCsmsid = doc.getElementsByTagName("csmsid").item(0).getTextContent();
            
            vTtlAsString = vUrl+"msisdn="+vMsisdn+"&sms="+vSms+"&user="+vUser+"&pass="+vPass+"&sid="+vSid+"&csmsid="+vCsmsid;
            //System.out.println("Param : "+vTtlAsString);  
            
            
            modal.setUser(vUser);
            modal.setPass(vPass);
            modal.setMblNo(vMsisdn);
            modal.setText(vSms);
            modal.setSid(vSid);
            modal.setSmsCod(vCsmsid);
            modal.setUrl(vUrl);
            modal.setTtlString(vTtlAsString);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    return modal;
    }
    public static void main(String argv[]) {
        
        XML_Parse xml_Parse = new XML_Parse();
        xml_Parse.xmlToString("a");
    }

}
