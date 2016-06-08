/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package regras.medidas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import regras.estruturas.Regra;
import regras.estruturas.RegraInterest;

/**
 *
 * @author ragero
 */
public class RuleInterest {
    public ArrayList<RegraInterest> calcRuleInterest(ArrayList<Regra> regras, HashMap<String,Float> itemSup){
        ArrayList<RegraInterest> regrasInterest = new ArrayList<RegraInterest>();

        for(int i=0;i<regras.size();i++){
            Regra regra = regras.get(i);
            float interest = 0;
            float supEsp = 0;
            if(regra.corpo.length() == 0){
                continue;
            }
            System.err.println("RegraCabeca: " + regra.cabeca + " RegraCorpo: " + regra.corpo);
            supEsp = itemSup.get(regra.cabeca) * itemSup.get(regra.corpo);
            interest = regra.suporte - supEsp;
            RegraInterest regraInterest = new RegraInterest(regra.cabeca, regra.corpo, interest);
            regrasInterest.add(regraInterest);
        }

        Object[] sortLift =  regrasInterest.toArray();
           Arrays.sort( sortLift, new Comparator() {
                public int compare( Object obj1, Object obj2 ) {
                        RegraInterest r1 = (RegraInterest)obj1;
                        RegraInterest r2 = (RegraInterest)obj2;
                        if(r1.interest > r2.interest){
                            return -1;
                        }else{
                            if(r1.interest < r2.interest){
                                return 1;
                            }else{
                                return 0;
                            }
                        }
                }
            });


            regrasInterest = new ArrayList<RegraInterest>();
            for(int i=0;i<sortLift.length;i++){
                RegraInterest obj = (RegraInterest)sortLift[i];
                //System.out.println(obj.palavra + " - " + obj.frequencia);
                regrasInterest.add(obj);
            }

        return regrasInterest;
    }
}
