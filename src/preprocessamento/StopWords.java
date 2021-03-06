/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package preprocessamento;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class StopWords {
    static ArrayList<String> lista = new ArrayList<String>();
    
    public StopWords(String language){
        if(language.equals("port")){
            try{
                RandomAccessFile arqStop = new RandomAccessFile("stopPort.txt", "r");
                String linha = "";
                while((linha = arqStop.readLine())!=null){
                    if(linha.length()>0){
                        String elem = new String(linha);
                        lista.add(elem);
                    }
                }
                arqStop.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de stopwords (stopPort.txt)\n. A operação será abortada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            try{
                RandomAccessFile arqStop = new RandomAccessFile("stopIngl.txt", "r");
                String linha = "";
                while((linha = arqStop.readLine())!=null){
                    if(linha.length()>0){
                        String elem = new String(linha);
                        lista.add(elem);
                    }
                }
                arqStop.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de stopwords (stopIngl.txt)\n. A operação será abortada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    public boolean isStopWord(String str){

        if(lista.contains(str)){
            return true;
        }else{
            return false;
        }
    }
    
    public String removeStopWords(String str){
        String[] terms = str.split(" ");
        String new_str = "";

        for(int i=0; i < terms.length; i++){
            String termo = terms[i];
            if(termo.startsWith("\n")){
                new_str = new_str.concat("\n");
            }
            termo = termo.trim();
            boolean quebra = false;
            if(termo.contains("\n")){
                quebra = true;
            }
            String[] terms2 = termo.split("\n");
            for(int j=0; j < terms2.length; j++){
                String termo2 = terms2[j].trim();
                if(j == terms2.length - 1){
                    quebra = false;
                }
                if(!isStopWord(termo2)){
                        if(termo2.equals(".")){
                            new_str=new_str.concat(" . ");
                            if(quebra == true){new_str=new_str.concat("\n");}
                            continue;
                        }
                        if(!(termo2.length()<=2)){
                            new_str=new_str.concat(termo2+" ");
                            if(quebra == true){new_str=new_str.concat("\n");}
                            continue;
                        }   

                }else{
                    new_str = new_str + " @ ";
                    if(quebra == true){new_str=new_str.concat("\n");}
                }    
            }
        }
        return  new_str.trim();
        
    }
}
