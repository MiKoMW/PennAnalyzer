import PennAnalyzer.FileIO;
import PennAnalyzer.PennAnalyzer;
import PennAnalyzer.PosSet;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mac on 2017/12/30.
 */
public class posDemo {

    public static void main(String[] args){

        ArrayList<String> input = new ArrayList<String>();

        //read input.
        try {
            input = FileIO.readLine("treebank.in");}
        catch(IOException e){
            e.printStackTrace();
        }

        // Invoke a pennanalyzer and transfer all grammars to PCFG.
        PennAnalyzer analyzer = new PennAnalyzer(input);
        PosSet set = analyzer.getAllPos();
        ArrayList<String> output = set.getOutput();

        //write output.
        try {
            FileIO.writeLine(output,"pos.out");}
        catch(IOException e){
            e.printStackTrace();
        }




    }

}
