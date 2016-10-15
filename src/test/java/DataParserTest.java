import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataParserTest {
    @Test
    public void shouldReturnATransitionTableWithProperFormThatHasSameFinalStates() throws Exception {
        String jsonString = "{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]}";
        DataParser dataParser = new DataParser();
        State q1 = new State("q1");
        State q2 = new State("q2");

        JsonObject jsonObject = dataParser.parse(jsonString);

        Tuple tuple = dataParser.convertToFormat(jsonObject);
        assertThat(tuple.getInitialState().getName(), is(q1.getName()));
        assertThat(tuple.getFinalStates().get(0).getName(), is(q2.getName()));
    }
}