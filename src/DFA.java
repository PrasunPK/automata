import java.util.ArrayList;

class DFA {

    private final ArrayList<State> states;
    private final ArrayList alphabets;
    private final TransitionTable transitionTable;
    private final State initialState;
    private final ArrayList<State> finalStates;

    DFA(ArrayList<State> states, ArrayList alphabets, TransitionTable transitionTable, State initialState, ArrayList<State> finalStates) {

        this.states = states;
        this.alphabets = alphabets;
        this.transitionTable = transitionTable;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public boolean isLanguage(ArrayList<String> inputString) throws RuntimeException {
        State currentState = initialState;
        for (String input : inputString) {
            currentState = transitToNextState(currentState, input);
        }
        return finalStates.contains(currentState);
    }

    private State transitToNextState(State currentState, String input) {
        if (isValid(input)) {
            Transition transition = getTransition(currentState);
            currentState = transition.get(input);
        } else {
            throw new InvalidAlphabetException();
        }
        return currentState;
    }

    private Transition getTransition(State currentState) {
        Transition transition = transitionTable.get(currentState);
        if (transition == null) {
            throw new InvalidStateException();
        }
        return transition;
    }

    private boolean isValid(String inputString) {
        return alphabets.contains(inputString);
    }
}
