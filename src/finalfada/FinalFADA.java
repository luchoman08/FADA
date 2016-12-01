/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author lucho
 */
public class FinalFADA {
private File fichero; //contiene los resultados de las loterias en formato separado por comas ej: 27/01/2001,1,2,5,20,27,33
private ArrayList<ResultadoLoteria> loteriasEstructuradas; //guarda por cada fila un vector que representa un resultado de loteria:     
private ArrayList<String> loteriasSimbolos;
public FinalFADA(){
    this.loteriasSimbolos = new ArrayList<String>();
    this.loteriasEstructuradas = new ArrayList<ResultadoLoteria>();
}
public void setFichero(File newFichero){
    this.fichero = newFichero;
}
public void inicializarLoterisSimbolos(){
    for(int i = 0 ;i < getLoteriasEstructuradas().size(); i++){
        this.getLoteriasSimbolos().add(this.getLoteriasEstructuradas().get(i).getResultadosSimbolos());
    }
}
protected File getFichero()
{
    return this.fichero;
}

public void openFile(String ruta){
    
    this.fichero = new File(ruta);
    
}
/*
inicializa las loterias estructuradas desde un fichero de texto 
*/

public void inicializarLoteriasEstructuradas(String ruta) throws FileNotFoundException, IOException, ParseException{ 
    this.openFile(ruta);
    FileReader fr = new FileReader (this.fichero);
    BufferedReader br = new BufferedReader(fr);
    String linea;
    while((linea=br.readLine())!=null){
        ResultadoLoteria tmp = new ResultadoLoteria(linea);
        this.getLoteriasEstructuradas().add(tmp);    
       // tmp.printInfo();
    }
     
}

public String lcs(String[] strings)
{
    if (strings.length == 0)
        return "";
    if (strings.length == 1)
        return strings[0];
    int max = -1;
    int cacheSize = 1;
    for (int i = 0; i < strings.length; i++)
    {
        cacheSize *= strings[i].length();
        if (strings[i].length() > max)
            max = strings[i].length();
    }
    String[] cache = new String[cacheSize];
    int[] indexes = new int[strings.length];
    for (int i = 0; i < indexes.length; i++)
        indexes[i] = strings[i].length() - 1;
    return lcsBack(strings, indexes, cache);
}
public String lcsBack(String[] strings, int[] indexes, String[] cache)
{
    for (int i = 0; i < indexes.length; i++ )
        if (indexes[i] == -1)
            return "";
    boolean match = true;
    for (int i = 1; i < indexes.length; i++)
    {
         if (strings[0].charAt(indexes[0]) != strings[i].charAt(indexes[i]))
         {
             match = false;
             break;
         } else {
         }
    }
    if (match)
    {
        int[] newIndexes = new int[indexes.length];
        for (int i = 0; i < indexes.length; i++)
            newIndexes[i] = indexes[i] - 1;
        String result = lcsBack(strings, newIndexes, cache) + strings[0].charAt(indexes[0]);
        cache[calcCachePos(indexes, strings)] = result;
        return result;
    }
    else
    {
        String[] substrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            if (indexes[i] <= 0)
                substrings[i] = "";
            else
            {
                int[] newIndexes = new int[indexes.length];
                for (int j = 0; j < indexes.length; j++)
                    newIndexes[j] = indexes[j];
                newIndexes[i]--;
                int cachePos = calcCachePos(newIndexes, strings);
                if (cache[cachePos] == null)
                    substrings[i] = lcsBack(strings, newIndexes, cache);
                else
                    substrings[i] = cache[cachePos];
            }
        }
        String longeststring = "";
        int longestlength = 0;
        for (int i = 0; i < substrings.length; i++)
        {
            if (substrings[i].length() > longestlength)
            {
                longeststring = substrings[i];
                longestlength = longeststring.length();
            }
        }
        cache[calcCachePos(indexes, strings)] = longeststring;
        return longeststring;
    }
}
public int calcCachePos(int[] indexes, String[] strings)
{
    int factor = 1;
    int pos = 0;
    for (int i = 0; i < indexes.length; i++)
    {
        pos += indexes[i] * factor;
        factor *= strings[i].length();
    }
    return pos;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here
        String[] a = {"68GI!!", ">BKL!!", "#*46=B!!"};
        //ResultadoLoteria res = new ResultadoLoteria("27/11/2016");
        FinalFADA proyecto = new FinalFADA();
        Config cfg = new Config();
        //System.out.println(proyecto.lcs(a));
        //proyecto.inicializarLoteriasEstructuradas(System.getProperty("user.dir") +"/src/data/loterias/loterias.txt");
         proyecto.inicializarLoteriasEstructuradas(cfg.rutaLoterias);
         proyecto.inicializarLoterisSimbolos();
        // System.out.println(proyecto.lcs(proyecto.getLoteriasSimbolos().toArray(new String[proyecto.getLoteriasSimbolos().size()])));
        System.out.println(Arrays.toString(proyecto.getLoteriasSimbolos().toArray(new String[proyecto.getLoteriasSimbolos().size()])));
        String[] b = proyecto.getLoteriasSimbolos().toArray(new String[proyecto.getLoteriasSimbolos().size()]);
        System.out.println(proyecto.lcs(b));
      //  System.out.println(res.getFechaResultado());
        
    }

    /**
     * @return the loteriasSimbolos
     */
    public ArrayList<String> getLoteriasSimbolos() {
        return loteriasSimbolos;
    }

    /**
     * @param loteriasSimbolos the loteriasSimbolos to set
     */
    public void setLoteriasSimbolos(ArrayList<String> loteriasSimbolos) {
        this.loteriasSimbolos = loteriasSimbolos;
    }

    /**
     * @return the loteriasEstructuradas
     */
    public ArrayList<ResultadoLoteria> getLoteriasEstructuradas() {
        return loteriasEstructuradas;
    }

    /**
     * @param loteriasEstructuradas the loteriasEstructuradas to set
     */
    public void setLoteriasEstructuradas(ArrayList<ResultadoLoteria> loteriasEstructuradas) {
        this.loteriasEstructuradas = loteriasEstructuradas;
    }
    
    
}
