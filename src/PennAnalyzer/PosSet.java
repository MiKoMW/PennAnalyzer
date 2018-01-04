/*
 * <summary>Songbo Hu</summary>
 * <author></author>
 * <email>s1647079@sms.ed.ac.uk</email>
 * <create-date>2017/12/27</create-date>
 */
package PennAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * A collection of poss.
 * Created by Mac on 2017/12/27.
 */
public class PosSet {

    /**
     * A counter for frequency of poss.
     */
    HashMap<PosPair,Integer> posCounter;

    /**
     * Constructor of a pos set.
     */
    public PosSet(){
        this.posCounter = new HashMap<PosPair, Integer>();
    }

    public PosSet(HashMap<PosPair,Integer> counter){
        this.posCounter = counter;
    }

    /**
     * Add a pos into the set.
     * @param pp the added grammar
     */
    public void add(PosPair pp){
        posCounter.put(pp,posCounter.getOrDefault(pp,0)+1);
    }

    /**
     * Get the output arraylist of string.
     * @return output arraylist
     */
    public ArrayList<String> getOutput(){

        ArrayList<String> ans = new ArrayList<String>();

        for(PosPair gr : posCounter.keySet()){
            Integer con = posCounter.get(gr);
            ans.add(con + " " + gr.toString());
        }
        return ans;
    }


}
