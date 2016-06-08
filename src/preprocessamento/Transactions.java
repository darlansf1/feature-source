/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package preprocessamento;

import estruturas.strFreq;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rafael
 */
public class Transactions {
    public static ArrayList<String> getTransSent(String texto, ArrayList<String> palavras, HashMap<String,String> dicionario){

       ArrayList<String> transacoes = new ArrayList<String>();
       int num_palavras = palavras.size(); //Tamanho do array de palavras frequentes

       //System.out.println("\nGerando o vetor de frases...\n");
       //Gerando uma matriz atributo valor só para as palavras que superaram um threshold
       String[] frases = texto.replace(".", "#").split("#"); //Substitui o caractere "." por "#" pois o split com o caractere "." não funciona
       int num_frases = frases.length; // Tamanho do array de frases
       
       for(int i=0; i<num_frases;i++){ //Verifica se um atributo está presente na frase e caso esteja é contabilizado na matriz
           String frase = new String(frases[i]);
           if(frase.length()<3){
               continue;
           }else{
               frase = frase.replace("@", "");
               frase = frase.replace("\n", " ");
               for(int j=0; j < 15; j++){ frase = frase.replace("  ", " ");}
               frase = frase.trim();
               if(frase.length()<3){
                    continue;
               }
               String[] stems = frase.split(" ");
               String new_frase = "";
               for(int j=0;j<stems.length;j++){
                   new_frase = new_frase + dicionario.get(stems[j]) + " ";
               }
               new_frase.trim();
               transacoes.add(new_frase);    
           }
       }
       return transacoes;
               
    }
    
    public static ArrayList<String> getTransPar(String texto, ArrayList<String> palavras, HashMap<String,String> dicionario){

       ArrayList<String> transacoes = new ArrayList<String>();
       int num_palavras = palavras.size(); //Tamanho do array de palavras frequentes

       //System.out.println("\nGerando o vetor de frases...\n");
       //Gerando uma matriz atributo valor só para as palavras que superaram um threshold
       //System.out.println("Imprimindo o Texto(3):");
       //System.out.println(texto);
       
       texto = texto.replace(".", "#");
       String[] paragrafos =  texto.split("# \n");//Substitui o caractere "." por "#" pois o split com o caractere "." não funciona
      
       //System.out.println("Imprimindo o Texto(4):");
       //System.out.println(texto);
       
       int num_par = paragrafos.length; // Tamanho do array de frases
       
       //System.out.println("\nCriando a matriz atributo-valor...\n");
       //System.out.println("Imprimindo paragrafos:");
       for(int i=0; i<num_par;i++){ //Verifica se um atributo está presente na frase e caso esteja é contabilizado na matriz
           String paragrafo = new String(paragrafos[i]);
           paragrafo = paragrafo.replace("#", "");
           paragrafo = paragrafo.replace("\n", " ");
           paragrafo = paragrafo.replace("\n", " ");
           paragrafo = paragrafo.replace("@", "");
           for(int j=0; j < 15; j++){ paragrafo = paragrafo.replace("  ", " ");}
           if(paragrafo.length()<3){
               continue;
           }else{
               String[] stems = paragrafo.split(" ");
               String new_paragrafo = "";
               for(int j=0;j<stems.length;j++){
                   new_paragrafo = new_paragrafo + dicionario.get(stems[j]) + " ";
               }
               new_paragrafo.trim();
               transacoes.add(new_paragrafo);
           }
       }
       return transacoes;
               
    }
    
    public static ArrayList<String> getTransJan(String texto, int tamJanela, int tamPulo, HashMap<String,String> dicionario){

       ArrayList<String> transacoes = new ArrayList<String>();

       //System.out.println("\nGerando o vetor de frases...\n");
       //Gerando uma matriz atributo valor só para as palavras que superaram um threshold
       //System.out.println("Imprimindo o Texto(3):");
       //System.out.println(texto);
       
       texto = texto.replace(".", "");
       texto = texto.replace("\n", "");
       texto = texto.replace("@", "");
       texto = texto.trim();
       for(int j=0; j < 15; j++){ texto = texto.replace("  ", " ");}
       
       //System.out.println("Imprimindo o Texto(4):");
       //System.out.println(texto);
    
       String[] palavras = texto.split(" ");
       
       int tam_max = palavras.length;
       int pi = 0;
       int pf = tamJanela - 1;
       while(pf < tam_max){
           String transacao = "";
           for(int i=pi;i<=pf;i++){
               transacao = transacao + palavras[i] + " ";
           }
           String[] stems = transacao.split(" ");
           String new_trans = "";
           for(int j=0;j<stems.length;j++){
               new_trans = new_trans + dicionario.get(stems[j]) + " ";
           }
           new_trans.trim();
           transacoes.add(new_trans);
           pi = pi + tamPulo;
           pf = pf + tamPulo;
       }
               
       return transacoes;
               
    }
    
    public static ArrayList<String> getTransJan2(String texto, int tamJanela, int tamPulo, HashMap<String,String> dicionario){

       ArrayList<String> transacoes = new ArrayList<String>();

       //System.out.println("\nGerando o vetor de frases...\n");
       //Gerando uma matriz atributo valor só para as palavras que superaram um threshold
       //System.out.println("Imprimindo o Texto(3):");
       //System.out.println(texto);
       
       texto = texto.replace(".", "");
       texto = texto.replace("\n", "");
       texto = texto.replace("@", "");
       texto = texto.trim();
       for(int j=0; j < 15; j++){ texto = texto.replace("  ", " ");}
       
       //System.out.println("Imprimindo o Texto(4):");
       //System.out.println(texto);
    
       String[] palavras = texto.split(" ");
       
       int tam_max = palavras.length;
       int pi = 0;
       int pf = tamJanela - 1;
       
       //Início das transações de janela
       for(int i=0;i<(tamJanela-1);i++){
           String transacao = "";
           for(int j=0;j<=i;j++){
               try{
                transacao = transacao + palavras[j] + " ";
               }catch(Exception e){
                   
               } 
           }
           String[] stems = transacao.split(" ");
           String new_trans = "";
           for(int j=0;j<stems.length;j++){
               new_trans = new_trans + dicionario.get(stems[j]) + " ";
           }
           new_trans = new_trans.trim();
           transacoes.add(new_trans);
       }
       
       //Transações de Janelas Normais
       while(pf < tam_max){
           String transacao = "";
           for(int i=pi;i<=pf;i++){
               transacao = transacao + palavras[i] + " ";
           }
           String[] stems = transacao.split(" ");
           String new_trans = "";
           for(int j=0;j<stems.length;j++){
               new_trans = new_trans + dicionario.get(stems[j]) + " ";
           }
           new_trans = new_trans.trim();
           transacoes.add(new_trans);
           pi = pi + tamPulo;
           pf = pf + tamPulo;
       }
       
       //Fim das Transações de Janelas
       for(int i=(tam_max - tamJanela + 1);i<tam_max;i++){
           String transacao = "";
           for(int j=i;j<tam_max;j++){
               try{
                transacao = transacao + palavras[j] + " ";
               }catch(Exception e){
                   
               }
           }
           String[] stems = transacao.split(" ");
           String new_trans = "";
           for(int j=0;j<stems.length;j++){
               new_trans = new_trans + dicionario.get(stems[j]) + " ";
           }
           new_trans = new_trans.trim();
           transacoes.add(new_trans);
       }
       
       return transacoes;
               
    }
    
}
