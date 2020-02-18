package Deserializer;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;

public class DIFDeserializer implements JsonDeserializer<double[]> {

    @Override
    public double[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        double[] DIF = new double[jsonElement.getAsJsonObject().size()];
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
            DIF[i++] = entry.getValue().getAsJsonObject().get("irradiance_diffuse").getAsDouble();
        }
        return DIF;
    }
}