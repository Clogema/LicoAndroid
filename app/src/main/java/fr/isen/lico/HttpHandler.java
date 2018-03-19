package fr.isen.lico;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler extends AsyncTask<String, Void, String> {

    private CallBackInterface callBackInterface;
    String resultat = "";

    public HttpHandler(CallBackInterface callBackInterfaceImplementation){
        this.callBackInterface = callBackInterfaceImplementation;
    }

    @Override
    protected String doInBackground(String... args) {
        URL webServiceUrl = null;
        StringBuilder result = new StringBuilder();

        try {
            webServiceUrl = new URL("https://lico-picolo.firebaseio.com/Picolo.json");
            HttpURLConnection urlConnection = (HttpURLConnection) webServiceUrl.openConnection();
            urlConnection.connect();

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = urlConnection.getInputStream();
                resultat = convertInputStreamToString(is);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    protected void onPostExecute(String resultat) {
        Log.d("WebServiceTask", resultat);
        if(resultat != null && !resultat.isEmpty()) {
            callBackInterface.success(resultat);
        }
        else {
            callBackInterface.error();
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuffer = new StringBuilder("");
        String readLine = bufferedReader.readLine();
        while(readLine != null){
            stringBuffer.append(readLine);
            stringBuffer.append("\n");
            readLine = bufferedReader.readLine();
        }
        inputStream.close();
        return stringBuffer.toString();
    }
}