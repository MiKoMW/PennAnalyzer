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
 * A collection of probabilistic grammars.
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class PRGrammarSet extends GrammarSet {

    /**
     * The probability of grammars.
     */
    private HashMap<Grammar,Double> PRCouunter;

    /**
     * Constructor of a probabilistic grammar.
     */
    PRGrammarSet(){
        this.PRCouunter = new HashMap<Grammar,Double>();
    }
    PRGrammarSet(HashMap<Grammar,Double> gras){
        this.PRCouunter = gras;
    }

    @Override
    public ArrayList<String> getOutput(){
        ArrayList<String> ans = new ArrayList<String>();

        for(Grammar gr : PRCouunter.keySet()){
            Double con = PRCouunter.get(gr);
            ans.add(con + " " + gr.toString());
        }
        return ans;
    }




}
