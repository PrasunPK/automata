package tuple;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class JsonObject {
    @SerializedName("states")
    private final String[] states;
    @SerializedName("alphabets")
    private final String[] alphabets;
    @SerializedName("start-state")
    private final String initialState;
    @SerializedName("final-states")
    private final String[] finalStates;
    @SerializedName("delta")
    private final HashMap<String, HashMap> delta;

    public JsonObject(String[] states, String[] alphabets, String initialState, String[] finalStates, HashMap<String, HashMap> delta) {
        this.states = states;
        this.alphabets = alphabets;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.delta = delta;
    }
}
