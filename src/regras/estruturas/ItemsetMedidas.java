/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package regras.estruturas;

/**
 *
 * @author Rafael
 */
public class ItemsetMedidas {
    
    public String[] items;
    public float suporte;
    public float confianca;
    public float lift;
    public float novidade;
    public float conviccao;
    public float satisfacao;

    public ItemsetMedidas(String[] itemsets, float sup, float conf, float lif){
        
        suporte = sup;
        confianca = conf; 
    }

}
