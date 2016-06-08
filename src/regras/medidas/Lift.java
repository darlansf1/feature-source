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
import regras.estruturas.RegraLift;

/**
 *
 * @author Rafael
 */
public class Lift {
    public ArrayList<RegraLift> calcLift(ArrayList<Regra> regras, HashMap<String,Float> itemSup){
        ArrayList<RegraLift> regrasLift = new ArrayList<RegraLift>();
        
        for(int i=0;i<regras.size();i++){
            Regra regra = regras.get(i);
            float lift = 0;
            lift = regra.confianca / itemSup.get(regra.cabeca);
            RegraLift regraLift = new RegraLift(regra.cabeca, regra.corpo, lift);
            regrasLift.add(regraLift);
        }        
        
        Object[] sortLift =  regrasLift.toArray();
           Arrays.sort( sortLift, new Comparator() {
                public int compare( Object obj1, Object obj2 ) {
                        RegraLift r1 = (RegraLift)obj1;
                        RegraLift r2 = (RegraLift)obj2;
                        if(r1.lift > r2.lift){
                            return -1;
                        }else{
                            if(r1.lift < r2.lift){
                                return 1;
                            }else{
                                return 0;
                            }         
                        }
                }
            });

            
            regrasLift = new ArrayList<RegraLift>();
            for(int i=0;i<sortLift.length;i++){
                RegraLift obj = (RegraLift)sortLift[i];
                //System.out.println(obj.palavra + " - " + obj.frequencia);
                regrasLift.add(obj);
            }
        
        return regrasLift;
    }
}
