package ia.mario;

public class Population {
    Individual[] individuals;

    // Crea una poblacion
    public Population(int populationSize, boolean initialise, int length) {
	individuals = new Individual[populationSize];
        // Inicializa Poblacion
        if (initialise) {
            // Crea individuos
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual(length);
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    // Obtenedores
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest(FitnessCalc fit) {
        Individual fittest = individuals[0];
        // Encuentra el mejor individuo (en este caso, minimizamos
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness(fit) >= getIndividual(i).getFitness(fit)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    // Obtiene tamano de poblacion
    public final int size() {
        return individuals.length;
    }

    // Almacena individuos en la poblacion
    public final void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}