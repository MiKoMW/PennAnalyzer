/*
 * <summary>Songbo Hu</summary>
 * <author></author>
 * <email>s1647079@sms.ed.ac.uk</email>
 * <create-date>2017/12/27</create-date>
 */
package PennAnalyzer;

/**
 * A grammar. User can construct PosPair freely.
 *
 * @author Songbo
 * Created by Mac on 2017/12/27.
 */

public class PosPair {

    /**
     * A word token.
     */
    String token;

    /**
     * The pos of the token.
     */
    String pos;

    /**
     * constructor for a pos pair.
     */
    public PosPair(String tok, String p){
        this.pos = p;
        this.token = tok;
    }

    public PosPair(String st){
        this.pos = st.substring(0,st.indexOf(' '));
        this.token =  st.substring(st.indexOf(' ')+1, st.length());
    }

    public String toString(){
        return "token:" + this.token + " pos:" + this.pos;
    }

    public boolean equals(Object pp){
        if(pp == this){
            return true;
        }
        if(!(pp instanceof  PosPair)){
            return false;
        }
        PosPair pospair = (PosPair) pp;
        return this.token.equals(pospair.token) && this.pos.equals(pospair.pos);
    }

    public int hashCode(){

        String temp = this.token + "#pos#" +this.pos;
        return temp.hashCode();
    }

}
