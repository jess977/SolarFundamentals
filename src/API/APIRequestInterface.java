package API;

import java.io.IOException;

public interface APIRequestInterface {
    double[] getT_air() throws IOException;
    double[] getDIF() throws IOException;
    double[] getGHI() throws IOException;
}