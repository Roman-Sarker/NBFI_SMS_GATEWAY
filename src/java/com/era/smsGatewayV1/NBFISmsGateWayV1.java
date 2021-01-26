package com.era.smsGatewayV1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Roman Sarker
 */
@WebServlet(name = "NBFISmsGateWayV1", urlPatterns = {"/NBFISmsGateWayV1"})
public class NBFISmsGateWayV1 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NBFISmsGateWayV1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NBFISmsGateWayV1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String sslResponse = null;
        //String param = "msisdn=8801791709161&sms=SMS body&user=Cvcflbulk&pass=Cvcfl2020&sid=CVCFLbulkbng&csmsid=123456789";

        //1** Receive xml parameter
        String xmlReqParam = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8")).lines().collect(
                Collectors.joining("\n"));
        System.out.println("xmlReqParam : " + xmlReqParam);
        if (xmlReqParam == null || xmlReqParam.trim().length() == 0) {
            System.out.println("Request data empty");
        }

        //2. Parse XML
        XML_Parse xml_Parse = new XML_Parse();
        //String ParamStr = xml_Parse.xmlToString(xmlReqParam);
        Modal modal = xml_Parse.xmlToString(xmlReqParam);
        System.out.println("URL : " + modal.getUrl());
        System.out.println("User : " + modal.getUser());
        System.out.println("Pass : " + modal.getPass());
        System.out.println("Text : " + modal.getText());
        System.out.println("Sid : " + modal.getSid());
        System.out.println("Mobile No : " + modal.getMblNo());
        System.out.println("Sms Id : " + modal.getSmsCod());

        //3. API Call
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        //RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"user\"\r\n\r\nCvcflbulk\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"pass\"\r\n\r\ncvcfL@26@20\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sid\"\r\n\r\nCVCFLbulkbng\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sms[0][0]\"\r\n\r\n8801791709161\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sms[0][1]\"\r\n\r\nকরোনায় আর্থসামাজিক\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sms[0][2]\"\r\n\r\n123456789\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        RequestBody body
                = RequestBody.create(mediaType,
                        "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"user\"\r\n\r\n" + modal.getUser() + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"pass\"\r\n\r\n" + modal.getPass() + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sid\"\r\n\r\n" + modal.getSid() + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sms[0][0]\"\r\n\r\n" + modal.getMblNo() + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sms[0][1]\"\r\n\r\n" + modal.getText() + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"sms[0][2]\"\r\n\r\n" + modal.getSmsCod() + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url(modal.getUrl()) //"http://sms.sslwireless.com/pushapi/dynamic/server.php"
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        sslResponse = response.body().string();
        System.out.println("Response : " + sslResponse);

        //4. Response
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/xml");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().print(sslResponse.toString());
        res.getWriter().flush();

    }

}
