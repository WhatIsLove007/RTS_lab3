//IV-73 Nahornyi V.V.
package genetic.algo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgo {

    public class Gene {
        private int x1, x2, x3, x4;
        private int fitness;

        public Gene(int x1, int x2, int x3, int x4) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
            this.fitness = Integer.MAX_VALUE;
        }

        int getFitness(){
            return fitness;
        }
    }

    private int a, b, c, d, y;
    private int populationSize = 2048;
    private double mutationRate = 0.25;
    private long numberMutation = 0;
    private double elitRate = 0.10;
    private int maxIterations = 10000;

    private List<Gene> population = new ArrayList<>(populationSize);


    public GeneticAlgo(int a, int b, int c, int d, int y) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.y = y;

        Random rand = new Random();
        for (Gene p: population){
            p = new Gene(rand.nextInt(y/2), rand.nextInt(y/2), rand.nextInt(y/2),
                    rand.nextInt(y/2));
        }
    }
	
    public String solution(){
        Random rand = new Random();
        int iterations = 0, i1, i2;

        while (iterations < maxIterations) {
            // calculating fitness for each gene
            for (Gene p: population) {
                p.fitness = Math.abs(y - equation(p));
            }

            Collections.sort(population, new Comparator<Gene>(){
               public int compare(Gene o1, Gene o2){
                   return Integer.compare(o1.fitness, o2.fitness);
               }
            });

            if (population.get(0).fitness == 0){
                return String.format("Solution of this equation is \n[ %1$d, %2$d, %3$d, %4$d] with number of mutations %5$d", population.get(0).x1,
                        population.get(0).x2, population.get(0).x3, population.get(0).x4, numberMutation);

            }
            else {
                int elitSize = (int) (population.size() * elitRate);

                for (int i = elitSize; i < population.size(); i++) {
                    i1 = rand.nextInt(population.size());
                    i2 = rand.nextInt(population.size());

                    switch (rand.nextInt(4)) {
                        case 0:
                           population.get(i1).x1 = population.get(i2).x1;
                           population.get(i1).x2 = population.get(i2).x2;
                           population.get(i1).x3 = population.get(i2).x3;
                           population.get(i1).x4 = population.get(i2).x4;
                           break;

                        case 1:
                            if (rand.nextInt(2) == 0){
                                population.get(i1).x2 = population.get(i2).x2;
                                population.get(i1).x3 = population.get(i2).x3;
                                population.get(i1).x4 = population.get(i2).x4;
                            }
                            else {
                                population.get(i1).x1 = population.get(i2).x1;
                            }
                            break;

                        case 2:
                            if (rand.nextInt(2) == 0){
                                population.get(i1).x3 = population.get(i2).x3;
                                population.get(i1).x4 = population.get(i2).x4;
                            }
                            else {
                                population.get(i1).x1 = population.get(i2).x1;
                                population.get(i1).x2 = population.get(i2).x2;
                            }
                            break;

                        case 3:
                            if (rand.nextInt(2) == 2) population.get(i1).x4 = population.get(i2).x4;
                            else {
                                population.get(i1).x1 = population.get(i2).x1;
                                population.get(i1).x2 = population.get(i2).x2;
                                population.get(i1).x3 = population.get(i2).x3;
                            }
                            break;
                    }

                    if (rand.nextInt(100) * mutationRate < 100 * mutationRate){
                        switch (rand.nextInt(4)) {
                            case 0:
                                population.get(i1).x1 = rand.nextInt(y/2);
                                mutationRate++;
                                break;
                            case 1:
                                population.get(i1).x2 = rand.nextInt(y/2);
                                mutationRate++;
                                break;
                            case 2:
                                population.get(i1).x3 = rand.nextInt(y/2);
                                mutationRate++;
                                break;
                            case 3:
                                population.get(i1).x4 = rand.nextInt(y/2);
                                mutationRate++;
                                break;
                        }
                    }
                }
            }
        }
        return "Out of iterations maximum!";
    }

    private int equation(Gene gene){
        return a * gene.x1 + b * gene.x2 + c * gene.x3 + d * gene.x4;
    }


}
