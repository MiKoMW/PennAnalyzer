/*
 * <summary>Songbo Hu</summary>
 * <author></author>
 * <email>s1647079@sms.ed.ac.uk</email>
 * <create-date>2017/11/30</create-date>
 */
package PennAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A collection of grammars.
 *
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class GrammarSet {

    /**
     * A counter for frequency of grammars.
     */
    private HashMap<Grammar,Integer> gramCounter;

    /**
     * Constructor of a grammar set.
     */
    GrammarSet(){
        this.gramCounter = new HashMap<Grammar,Integer>();
    }
    GrammarSet(HashMap<Grammar,Integer> gras){
        this.gramCounter = gras;
    }

    /**
     * Add a grammar into the set.
     * @param gr the added grammar
     */
    public void addGram(Grammar gr){
        gramCounter.put(gr,gramCounter.getOrDefault(gr,0)+1);
    }

    /**
     * Get the Probabilistic grammar set.
     */
    public PRGrammarSet getPRGram() {

        HashMap<String, Integer> nodeCounter = new HashMap<String,Integer>();
        HashMap<Grammar,Double> prCouunter = new HashMap<Grammar,Double>();

        for(Grammar gr : gramCounter.keySet()){
            nodeCounter.put(gr.lhr,nodeCounter.getOrDefault(gr.lhr,1)+1);
        }

        for(Grammar gr : gramCounter.keySet()){
            prCouunter.put(gr,((double)gramCounter.get(gr)/(double)nodeCounter.get(gr.lhr)));
        }

        return new PRGrammarSet(prCouunter);

    }

    /**
     * Transfer the original grammar into CNF.
     */
    public GrammarSet toCNF(){

        HashMap<Grammar,Integer> tempSet = new HashMap<Grammar,Integer>();
        for(Grammar gr : gramCounter.keySet()){

            Integer con = gramCounter.get(gr);
            if(gr.rhr.size() <= 2){
                tempSet.put(gr,con);
                continue;
            }else{

                Grammar temp = new Grammar(gr.lhr);
                ArrayList<String> righthand = gr.rhr;

                String st = "";
                for(int i = 2; i < righthand.size(); i++){
                    st = st + righthand.get(i);
                }
                temp.setLhr(righthand.get(0));
                temp.addRhr(righthand.get(1));
                temp.addRhr(st);
                tempSet.put(temp,con);

                for(int subcon = 2; subcon < righthand.size()-1; subcon++){

                    Grammar cnfGram = new Grammar(st);
                    cnfGram.addRhr(righthand.get(subcon));
                    st = "";
                    for(int k = subcon+1;k<righthand.size();k++){
                        st = st.concat(righthand.get(k));
                    }
                    cnfGram.addRhr(st);
                    tempSet.put(cnfGram,con);
                }
            }

        }

        return new GrammarSet(tempSet);
    }

    /**
     * Get the output arraylist of string.
     * @return output arraylist
     */
    public ArrayList<String> getOutput(){

        ArrayList<String> ans = new ArrayList<String>();

        for(Grammar gr : gramCounter.keySet()){
            Integer con = gramCounter.get(gr);
            ans.add(con + " " + gr.toString());
        }
        return ans;
    }

}
