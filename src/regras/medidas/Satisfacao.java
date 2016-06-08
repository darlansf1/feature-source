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
import regras.estruturas.RegraSatisfacao;

/**
 *
 * @author ragero
 */
public class Satisfacao {
    public ArrayList<RegraSatisfacao> calcRuleInterest(ArrayList<Regra> regras, HashMap<String,Float> itemSup){
        ArrayList<RegraSatisfacao> regrasSatisfacao = new ArrayList<RegraSatisfacao>();

        for(int i=0;i<regras.size();i++){
            Regra regra = regras.get(i);
            float satisfacao = 0;
            float supEsp = 0;
            if(regra.corpo.length() == 0){
                continue;
            }
            
            satisfacao = (1 - itemSup.get(regra.cabeca)) - ((itemSup.get(regra.corpo) - regra.suporte)/(1 - itemSup.get(regra.cabeca)))/(1 - itemSup.get(regra.cabeca));
            RegraSatisfacao regraSatisfacao = new RegraSatisfacao(regra.cabeca, regra.corpo, satisfacao);
            regrasSatisfacao.add(regraSatisfacao);
        }

        Object[] sortSatisfacao =  regrasSatisfacao.toArray();
           Arrays.sort( sortSatisfacao, new Comparator() {
                public int compare( Object obj1, Object obj2 ) {
                        RegraSatisfacao r1 = (RegraSatisfacao)obj1;
                        RegraSatisfacao r2 = (RegraSatisfacao)obj2;
                        if(r1.satisfacao > r2.satisfacao){
                            return -1;
                        }else{
                            if(r1.satisfacao < r2.satisfacao){
                                return 1;
                            }else{
                                return 0;
                            }
                        }
                }
            });


            regrasSatisfacao = new ArrayList<RegraSatisfacao>();
            for(int i=0;i<sortSatisfacao.length;i++){
                RegraSatisfacao obj = (RegraSatisfacao)sortSatisfacao[i];
                //System.out.println(obj.palavra + " - " + obj.frequencia);
                regrasSatisfacao.add(obj);
            }

        return regrasSatisfacao;
    }
}
