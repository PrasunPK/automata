import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class TransitionFunctionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private State q0;
    private State q1;
    private State q2;
    private ArrayList<State> states;
    private TransitionTable transitionTable;
    private TransitionFunction transitionFunction;
    private ArrayList<String> input = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        q0 = new State("q0");
        q1 = new State("q1");
        q2 = new State("q2");
        states = new ArrayList<>();
        transitionTable = new TransitionTable();
        transitionFunction = new TransitionFunction();
    }

    @Test
    public void shouldValidateIfTransitionTableHasWrongStateThatDFADoesNotAccept() throws Exception {
        states.addAll(asList(q0, q1));

        Transition transitionFromQ0 = new Transition();
        transitionFromQ0.put("1", q1);
        transitionFromQ0.put("0", q0);
        Transition transitionFromQ1 = new Transition();
        transitionFromQ1.put("1", q1);
        transitionFromQ1.put("0", q0);
        transitionFromQ1.put("1", q2);

        transitionTable.put(q0, transitionFromQ0);
        transitionTable.put(q1, transitionFromQ1);

        thrown.expect(InvalidStateException.class);
        transitionFunction.validateStates(states, transitionTable);
    }

    @Test
    public void shouldTransitFromAStateToNextStateWithAValidInput() throws Exception {
        states.addAll(asList(q0, q1));

        Transition transitionFromQ0 = new Transition();
        transitionFromQ0.put("1", q1);
        transitionFromQ0.put("0", q0);
        Transition transitionFromQ1 = new Transition();
        transitionFromQ1.put("1", q1);
        transitionFromQ1.put("0", q0);

        transitionTable.put(q0, transitionFromQ0);
        transitionTable.put(q1, transitionFromQ1);

        input.addAll(asList("1", "0", "1"));
        State expectedFinalState = q1;

        State initialState = q0;
        State state = transitionFunction.transitToFinalState(initialState, input, transitionTable);
        assertThat(state.getName(), is(expectedFinalState.getName()));
    }

    @Test
    public void shouldValidateStateBasedOnTheTransitionTableAndStatesGivenToTheDFA() throws Exception {
        states.addAll(asList(q0, q1));
        Transition transitionFromQ0 = new Transition();
        transitionFromQ0.put("1", q1);
        transitionFromQ0.put("0", q0);
        Transition transitionFromQ1 = new Transition();
        transitionFromQ1.put("1", q1);
        transitionFromQ1.put("0", q0);

        transitionTable.put(q0, transitionFromQ0);
        transitionTable.put(q1, transitionFromQ1);

        input.addAll(asList("1", "0", "1"));

        TransitionFunction transitionFunction = new TransitionFunction();
        Boolean validation = transitionFunction.validateStates(states, transitionTable);
        assertTrue(validation);
    }


}