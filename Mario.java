package ia.mario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Mario {

    public static void main(String[] args) throws Exception {         
        try {
            
            File world = new File("world (1).txt");
            FileReader fr = new FileReader(world);
            BufferedReader br = new BufferedReader(fr);

            // Leemos el ancho y el alto de la matriz
            int numColumnas = Integer.parseInt(br.readLine());
            int numFilas = Integer.parseInt(br.readLine());

            // Creamos la matriz con las dimensiones especificadas
            int marioWorld[][] = new int[numFilas][numColumnas];

            // Leemos el resto del archivo para llenar la matriz
            for (int row = 0; row < numFilas; row++) {
                String[] values = br.readLine().split(" ");
                for (int col = 0; col < numColumnas; col++) {
                    marioWorld[row][col] = Integer.parseInt(values[col]);
                }
            }

            // Ahora, marioWorld es tu matriz con la representación del mundo del archivo.

            // Puedes imprimir la matriz para verificar que se ha cargado correctamente.
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    System.out.print(marioWorld[i][j] + " ");
                }
                System.out.println();
            }
            
            FitnessCalc calc = new FitnessCalc(marioWorld);
            char[] caracteres = {'D', 'J', 'D', 'D', 'S', 'D', 'D', 'D', 'D', 'D', 'D', 'S', 'D', 'D'};
            Individual camino = new Individual(caracteres);
            int calcing = calc.getFitness(camino);
            System.out.println(calcing);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
