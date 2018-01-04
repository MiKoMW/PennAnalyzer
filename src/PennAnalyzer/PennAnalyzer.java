/*
 * <summary>Songbo Hu</summary>
 * <author></author>
 * <email>s1647079@sms.ed.ac.uk</email>
 * <create-date>2017/11/30</create-date>
 */
package PennAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Analyzer for a penn style treebank and derived the PCFG from it.
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */

public class PennAnalyzer{

    /**
     * Grammar set.
     */
    ArrayList<Grammar> grams;
    GrammarSet gramSet;
    ArrayList<PosPair> poss;
    PosSet posSet;

    /**
     * Input files.
     */
    ArrayList<String> input;

    /**
     * Analyzer constructor.
     * @param in input file
     */
    public PennAnalyzer(ArrayList<String> in){
        grams = new ArrayList<Grammar>();
        gramSet = new GrammarSet();
        this.posSet = new PosSet();
        this.input = in;
        formatInput();
        normalize();
    }

    /**
     * Format the input, put the tree-structured input into a line.
     */
    public void formatInput(){

        ArrayList<String> formatInput = new ArrayList<String>();
        int counter = 0;
        String temp = new String();
        for(String st : input){
            counter += countDiff(st);
            temp = temp.concat(st);
            if (counter == 0) {
                formatInput.add(temp);
                temp = new String();
            }
        }
        this.input = formatInput;
    }

    /**
     * Counting the number of difference between '(' and ')'.
     */
    public int countDiff(String st){

        int ans = 0;
        char[] chs = st.toCharArray();
        for(char ch:chs){
            if(ch == '('){
                ans++;
            }
            if(ch == ')'){
                ans--;
            }
        }
        return ans;
    }

    /**
     * Delete the useless space.
     */
    public void normalize(){

        ArrayList<String> normalized = new ArrayList<String>();
        for(String st: input){
            StringTokenizer tok = new StringTokenizer(st," ");
            String ans = new String();
            while(tok.hasMoreTokens()){
                String temp = tok.nextToken();
                if(temp.charAt(0) == ')'){
                    ans+= temp;
                }else{
                    ans = ans + " " + temp;
                }
            }
            normalized.add(ans);
        }
        this.input = normalized;
    }

    /**
     * Get all grammars.
     * @return grammar set
     */
    public GrammarSet getAllGram(){

        for(String st : input){
            countGrammar(getGrammars(st));
        }
        return gramSet;
    }

    /**
     * Derived the grammars from a string acting like a tree tokenizer.
     * @return grammar lists
     */
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

    /**
     * Counting all grammars.
     * @param gras  grammar list
     */
    public void countGrammar(ArrayList<Grammar>  gras){
        for(Grammar st:gras) {
            this.gramSet.addGram(st);
        }
    }

    /**
     * Derived the pos pairs from a string acting like a tree tokenizer.
     * @return pos lists
     */
    public ArrayList<PosPair> getPos(String st){
        ArrayList<PosPair> ans = new ArrayList<PosPair>();
        String[] temp = st.split("[()]");
        for(String sts : temp){
            if(sts.matches("[^ ]+ [^ ]+")) {
                ans.add(new PosPair(sts));
            }
        }
        return ans;
    }

    /**
     * Counting all poss.
     * @param pp  pos list
     */
    public void countPos(ArrayList<PosPair> pp){
        for(PosPair p: pp){
            this.posSet.add(p);
        }
    }

    /**
     * Get all poss.
     * @return pos set
     */
    public PosSet getAllPos(){
        for(String st:input){
            countPos(getPos(st));
        }
        return this.posSet;
    }


}
