package tuple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import state.State;
import transition.TransitionTable;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class Tuple {
    private final ArrayList<State> states;
    private final ArrayList<String> alphabets;
    private final TransitionTable transitionTable;
    private final State initialState;
    private final ArrayList<State> finalStates;
}
