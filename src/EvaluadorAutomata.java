
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Monroy
 */
public class EvaluadorAutomata {

    String alfabeto[];
    int estados;
    int matrizEstados[][];
    int estadosFinales[];
    int estadoInicial;
    private String cadena;

    EvaluadorAutomata(String alf[], int est, int matEst[][], int inicial, int estFin[]) {
        alfabeto = alf;
        estados = est;
        matrizEstados = matEst;
        estadoInicial = inicial;
        estadosFinales = estFin;

    }

    boolean esValido(String cad) {
        cadena = cad;

        return analizador(0, 0);
    }

    private boolean analizador(int indice, int est) {

        if (indice == cadena.length()) {
            if (esFin(est)) {
                return true;
            } else {
                return false;
            }
        } else {

            int columna = getColumna("" + cadena.charAt(indice));
            if (columna == -1) {
                return false;
            } else {
                if (matrizEstados[est][columna] == -1) {
                    return false;
                } else {
                    return analizador(indice + 1, matrizEstados[est][columna]);
                }
            }
        }

    }

    boolean esFin(int est) {

        for (int i = 0; i < estadosFinales.length; i++) {
            if (estadosFinales[i] == est) {
                return true;
            }
        }

        return false;
    }

    int getColumna(String letra) {
        for (int i = 0; i < alfabeto.length; i++) {
            String temp[] = alfabeto[i].split(" ");
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].equals(letra)) {
                    return i;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        /*int matriz[][] = {{1, -1},
         {1, 1}};
         String alf[] = {"a", "b"};
         int inicial = 0;
         int finales[] = {1};
         int estados = 1;*/

        String alf[];
        alf = new String[3];
        String mayusculas = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
        String minusculas = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        String digitos = "0 1 2 3 4 5 6 7 8 9";
        String simbolos = "+ - * / = ( ) { } [ ] < > ' “ ¡ ! @ # $ % & _ | ^ ~ \\ . , ; : ¿ ?";
        
        alf[0]=mayusculas;
        alf[1]=minusculas;
        alf[2]=digitos;
        
        
        
        //identificadodes
        int estadosID[] = {0, 1};
        int estIniID = 0;
        int estFinID[] = {1};
        int MatrizID[][] = new int[2][3];
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 3; k++) {
                MatrizID[i][k] = -1;
            }
        }
        MatrizID[0][0] = 1;
        MatrizID[1][0] = 1;
        MatrizID[1][1] = 1;
        MatrizID[1][2] = 1;

        EvaluadorAutomata identificador = new EvaluadorAutomata(alf, 2, MatrizID, estIniID, estFinID);
        //JOptionPane.showMessageDialog(null, "" + identificador.esValido("Aweweqweqwe11"));
        
        
        //cadena
         
         int estIniCad=0;
         int estFinCad[]={3};
         int MatrizCad[][]=new int[4][5];
         for(int i=0;i<4;i++){
         for(int k=0;k<5;k++){
         MatrizCad[i][k]=-1;
         }
         }
         MatrizCad[0][0]=1;
         MatrizCad[1][1]=2;
         MatrizCad[1][2]=2;
         MatrizCad[1][3]=2;
         MatrizCad[1][4]=2;
         MatrizCad[2][0]=3;
         MatrizCad[2][1]=2;
         MatrizCad[2][2]=2;
         MatrizCad[2][3]=2;
         MatrizCad[2][4]=2;
         
         alf=null;
         String apostrofe="'";
         alf=new String[]{apostrofe,minusculas,mayusculas,digitos,simbolos};

         EvaluadorAutomata cadenas = new EvaluadorAutomata(alf,4,MatrizCad,estIniCad, estFinCad);
         JOptionPane.showMessageDialog(null, ("'lk//\\mlklskdf'"));
         
         /////////automata palabras reservadas
         alf=new String[]{"a","b","c","d","e","f","g","h","i","l","m","n","o","p","r","s","t","u","v","y"};
         JOptionPane.showMessageDialog(null, alf.length);
         
         
         

    }
}

/**
 * digitos="0,1,2,3,4,5,6,7,8,9,0" simbolosEspeciales="!,#" String arreglo[5];
 * arreglo[0]=minusculas; arreglo[1]=minusculas+","+mayusculas arreglo[1]="n"
 *
 */
