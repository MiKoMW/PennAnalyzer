import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Songbo
 * Created by Mac on 2017/11/30.
 */
public class GrammarSet {
    private HashMap<Grammar,Integer> gramCounter;

    GrammarSet(){
        this.gramCounter = new HashMap<Grammar,Integer>();
    }
    GrammarSet(HashMap<Grammar,Integer> gras){
        this.gramCounter = gras;
    }

    public void addGram(Grammar gr){

        gramCounter.put(gr,gramCounter.getOrDefault(gr,1)+1);
    }

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
        //this.gramCounter = tempSet;
        return new GrammarSet(tempSet);
    }

    public ArrayList<String> getOutput(){
        ArrayList<String> ans = new ArrayList<String>();

        for(Grammar gr : gramCounter.keySet()){
            Integer con = gramCounter.get(gr);
            ans.add(con + " " + gr.toString());
        }
        return ans;
    }

}
