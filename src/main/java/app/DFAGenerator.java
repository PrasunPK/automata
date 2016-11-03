package app;

import parser.CaseParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DFAGenerator {
    public static void main(String[] args) throws IOException {
        File file = new File("data/dfa_examples.json");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fileInputStream.read(data);
        String s = new String(data, "UTF-8");
        fileInputStream.close();

        CaseParser caseParser = new CaseParser();
        caseParser.parseCases(s);
    }

}
