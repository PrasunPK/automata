import java.util.ArrayList;

public class TransitionFunction {

    public Boolean validateStates(ArrayList<State> states, TransitionTable transitionTable) {
        for (State state : transitionTable.keySet()) {
            if (!states.contains(state) || !states.containsAll(transitionTable.get(state).values())) {
                throw new InvalidStateException();
            }
        }
        return true;
    }

    public State transitToFinalState(State initialState, ArrayList<String> inputStrings, TransitionTable transitionTable) {
        State currentState = initialState;
        for (String input : inputStrings) {
            Transition transition = transitionTable.get(currentState);
            currentState = transition.get(input);
        }
        return currentState;
    }
}
