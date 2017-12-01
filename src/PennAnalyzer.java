import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class PennAnalyzer {

    ArrayList<Grammar> grams;
    GrammarSet gramSet;
    ArrayList<String> input;
    PennAnalyzer(    ArrayList<String> in){
        grams = new ArrayList<Grammar>();
        gramSet = new GrammarSet();
        this.input = in;
    }

    public GrammarSet getAllGram(){
        for(String st : input){
            countGrammar(getGrammars(st));
        }
        return gramSet;
    }

    public ArrayList<Grammar> getGrammars(String penn){
        StringTokenizer tok = new StringTokenizer(penn," ");
        int tokLevel = 0;

        ArrayList<Grammar> out = new ArrayList<Grammar>();
        HashMap<Integer,Grammar> tempGram = new HashMap<Integer,Grammar>();

        while(tok.hasMoreTokens()){

            String str = tok.nextToken();
            if(str.charAt(0) == '('){
                String temp1 = str.substring(1);
                Grammar gr1 = new Grammar(temp1);
                tokLevel++;
                tempGram.put(tokLevel,gr1);
                if(tokLevel > 1){
                    Grammar gr2 = tempGram.get(tokLevel - 1);
                    gr2.addRhr(str.substring(1));
                    tempGram.put(tokLevel-1,gr2);
                }
                continue;
            }

            if (str.contains(")")){
                String temp1 = str.substring(0,str.indexOf(')'));
                Grammar gr2 = tempGram.get(tokLevel);
                gr2.addRhr(temp1);
                out.add(gr2);
                tokLevel--;
                int con = str.lastIndexOf(')') - str.indexOf(')');
                while(con-- > 0 ){
                    out.add(tempGram.get(tokLevel));
                    tokLevel--;
                }

            }
        }
        return out;
    }

    public void countGrammar(ArrayList<Grammar>  gras){
        for(Grammar st:gras) {
            this.gramSet.addGram(st);
        }
    }



}
