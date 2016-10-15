import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Arrays.asList;

public class DataParser {


    public JsonObject parse(String jsonString) {
        Gson gson = new Gson();
        jsonString = jsonString.replaceAll("\\\\", "");
        return gson.fromJson(jsonString, JsonObject.class);
    }

    public Tuple convertToFormat(JsonObject jsonObject) {
        ArrayList<State> states = new ArrayList<>();
        ArrayList<String> alphabets = new ArrayList<>(asList(jsonObject.getAlphabets()));
        TransitionTable transitionTable = new TransitionTable();
        State initialState = new State(jsonObject.getInitialState());
        ArrayList<State> finalStates = new ArrayList<>();

        convertAccordingly(jsonObject, states, transitionTable, finalStates);

        return new Tuple(states, alphabets, transitionTable, initialState, finalStates);
    }

    private void convertAccordingly(JsonObject jsonObject, ArrayList<State> states, TransitionTable transitionTable, ArrayList<State> finalStates) {
        convertToStates(states, jsonObject.getStates());
        convertToStates(finalStates, jsonObject.getFinalStates());
        convertToTransitionTable(transitionTable, jsonObject.getDelta());
    }

    private void convertToTransitionTable(TransitionTable transitionTable, HashMap<String, HashMap> delta) {
        for (String s : delta.keySet()) {
            State state = new State(s);

            HashMap hashMap = delta.get(s);
            Transition transition = new Transition();

            for (Object o2 : hashMap.keySet()) {
                transition.put((String) o2, new State((String) hashMap.get(o2)));
            }
            transitionTable.put(state, transition);
        }
    }

    private void convertToStates(ArrayList<State> states, String[] parsedStates) {
        for (String parsedState : parsedStates) {
            states.add(new State(parsedState));
        }
    }
}