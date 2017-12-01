import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class PRGrammarSet {

    private HashMap<Grammar,Double> PRCouunter;
    PRGrammarSet(){
        this.PRCouunter = new HashMap<Grammar,Double>();
    }
    PRGrammarSet(HashMap<Grammar,Double> gras){
        this.PRCouunter = gras;
    }

    public ArrayList<String> getOutput(){
        ArrayList<String> ans = new ArrayList<String>();

        for(Grammar gr : PRCouunter.keySet()){
            Double con = PRCouunter.get(gr);
            ans.add(con + " " + gr.toString());
        }
        return ans;
    }




}
