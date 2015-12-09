/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Adria
 */
public class Pr09 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        if (args[0].equals("GET")){
            String url = args[1];
                sendGET(url);
        }
        else if (args[0].equals("POST")){
            String url = args[3];
            String texto = args[2];
                sendPOST(url, texto);     
        }
        else if (args[0].equals("PUT")){
            String url = args[3];
            String texto = args[2];
                sendPUT(url, texto); 
        }
        else if (args[0].equals("DELETE")){
            String url = args[1];
                sendDELETE(url);     
        }
    }
 
    private static void sendGET(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Nombre Navegador: FelipAdria");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {             StringBuilder response;
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            }
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
    }
 
    private static void sendPOST(String url, String texto) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Nombre Navegador: FelipAdria");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            os.write(texto.getBytes());
            os.flush();
        }
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { 
            StringBuffer response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
    
    private static void sendPUT(String url, String texto) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection httpCon = (HttpURLConnection) obj.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        try (OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream())) {
            out.write(texto);
        }
        httpCon.getInputStream();
    }
    
    private static void sendDELETE (String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) obj.openConnection();
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.getResponseCode();
            System.out.println("DELETE request not worked");
        } catch (IOException exception) {
        } finally {         
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } 
    } 
}
