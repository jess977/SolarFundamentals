package DTO;

import com.google.gson.annotations.SerializedName;

public class ModelDTO {
    public String id;
    public String name;
    public String default_dataset;
    public String dataset_help;
    public Dataset[] datasets;
    public class Dataset {
        public String id;
        public String name;
        public String[] daterange;
        public int[] bounds;
    }
    public String license_url;
    public String license_name;
    public String citation_url;
    public String citation_name;
    public String max_daterange;
    public String version;
    public Field[] fields;
    public class Field {
        public String id;
        public String name;
        public String help;
        public String type;
        public int min;
        public int max;
        public String dropdown_type;
        public Option[] options;
        public class Option {
            public String value;
            public String label;
        }
        public @SerializedName("default") String _default;
        public boolean optional;
    }
}