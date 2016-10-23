import org.junit.Test;
import parser.DataParser;
import state.State;
import transition.TransitionTable;
import tuple.JsonObject;
import tuple.Tuple;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataParserTest {
    @Test
    public void shouldReturnATransitionTableWithProperFormThatHasSameFinalStates() throws Exception {
        String jsonString = "{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]}";
        DataParser dataParser = new DataParser();
        State q1 = new State("q1");
        State q2 = new State("q2");

        dataParser.replaceCharacter(jsonString, "\\\\");
        JsonObject jsonObject = dataParser.parse(jsonString);

        Tuple tuple = dataParser.convertToFormat(jsonObject);
        assertThat(tuple.getInitialState().getName(), is(q1.getName()));
        assertThat(tuple.getFinalStates().get(0).getName(), is(q2.getName()));
        assertThat(tuple.getAlphabets(), is(asList("1", "0")));
        assertThat(tuple.getStates().get(0).getName(), is(q1.getName()));
        assertThat(tuple.getTransitionTable().getClass().getCanonicalName(), is(TransitionTable.class.getCanonicalName()));
    }

    @Test
    public void shouldParseTheWholeStringAndSeparateTheNameTypeTupleAndDelta() throws Exception {
        String inputString = "[{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]";
        DataParser dataParser = new DataParser();
        String s = dataParser.replaceCharacter(inputString, "\\\\");
        System.out.println(s);
    }
}