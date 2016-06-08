/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package feature;

import estruturas.strFreq;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import preprocessamento.Cleaner;
import preprocessamento.Transactions;
import preprocessamento.StemmerEn;
import preprocessamento.StopWords;
import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;

/**
 *
 * @author Rafael
 */
public class TextToTransaction {
    static RandomAccessFile arq;
    public static ArrayList<String> convertTT(File arqIn, HashMap<String,ArrayList<strFreq>> dicionario, String language, boolean radicalizar, String typeTrans, Integer tamJanela, Integer tamPulo, StopWords sw, boolean remStopWords, HashMap<String,String> stemPal){
        
        ArrayList<String> transacoes = new ArrayList<String>();
        
        //FileRead fr = new FileRead(); //Objeto para a leitura de documentos
        Cleaner cln = new Cleaner(); //Objeto para limpeza de documentos
          
        Stemmer stemPt = new OrengoStemmer();
        StemmerEn stemEn = new StemmerEn(); //Objeto para a radicalização
       
        
        //String txt = fr.Read(arqIn.toString()); //Leitura do arquivo texto 
        //fr = null;
        //Lendo o arquivo de Textos
        String txt = "";
        
        try{
            if (!arqIn.exists()) {
                System.out.println("O arquivo não foi encontrado: "+arqIn.getAbsolutePath());
            }
            arq = new RandomAccessFile(arqIn, "r");
            //FileReader fReader = new FileReader(f);
            //BufferedReader br = new BufferedReader(fReader);
            //StringBuffer bufSaida = new StringBuffer();

            String linha;    
            while( (linha = arq.readLine()) != null )
            {
                txt = txt + linha + "\n";

            }

            arq.close();    
        }catch(Exception e){
            System.out.println("Ocorreu um erro ao ler o arquivo.");
        }
        
        
        //Fim da leitura do arquivo de textos
        //System.out.println("\n\nImprimindo arquivo texto(1):");
        //System.out.println(txt);
        
        txt = cln.clean(txt); //Limpeza do arquivo texto
        
        //System.out.println("\n\nImprimindo arquivo texto(2):");
        //System.out.println(txt);
        
        //System.out.println("\nRemovendo StopWords...\n");
        if(remStopWords==true){
            txt = sw.removeStopWords(txt); //Remoção de StopWords do arquivo texto    
        }
        
        
        //System.out.println("\n\nImprimindo arquivo texto(2.1):");
        //System.out.println(txt);
       
        String txt2 = txt.replace(".",""); //Elimina os sinais de pontuação do texto para que este as palavras do texto possam ser jogadas em um vedor
        int result = 1;
        while(result != 0){ 
            int tam_1 = txt2.length();
            txt2 = txt2.replace("  "," ");
            int tam_2 = txt2.length();
            result = tam_1 - tam_2;
        }
        
        //-------------------------------------------------------------------------
        ArrayList<String> palavras = new ArrayList<String>();
        if(radicalizar == true){
            if(language.equals("port")){ //Radicalizando as palavras do documentos
                String[] todas_palavras = txt2.split(" "); //Armazena as palavras do texto em um vetor
                for(int i = 0;i<todas_palavras.length;i++){
                    String chave = todas_palavras[i];
                    String stem;
                    if(stemPal.containsKey(chave)){
                        stem = stemPal.get(chave);
                    }else{
                        stem = new String(stemPt.wordStemming(chave));
                        stemPal.put(chave, stem);
                    }
                    ArrayList<strFreq> listaPalavras;
                    if(dicionario.containsKey(stem)){
                        listaPalavras = dicionario.get(stem);
                        boolean trocou = false;
                        for(int j=0;j<listaPalavras.size();j++){
                            strFreq palFreq = listaPalavras.get(j);
                            if(palFreq.palavra.equals(chave)){
                                palFreq.frequencia++;
                                trocou = true;
                            }
                        }
                        if(trocou == false){
                            listaPalavras.add(new strFreq(chave,1));
                        }
                    }else{
                        listaPalavras = new ArrayList<strFreq>();
                        listaPalavras.add(new strFreq(chave,1));
                        dicionario.put(stem, listaPalavras);
                    }
                    if(!palavras.contains(stem)){
                        palavras.add(stem);
                    }            
                }
            }else{
                String[] todas_palavras = txt2.split(" "); //Armazena as palavras do texto em um vetor
                for(int i = 0;i<todas_palavras.length;i++){
                    String chave = todas_palavras[i];
                    chave = chave.trim();
                    String stem;
                    if(stemPal.containsKey(chave)){
                        stem = stemPal.get(chave);
                    }else{
                        stem = new String(StemmerEn.get(chave));
                        stemPal.put(chave, stem);
                    }                    
                
                    ArrayList<strFreq> listaPalavras;
                    if(dicionario.containsKey(stem)){
                        listaPalavras = dicionario.get(stem);
                        boolean trocou = false;
                        for(int j=0;j<listaPalavras.size();j++){
                            strFreq palFreq = listaPalavras.get(j);
                            if(palFreq.palavra.equals(chave)){
                                palFreq.frequencia++;
                                trocou = true;
                            }
                        }
                        if(trocou == false){
                            listaPalavras.add(new strFreq(chave,1));
                        }
                    }else{
                        listaPalavras = new ArrayList<strFreq>();
                        listaPalavras.add(new strFreq(chave,1));
                        dicionario.put(stem, listaPalavras);
                    }
                    if(!palavras.contains(stem)){
                        palavras.add(stem);
                    }            
                }
            }     
        }else{
            String[] todas_palavras = txt2.split(" "); //Armazena as palavras do texto em um vetor
                for(int i = 0;i<todas_palavras.length;i++){
                    String chave = todas_palavras[i];
                    chave = chave.trim();
                    if(!stemPal.containsKey(chave)){
                        stemPal.put(chave, chave);
                    }
                    ArrayList<strFreq> listaPalavras;
                    if(dicionario.containsKey(chave)){
                        listaPalavras = dicionario.get(chave);
                        boolean trocou = false;
                        for(int j=0;j<listaPalavras.size();j++){
                            strFreq palFreq = listaPalavras.get(j);
                            if(palFreq.palavra.equals(chave)){
                                palFreq.frequencia++;
                                trocou = true;
                            }
                        }
                        if(trocou == false){
                            listaPalavras.add(new strFreq(chave,1));
                        }
                    }else{
                        listaPalavras = new ArrayList<strFreq>();
                        listaPalavras.add(new strFreq(chave,1));
                        dicionario.put(chave, listaPalavras);
                    }
                    if(!palavras.contains(chave)){
                        palavras.add(chave);
                    }            
                }
        }
        
        
        
        
        //System.out.println("typeTrans: " + typeTrans);
        if(typeTrans.equals("sent")){
            transacoes = Transactions.getTransSent(txt, palavras, stemPal);
        }else{
            if(typeTrans.equals("par")){
                transacoes = Transactions.getTransPar(txt, palavras, stemPal);
            }else{
                transacoes = Transactions.getTransJan2(txt, tamJanela, tamPulo, stemPal);
            }
        }
        
        
     /*String txt2 = txt.replace(".",""); //Elimina os sinais de pontuação do texto para que este as palavras do texto possam ser jogadas em um vedor
       int result = 1;
       while(result != 0){ //Elimina multiplos espaços em branco
           int tam_1 = txt2.length();
           txt2 = txt2.replace("  "," ");
           int tam_2 = txt2.length();
           result = tam_1 - tam_2;
       }
           
        */   
        return transacoes;
    }
}
