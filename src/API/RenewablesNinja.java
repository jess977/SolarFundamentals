package API;

import Deserializer.DIFDeserializer;
import Deserializer.GHIDeserializer;
import Deserializer.WeatherDeserializer;
import com.google.gson.GsonBuilder;
import javafx.util.Pair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RenewablesNinja implements APIRequestInterface {
    private static final String HOST = "https://www.renewables.ninja";
    private static final String[] ENDPOINTS = new String[] {"/api/limits", "/api/models", "/api/data/weather", "/api/data/pv"};
    private static final int LIMITS = 0, MODELS = 1, DATA_WEATHER = 2, DATA_PV = 3;

    private static String request(URL url) throws IOException {
        StringBuilder content = new StringBuilder();

        File file = new File(url.toString().replace("/","_"));
        if(file.isFile()) {
            Scanner sc = new Scanner(file);
            content.append(sc.next());
        } else {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "");
            con.setRequestProperty("Authorization", "Token 4c4f1f7518046e78fe9607924bfcd06cb9e8e49a");

            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Marcos: Request Failed! Probably because of request burst limit being 6 per minute");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            PrintWriter writer = new PrintWriter(url.toString().replace("/","_"), "UTF-8");

            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
                writer.println(line);
            }

            in.close();
            writer.close();
            con.disconnect();
        }
        return content.toString();
    }

    @Override
    public double[] getT_air() throws IOException {
        URL url = new URL(setParametersAndGetUri(ENDPOINTS[DATA_WEATHER]
                , new Pair<>("date_from","2014-01-01")
                , new Pair<>("date_to","2014-12-31")
                , new Pair<>("lon","30")
                , new Pair<>("lat","45")
                , new Pair<>("dataset","merra2")
                , new Pair<>("var_t2m","true")
                , new Pair<>("header","false")
                , new Pair<>("format","json")
        ));

        return new GsonBuilder()
                .registerTypeAdapter(double[].class, new WeatherDeserializer())
                .create()
                .fromJson(request(url), double[].class);
    }

    @Override
    public double[] getDIF() throws IOException {
        URL url = new URL(setParametersAndGetUri(ENDPOINTS[DATA_PV]
                , new Pair<>("local_time","true")
                , new Pair<>("lat","26.831281096292983")
                , new Pair<>("lon","4.726562500002411")
                , new Pair<>("date_from","2018-01-01")
                , new Pair<>("date_to","2018-12-31")
                , new Pair<>("dataset","merra2")
                , new Pair<>("capacity","1")
                , new Pair<>("system_loss","0.1")
                , new Pair<>("tracking","0")
                , new Pair<>("tilt","35")
                , new Pair<>("azim","180")
                , new Pair<>("raw","true")
                , new Pair<>("header","false")
                , new Pair<>("format","json")
        ));

        return new GsonBuilder()
                .registerTypeAdapter(double[].class, new DIFDeserializer())
                .create()
                .fromJson(request(url), double[].class);
    }

    @Override
    public double[] getGHI() throws IOException {
        URL url = new URL(setParametersAndGetUri(ENDPOINTS[DATA_PV]
                , new Pair<>("local_time","true")
                , new Pair<>("lat","26.831281096292983")
                , new Pair<>("lon","4.726562500002411")
                , new Pair<>("date_from","2018-01-01")
                , new Pair<>("date_to","2018-12-31")
                , new Pair<>("dataset","merra2")
                , new Pair<>("capacity","1")
                , new Pair<>("system_loss","0.1")
                , new Pair<>("tracking","0")
                , new Pair<>("tilt","0")
                , new Pair<>("azim","180")
                , new Pair<>("raw","true")
                , new Pair<>("header","false")
                , new Pair<>("format","json")
        ));

        return new GsonBuilder()
                .registerTypeAdapter(double[].class, new GHIDeserializer())
                .create()
                .fromJson(request(url), double[].class);
    }

    private static String setParametersAndGetUri(String endpoint, Pair<String,String>... params) {
        StringBuilder sb = new StringBuilder(HOST);
        sb.append(endpoint);
        sb.append("?");
        for(Pair<String,String> p : params) {
            sb.append(p.getKey());
            sb.append("=");
            sb.append(p.getValue());
            sb.append("&");
        }
        return sb.toString();
    }
}