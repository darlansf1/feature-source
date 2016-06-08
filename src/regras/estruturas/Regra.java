/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package regras.estruturas;

/**
 *
 * @author Rafael
 */
public class Regra {
    public String cabeca;
    public String corpo;
    public float suporte;
    public float confianca;
    
    public Regra(String cab, String cor, Float sup, Float conf){
        cabeca = cab;
        corpo = cor;
        suporte = sup;
        confianca = conf; 
    }
}
