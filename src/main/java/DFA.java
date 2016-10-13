import java.util.ArrayList;

class DFA {

    private final ArrayList<State> states;
    private final ArrayList<String> alphabets;
    private final TransitionTable transitionTable;
    private final State initialState;
    private final ArrayList<State> finalStates;

    DFA(ArrayList<State> states, ArrayList<String> alphabets, TransitionTable transitionTable, State initialState, ArrayList<State> finalStates) {

        this.states = states;
        this.alphabets = alphabets;
        this.transitionTable = transitionTable;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public boolean isLanguage(ArrayList<String> inputString) throws RuntimeException {
        TransitionFunction transitionFunction = new TransitionFunction();
        State finalState;
        if (isValid(inputString) && transitionFunction.validateStates(states, transitionTable)) {
            finalState = transitionFunction.transitToFinalState(initialState, inputString, transitionTable);
        } else {
            throw new InvalidAlphabetException();
        }
        return finalStates.contains(finalState);
    }

    private boolean isValid(ArrayList<String> inputString) {
        return alphabets.containsAll(inputString);
    }
}