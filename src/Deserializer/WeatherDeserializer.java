package Deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

public class WeatherDeserializer implements JsonDeserializer<double[]> {

    @Override
    public double[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        double[] airTemperature = new double[jsonElement.getAsJsonObject().size()];
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
            airTemperature[i++] = entry.getValue().getAsJsonObject().get("temperature").getAsDouble();
        }
        return airTemperature;
    }
}