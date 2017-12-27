/*
 * <summary>Songbo Hu</summary>
 * <author></author>
 * <email>s1647079@sms.ed.ac.uk</email>
 * <create-date>2017/11/30</create-date>
 */
package PennAnalyzer;

import java.util.ArrayList;

/**
 * A grammar. User can construct grammar freely.
 *
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class Grammar {

    /**
     * Left hand side non-terminal.
     */
    String lhr;

    /**
     * Right hand side elements.
     */
    ArrayList<String> rhr;


    /**
     * constructor for a grammar.
     */
    Grammar(){
        this.lhr = new String();
        this.rhr = new ArrayList<String>();
    }

    Grammar(String lh){
        this.lhr = lh;
        this.rhr = new ArrayList<String>();
    }

    Grammar(String lh,ArrayList<String> rh){
        this.lhr = lh;
        this.rhr = rh;
    }

    /**
     * Set left hand side element of a grammar.
     * @param lh left side element
     */
    public void setLhr(String lh){
        this.lhr = lh;
    }

    /**
     * Add left hand side element of a grammar.
     * @param rh right side element
     */
    public void addRhr(String rh){
        this.rhr.add(rh);
    }

@Override
    public String toString() {
    String ans = new String();
    ans = lhr;
    ans += " -> ";
    for (String st : rhr) {
        ans += " " + st;
    }
    return ans;
    }

   public boolean equals(Object gr){

        if(gr == this){
            return true;
        }
        if(!(gr instanceof  Grammar)){
            return false;
        }
        Grammar grammar = (Grammar) gr;
        return this.lhr.equals(grammar.lhr) && this.rhr.equals(grammar.rhr);
    }

    public int hashCode(){
       String temp = this.lhr;
       for(String st : rhr){
           temp = temp.concat(st);
       }

       return temp.hashCode();
    }

}
