
package ia.mario;

import java.util.Random;

public class Individual {
    static int defaultGeneLength = 10;
    public char[] genes = new char[defaultGeneLength];
    // fitness maximal
    private int fitness = Integer.MAX_VALUE;
    Random random = new Random();

    public Individual(int length) {
        defaultGeneLength = length;
        genes = new char[defaultGeneLength];
        fitness = Integer.MAX_VALUE;
    }
    
    public Individual(char[] camino) {
        // METODO DE TEST BORRAR AL FINAL SUPONGO
        defaultGeneLength = camino.length;
        genes = camino;
        fitness = Integer.MAX_VALUE;
    }
    
    public Individual(Individual o){
        defaultGeneLength = o.size();
        genes = new char[defaultGeneLength];
        for(int i=0;i<o.size();i++){
            genes[i] = o.getGene(i);
        }
        fitness = Integer.MAX_VALUE;
    }
	
    // Individuo al azar... valor de bits se eligen azarosamente
    public void generateIndividual(){
        for(byte i=0;i<size();i++){
            String caminos = "DISJ";
            int randomIndex = random.nextInt(caminos.length());
            genes[i] = caminos.charAt(randomIndex);
        }
    }

    // seteadores y obtenedores
    public char getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, char value) {
        genes[index] = value;
        fitness = Integer.MAX_VALUE;
    }

    /* Metodos publicos */
    public int size() {
        return genes.length;
    }

    public int getFitness(FitnessCalc fit) {
        if (fitness == Integer.MAX_VALUE) {
            fitness = fit.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        int suma = 0;
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i)+",";
            suma += getGene(i);
        }
        geneString += " = "+suma+"/"+fitness;
        return geneString;
    }
}
