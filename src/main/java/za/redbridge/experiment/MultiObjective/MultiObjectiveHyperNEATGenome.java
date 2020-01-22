package za.redbridge.experiment.MultiObjective;

import org.encog.neural.hyperneat.HyperNEATGenome;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.training.NEATLinkGene;
import org.encog.neural.neat.training.NEATNeuronGene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO fill this in
 */

public class MultiObjectiveHyperNEATGenome extends HyperNEATGenome implements MultiObjectiveGenome
{

    private ArrayList<Double> scoreVector;

    private int rank;

    private Double distance;

    /**
     * Construct a genome by copying another.
     *
     * @param other The other genome.
     */
    public MultiObjectiveHyperNEATGenome(final MultiObjectiveHyperNEATGenome other)
    {
        super(other);
        this.scoreVector = new ArrayList<Double>();
        for (double score : other.scoreVector)
        {
            this.scoreVector.add(score);
        }

    }

    /**
     * Create a NEAT gnome. Neuron genes will be added by reference, links will
     * be copied.
     *
     * @param neurons     The neurons to create.
     * @param links       The links to create.
     * @param inputCount  The input count.
     * @param outputCount The output count.
     */
    public MultiObjectiveHyperNEATGenome(final List<NEATNeuronGene> neurons,
                                         final List<NEATLinkGene> links, final int inputCount,
                                         final int outputCount)
    {
        super(neurons, links, inputCount, outputCount);
        this.scoreVector = new ArrayList<Double>();
    }

    /**
     * Create a new genome with the specified connection density. This
     * constructor is typically used to create the initial population.
     *
     * @param rnd               Random number generator.
     * @param pop               The population.
     * @param inputCount        The input count.
     * @param outputCount       The output count.
     * @param connectionDensity The connection density.
     */
    public MultiObjectiveHyperNEATGenome(final Random rnd, final NEATPopulation pop,
                                         final int inputCount, final int outputCount,
                                         double connectionDensity)
    {
        super(rnd, pop, inputCount, outputCount, connectionDensity);
        this.scoreVector = new ArrayList<Double>();

    }

    /**
     * Empty constructor for persistence.
     */
    public MultiObjectiveHyperNEATGenome()
    {
        super();
    }

    public void setScore(int objective, Double score)
    {
        if(objective == scoreVector.size())
        {
            scoreVector.add(score);
        }
        else if(objective<scoreVector.size())
        {
            scoreVector.set(objective,score);
        }

    }

    public ArrayList<Double> getScoreVector(){
        return scoreVector;
    }

    public  void setDistance(Double distance){
        this.distance = distance;
    }

    public  void setRank(int rank){
        this.rank = rank;
    }

    public  double getDistance(){
        return distance;
    }

    public int getRank(){
        return rank;
    }


}
