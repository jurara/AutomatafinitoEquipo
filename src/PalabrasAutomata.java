
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Monroy
 */
public class PalabrasAutomata {

    String palabras[];
    int matrizEstados[][];
    String alfabeto[];
    int tope = 0;
    int fila = 0;
    int totalLetras = 0;
    int estadosFinales[];

    PalabrasAutomata(String ar[]) {
        palabras = ar;
        alfabeto = new String[60];
        estadosFinales=new int[1];
        ordenarPalabras();
        obtenerAlfabeto();
        ordenarLetras();
        llenaMatriz();
    }

    public void ordenarPalabras() {
        for (int i = 1; i <= palabras.length; i++) {
            for (int j = 0; j < palabras.length - i; j++) {
                if (palabras[j].compareTo(palabras[j + 1]) > 0) {
                    String tem = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = tem;
                }
            }
        }
    }

    public void llenaMatriz() {
        matrizEstados = new int[totalLetras][alfabeto.length];

        for (int j = 0; j < totalLetras; j++) {
            for (int i = 0; i < tope; i++) {
                matrizEstados[j][i] = -1;
            }
        }

        int estadoTemporal = 0;
        int estadoActual = 0;

        for (int i = 0; i < palabras.length; i++) {
            
            for (int j = 0; j < palabras[i].length(); j++) {
                int columnaActual = getColumna(""+palabras[i].charAt(j));
                if (j == 0) 
                {
                    if (matrizEstados[0][columnaActual] == -1) {
                        if(palabras[i].length()==1)
                        {
                            matrizEstados[0][columnaActual] = -2;
                        }
                        else
                        {
                        estadoActual++;
                        matrizEstados[0][columnaActual] = estadoActual;
                        }
                    } else {
                        estadoTemporal = 0;
                        while (matrizEstados[estadoTemporal][columnaActual] > -1 && j < palabras[i].length()) {

                            j++;
                            estadoTemporal = matrizEstados[estadoTemporal][columnaActual];
                            columnaActual = getColumna(""+palabras[i].charAt(j));
                            System.out.println("palabra actual: " + palabras[i] + " letra: " + j + " estadoTem: " + estadoTemporal+" estado actual: "+estadoActual);
                        }
                        /*if(estadoTemporal>estadoActual)//NO CREO QUE ESTO SE CUMPLA
                         {
                         estadoActual=estadoTemporal;
                         }*/
                        if (j < palabras[i].length()) {
                            if (j == palabras[i].length() - 1) {
                                matrizEstados[estadoTemporal][columnaActual] = -2;

                            }
                            else
                            {
                                if(matrizEstados[estadoTemporal][columnaActual]==-2)
                                {
                                    int temporal[]=estadosFinales;
                                    estadosFinales=new int[temporal.length+1];
                                    for(int k=0;k<temporal.length;k++)
                                    {
                                      estadosFinales[k]=temporal[k];
                                    }
                                    estadosFinales[estadosFinales.length-2]=estadoActual+1;
                                    matrizEstados[estadoTemporal][columnaActual] = estadoActual + 1;
                                    estadoActual++;
                                }
                                else
                                {
                                    
                                matrizEstados[estadoTemporal][columnaActual] = estadoActual + 1;
                                estadoActual++;
                                }
                            }
                        }

                    }
                } else //no es la primera letra, ya esta calculada la columna actual
                {
                    if (j == palabras[i].length() - 1) {
                        matrizEstados[estadoActual][columnaActual] = -2;

                    } else {
                        matrizEstados[estadoActual][columnaActual] = estadoActual + 1;
                        estadoActual++;
                    }

                }
            }
            //verMatriz();
        }
        //agregando los estados finales        
        estadoActual++;
        for (int i = 0; i < matrizEstados.length; i++) {
            for (int j = 0; j < tope; j++) {
                if (matrizEstados[i][j] == -2) {
                    matrizEstados[i][j] = estadoActual;
                }
            }
        }
        estadosFinales[estadosFinales.length-1]=estadoActual;
        //eliminando filas que no se ocupan
        int matrizTemporal[][]=new int[estadoActual+1][tope];
        for (int j = 0; j < estadoActual+1; j++) {
            for (int i = 0; i < tope; i++) {
                matrizTemporal[j][i] = matrizEstados[j][i];
            }
        }
        matrizEstados=matrizTemporal;
        String temm[]=new String[tope];
        for(int i=0;i<temm.length;i++)
        {
          temm[i]=alfabeto[i];
        }
        alfabeto=temm;
        

    }

    public int getColumna(String c) {
        for (int i = 0; i < alfabeto.length; i++) {
            if (c.equals(alfabeto[i])) {
                return i;
            }
        }
        return -1;
    }

    public void ordenarLetras() {
        for (int i = 1; i <= tope; i++) {
            for (int j = 0; j < tope - i; j++) {
                if (alfabeto[j].compareTo(alfabeto[j + 1])>0) {
                    String tem = alfabeto[j];
                    alfabeto[j] = alfabeto[j + 1];
                    alfabeto[j + 1] = tem;
                }

            }
        }
    }

    public void obtenerAlfabeto() {
        for (int i = 0; i < palabras.length; i++) {
            for (int j = 0; j < palabras[i].length(); j++) {
                insertaLetra(""+palabras[i].charAt(j));
                totalLetras++;
            }
        }
    }

    public void insertaLetra(String letra) {
        for (int i = 0; i <= tope; i++) {
            if (i == tope) {
                alfabeto[i] = letra;
                tope++;
                //System.out.println(tope + " " + letra);
                break;
            } else {
                if (letra.equals(alfabeto[i])) {
                    // System.out.println("ya stuvo");
                    break;
                }
            }
        }
    }

    public void verPalabras() {
        String tem = "";
        for (int i = 0; i < palabras.length; i++) {
            tem = tem + palabras[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, tem);
    }

    public void verAlfabeto() {
        String tem = "";
        for (int i = 0; i < tope; i++) {
            tem = tem + alfabeto[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, tem);
    }
    public void verFinales() {
        String tem = "";
        for (int i = 0; i < estadosFinales.length; i++) {
            tem = tem + estadosFinales[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, tem);
    }

    public void verMatriz() {
        String tem = "";
        for (int i = 0; i < tope; i++) {
            tem = tem + alfabeto[i] + "   ";
        }
        tem = tem + "\n";
        for (int i = 0; i < matrizEstados.length; i++) {
            for (int j = 0; j < tope; j++) {
                tem = tem + matrizEstados[i][j] + " ";
            }
            tem = tem + "\n";
        }
        JOptionPane.showMessageDialog(null, tem);
    }

    public static void main(String[] arrr) {
        String arr[] = {"alfabeto", "aritmetico", "automata", "bit", "boolean", "byte", "char", "comentarios", "con", "digitos", "double", "estado", "estados", "final", "float", "hasta", "identificador", "inicial", "int", "logico", "mayusculas", "minusculas", "numero", "operadores", "palabras", "regla", "relacional", "reservadas", "short"};
        //String arr[] = {"alf", "ari"};//, "automata", "bit", "boolean", "byte"};
        PalabrasAutomata prueba = new PalabrasAutomata(arr);
        EvaluadorAutomata reservadas = new EvaluadorAutomata(prueba.alfabeto,prueba.totalLetras,prueba.matrizEstados,0, prueba.estadosFinales);
//        prueba.verMatriz();
//        prueba.verFinales();
        JOptionPane.showMessageDialog(null, reservadas.esValido("comentarioss"));
        

    }
}
