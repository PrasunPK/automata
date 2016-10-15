import lombok.AllArgsConstructor;
import lombok.Getter;

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
