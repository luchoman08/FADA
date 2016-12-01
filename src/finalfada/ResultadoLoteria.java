/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfada;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 *
 * @author luisgemc
 */
public class ResultadoLoteria {
   private Date fechaResultado;
   private int[] resultados;
   private String resultadosSimbolos;
   Config cfg = new Config();
   public ResultadoLoteria(){
       this.fechaResultado = new Date();
       this.resultados = new int[8];
       this.inicializarResultadosString();
   }

   public ResultadoLoteria(String date, int[] newResultados) throws ParseException{
       DateFormat format = new SimpleDateFormat("d/MM/yyyy", Locale.ENGLISH);
       this.fechaResultado =  format.parse(date);
       this.resultados = newResultados;
       this.inicializarResultadosString();
   }
   public ResultadoLoteria(String infoCSV) throws ParseException{ //recibe la linea 27/01/2001,1,2,5,20,27,33 y se inicializa con esta
       this.fechaResultado = new Date();
       this.resultados = new int[8];
       StringTokenizer st = new StringTokenizer(infoCSV,cfg.delimitador);
       for (int i = -1; st.hasMoreElements(); i++){
           switch(i){
               case -1: this.setFechaResultado(st.nextToken());break;
               default: this.resultados[i] = Integer.parseInt(st.nextToken());break;
           }
       }
       this.inicializarResultadosString();
   }
      public void printInfo()  { //recibe la linea 27/01/2001,1,2,5,20,27,33 y se inicializa con esta
       System.out.println(this.fechaResultado+"," + Arrays.toString(this.resultados)+ " " +  this.getResultadosSimbolos());
   }
    /**
     * @return the fechaResultado
     */
    public Date getFechaResultado() {
        return fechaResultado;
    }

    /**
     * @param fechaResultado the fechaResultado to set
     */
    public void setFechaResultado(Date fechaResultado) {
        this.fechaResultado = fechaResultado;
    }
    public void setFechaResultado(String fechaResultado) throws ParseException { //fecha = dd/MM/yyyy
       DateFormat format = new SimpleDateFormat("d/MM/yyyy", Locale.ENGLISH);
       this.fechaResultado =  format.parse(fechaResultado);
       
    }
    /**
     * @return the resultados
     */
    public int[] getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(int[] resultados) {
        this.resultados = resultados;
    }
      public void inicializarResultadosString(){
       String stemporal = "";
       for(int i = 0; i < 8; i++){
           char ctemporal = (char) (cfg.caracterInicial + this.resultados[i]);
           stemporal = stemporal + ctemporal;
       }
       this.setResultadosSimbolos(stemporal);
} 

/*
ya se debe haber inicializado los resultados enteros
*/

    /**
     * @return the resultadosSimbolos
     */
    public String getResultadosSimbolos() {
        return resultadosSimbolos;
    }

    /**
     * @param resultadosSimbolos the resultadosSimbolos to set
     */
    public void setResultadosSimbolos(String resultadosSimbolos) {
        this.resultadosSimbolos = resultadosSimbolos;
    }
      
   }