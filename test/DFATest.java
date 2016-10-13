import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFATest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private State q1;
    private State q0;
    private ArrayList<String> alphabets;
    private State q2;
    private State q3;
    private ArrayList<State> states;
    private ArrayList<State> finalState;
    private TransitionTable transitionTable;
    private Transition transitionFromQ0;
    private Transition transitionFromQ1;


    @Before
    public void setUp() throws Exception {

        q1 = new State("q1");
        q0 = new State("q0");
        alphabets = new ArrayList<>();

        q2 = new State("q2");
        q3 = new State("q3");

        states = new ArrayList<>();
        states.add(q0);
        states.add(q1);

        alphabets.add("0");
        alphabets.add("1");


        finalState = new ArrayList<>();
        finalState.add(q1);

        transitionTable = new TransitionTable();
        transitionFromQ0 = new Transition();
        transitionFromQ1 = new Transition();

        transitionFromQ0.put("1", q1);
        transitionFromQ0.put("0", q0);

        transitionFromQ1.put("1", q1);
        transitionFromQ1.put("0", q0);

        transitionTable.put(q0, transitionFromQ0);
        transitionTable.put(q1, transitionFromQ1);
    }

    @Test
    public void shouldReturnTrueIfTheMachineAcceptsTheGivenString() throws RuntimeException {
        DFA dfa = new DFA(states, alphabets, transitionTable, q0, finalState);

        ArrayList<String> inputString = new ArrayList<>();
        inputString.addAll(Collections.singletonList("1"));

        ArrayList<String> anotherInputString = new ArrayList<>();
        anotherInputString.addAll(Collections.singletonList("0"));

        ArrayList<String> stringThatSatisfies = new ArrayList<>();
        stringThatSatisfies.addAll(asList("0", "1", "1"));

        assertTrue(dfa.isLanguage(inputString));
        assertFalse(dfa.isLanguage(anotherInputString));
        assertTrue(dfa.isLanguage(stringThatSatisfies));
    }

    @Test
    public void shouldNotAcceptStringThatHasAlphabetNotSupportedByTheDFA() throws RuntimeException {
        DFA dfa = new DFA(states, alphabets, transitionTable, q0, finalState);
        ArrayList<String> inputString = new ArrayList<>();
        inputString.addAll(Collections.singletonList("2"));

        thrown.expect(InvalidAlphabetException.class);
        dfa.isLanguage(inputString);
    }

    @Test
    public void shouldThrowErrorWhenAnInvalidStateIsIncludeInTransitionTable() throws RuntimeException {
        Transition wrongTransitionFromQ1 = new Transition();
        wrongTransitionFromQ1.put("0", q2);
        transitionTable.put(q1,wrongTransitionFromQ1);

        DFA dfa = new DFA(states, alphabets, transitionTable, q0, finalState);
        ArrayList<String> inputString = new ArrayList<>();
        inputString.addAll(asList("1", "0", "1"));

        thrown.expect(InvalidStateException.class);
        dfa.isLanguage(inputString);
    }

    @Test
    public void shouldReturnTrueForAStringThatSatisfies() throws Exception {
        ArrayList<String> inputString = new ArrayList<>();
        inputString.addAll(asList("1", "0", "1"));
        DFA dfa = new DFA(states, alphabets, transitionTable, q0, finalState);
        assertTrue(dfa.isLanguage(inputString));
    }
}