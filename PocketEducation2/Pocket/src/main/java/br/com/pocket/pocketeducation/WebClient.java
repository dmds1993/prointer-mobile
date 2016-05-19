package br.com.pocket.pocketeducation;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by daniel on 19/05/16.
 */
public class WebClient {

    public String post (String json) {
        try {
            URL url = new URL("http://www.mocky.io/v2/573d3828370000670c4dccd3");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type","application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            PrintStream outPut = new PrintStream(connection.getOutputStream());
            outPut.println(json);

            connection.connect();
            Scanner scanner = new Scanner(connection.getInputStream());
            String reposta = scanner.next();
            return reposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
