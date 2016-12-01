/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfada;

/**
 *
 * @author luisgemc
 */
public class Config {
    public int caracterInicial = 33; //simbolo !
    public String delimitador = ",";
    public String nombreFicheroLoterias = "loterias.txt";
    public String rutaLoterias = System.getProperty("user.dir") + "/src/data/loterias/" + nombreFicheroLoterias;
}
