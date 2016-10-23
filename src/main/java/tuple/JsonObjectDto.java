package tuple;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class JsonObjectDto {
    private String name;
    private String type;
    @SerializedName("tuple")
    private JsonObject tuple;
    @SerializedName("pass-cases")
    private String[] passCasses;
    @SerializedName("fail-cases")
    private String[] failCases;
}