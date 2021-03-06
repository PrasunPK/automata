package parser;

import com.google.gson.Gson;
import state.State;
import transition.Transition;
import transition.TransitionTable;
import tuple.JsonObject;
import tuple.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Arrays.asList;

public class DataParser {


    public JsonObject parse(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JsonObject.class);
    }

    public String replaceCharacter(String jsonString, String charToReplace) {
        jsonString = jsonString.replaceAll(charToReplace, "");
        return jsonString;
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
        for (String inout : delta.keySet()) {
            State state = new State(inout);

            HashMap hashMap = delta.get(inout);
            Transition transition = new Transition();

            for (Object transitionInput : hashMap.keySet()) {
                transition.put((String) transitionInput, new State((String) hashMap.get(transitionInput)));
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
