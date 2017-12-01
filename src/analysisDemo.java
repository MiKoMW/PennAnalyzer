import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mac on 2017/11/30.
 */
public class analysisDemo {

    public static void main(String[] args){


        ArrayList<String> input = new ArrayList<String>();

        try {
        input = FileIO.readLine(args[0]);}
        catch(IOException e){
            e.printStackTrace();
        }
        PennAnalyzer penn = new PennAnalyzer(input);
        GrammarSet set = penn.getAllGram().toCNF();
        PRGrammarSet prset = set.getPRGram();

        ArrayList<String> output = prset.getOutput();

        try {
            FileIO.writeLine(output,args[1]);}
        catch(IOException e){
            e.printStackTrace();
        }

    }

}
