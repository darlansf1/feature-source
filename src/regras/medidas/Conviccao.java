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
import regras.estruturas.RegraConviccao;

/**
 *
 * @author ragero
 */
public class Conviccao {
    public ArrayList<RegraConviccao> calcConviccao(ArrayList<Regra> regras, HashMap<String,Float> itemSup){
        ArrayList<RegraConviccao> regrasConviccao = new ArrayList<RegraConviccao>();

        for(int i=0;i<regras.size();i++){
            Regra regra = regras.get(i);
            float convic = 0;
            if(regra.corpo.length() == 0){
                continue;
            }
            convic = (itemSup.get(regra.corpo) * itemSup.get(regra.cabeca))/(itemSup.get(regra.corpo) - regra.suporte);
            RegraConviccao regraConviccao = new RegraConviccao(regra.cabeca, regra.corpo, convic);
            regrasConviccao.add(regraConviccao);
        }

        Object[] sortConviccao =  regrasConviccao.toArray();
           Arrays.sort( sortConviccao, new Comparator() {
                public int compare( Object obj1, Object obj2 ) {
                        RegraConviccao r1 = (RegraConviccao)obj1;
                        RegraConviccao r2 = (RegraConviccao)obj2;
                        if(r1.conviccao > r2.conviccao){
                            return -1;
                        }else{
                            if(r1.conviccao < r2.conviccao){
                                return 1;
                            }else{
                                return 0;
                            }
                        }
                }
            });


            regrasConviccao = new ArrayList<RegraConviccao>();
            for(int i=0;i<sortConviccao.length;i++){
                RegraConviccao obj = (RegraConviccao)sortConviccao[i];
                //System.out.println(obj.palavra + " - " + obj.frequencia);
                regrasConviccao.add(obj);
            }

        return regrasConviccao;
    }
}
