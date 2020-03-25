import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Wetter {
    private String Ort;

    public Wetter(String ort) throws IOException {
        Ort = ort;
    }

    String getTemp() throws IOException, JSONException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + Ort + "&units=metric&appid=ea83cbef0fe44057e30baa8b0741c8b1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject jo = new JSONObject(content.toString());
        JSONObject jom = jo.getJSONObject("main");
        double temperatur = jom.getDouble("temp");
        return temperatur + " \u2103";
    }
}