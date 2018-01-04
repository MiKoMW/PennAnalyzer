import PennAnalyzer.FileIO;
import PennAnalyzer.GrammarSet;
import PennAnalyzer.PennAnalyzer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A demo for the analyzer.
 *
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class pcfgDemo {

    public static void main(String[] args){

        ArrayList<String> input = new ArrayList<String>();

        //read input.
        try {
        input = FileIO.readLine("treebank.in");}
        catch(IOException e){
            e.printStackTrace();
        }

        // Invoke a pennanalyzer and transfer all grammars to PCFG.
        PennAnalyzer penn = new PennAnalyzer(input);
        GrammarSet set = penn.getAllGram().toCNF().getPRGram();
        ArrayList<String> output = set.getOutput();

        //write output.
        try {
            FileIO.writeLine(output,"treebank.out");}
        catch(IOException e){
            e.printStackTrace();
        }

    }

}
