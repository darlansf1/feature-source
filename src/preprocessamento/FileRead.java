package preprocessamento;

/*
 * Classe que realiza a leitura de um arquivo texto em disco.
 */

import java.io.File;
import java.io.RandomAccessFile;

/*
 * Classe que realiza a leitura de um arquivo texto em disco.
 */
public class FileRead {


    public FileRead(){

    }

    /** Retorna o conteudo de um arquivo em disco de endereco f */
    public String Read(String f){


        try
        {
            File file = new File(f);
            
            //return texto;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;


    }

}
