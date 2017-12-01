import java.util.ArrayList;

/**
 * Created by Mac on 2017/11/20.
 */
public class Grammar {
    String lhr;
    ArrayList<String> rhr;

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

    public void setLhr(String lh){
        this.lhr = lh;
    }
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
