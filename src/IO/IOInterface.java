package IO;

import DTO.InputDTO;

public interface IOInterface {
    InputDTO read();
    double read(String req);
    void write(Object str);
}