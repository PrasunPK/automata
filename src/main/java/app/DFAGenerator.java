package app;

import com.google.gson.Gson;
import dfa.DFA;
import parser.DataParser;
import tuple.JsonObject;
import tuple.JsonObjectDto;
import tuple.Tuple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DFAGenerator {
    public static void main(String[] args) throws IOException {
        File file = new File("data/examples.json");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fileInputStream.read(data);
        String s = new String(data, "UTF-8");
        fileInputStream.close();

        DataParser dataParser = new DataParser();

        String withoutEscapeCharacter = dataParser.replaceCharacter(s, "\\\\");
        String substring = withoutEscapeCharacter.substring(1, withoutEscapeCharacter.length() - 1);

        Gson gson = new Gson();
        JsonObjectDto[] DfaDto = gson.fromJson(substring, JsonObjectDto[].class);

        for (JsonObjectDto dto : DfaDto) {
            System.out.println(dto.getType());
            System.out.println(dto.getName());
            JsonObject jsonObject = dto.getTuple();
            Tuple tuple = dataParser.convertToFormat(jsonObject);
            DFA dfa = new DFA(tuple.getStates(), tuple.getAlphabets(), tuple.getTransitionTable(), tuple.getInitialState(), tuple.getFinalStates());

            System.out.println("PASS CASES : ");
            ArrayList<String> passes = new ArrayList<>();
            Collections.addAll(passes, dto.getPassCasses());
            iterateOverCases(dfa, passes);

            System.out.println("FAIL CASES : ");
            ArrayList<String> failCases = new ArrayList<>();
            Collections.addAll(failCases, dto.getFailCases());
            iterateOverCases(dfa, failCases);
        }
    }


    private static void iterateOverCases(DFA dfa, ArrayList<String> failCases) {
        ArrayList<String> f = new ArrayList<>();
        for (String fail : failCases) {
            String[] split = fail.split("");
            Collections.addAll(f, split);
            if (dfa.isLanguage(f)) {
                System.out.println( "PASSED : "  + fail);
            } else {
                System.out.println( "FAILED : " + fail);
            }
        }
    }

}
