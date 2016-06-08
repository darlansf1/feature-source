package preprocessamento;

/*
 * Remove caracteres nao permitidos de uma variavel string que representa um texto.
 * Os unicos caracteres permitidos sao: abcdefghijklmnopqrstuvwxyz.!_?
 */

/*
 * Remove caracteres nao permitidos de uma variavel string que representa um texto.
 * Os unicos caracteres permitidos sao: abcdefghijklmnopqrstuvwxyz.!_?
 */
public class Cleaner {

    public Cleaner(){

    }

    /** Recebe uma String qualquer e retorna uma String apenas com caracteres permitidos  */
    public String clean(String str){
        String allowed="abcdefghijklmnopqrstuvwxyz.!_?\n";
        StringBuffer new_str= new StringBuffer("");
        str=str.toLowerCase();

        //for(int i=0; i < 100; i++) str=str.replace("\n\n", "\n");
        //str=str.replace("\r", "");
        //str=str.replace("\n", ".");

        for(int i=0; i < str.length(); i++){
            String ch = String.valueOf(str.charAt(i));
            if(allowed.contains(ch)){ /* char allowed? */
                if(ch.equals(".") || ch.equals("!") || ch.equals("?")) ch=" . ";
                new_str=new_str.append(ch);
            }else new_str=new_str.append(" ");
        }
        String new_str2 = new_str.toString();
        for(int i=0; i < 15; i++) new_str2=new_str2.replace("  ", " ");
        for(int i=0; i < 15; i++) new_str2=new_str2.replace(". .", ". ");
        return new_str2.trim();
    }

}
