package za.redbridge.experiment;

import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sim.util.Double2D;
import za.redbridge.experiment.NEATM.NEATMNetwork;
import za.redbridge.experiment.NEATM.sensor.SensorMorphology;
import za.redbridge.simulator.phenotype.Phenotype;
import za.redbridge.simulator.sensor.AgentSensor;

public class NEATMPhenotype implements Phenotype {

    private final NEATMNetwork network;
    private final MLData input;

    private final List<AgentSensor> sensors;

    public NEATMPhenotype(NEATMNetwork network) {
        this.network = network;

        // Initialise sensors
        SensorMorphology morphology = network.getSensorMorphology();
        final int numSensors = morphology.getNumSensors();
        sensors = new ArrayList<>(numSensors);
        for (int i = 0; i < numSensors; i++) {
            sensors.add(morphology.getSensor(i));
        }

        input = new BasicMLData(numSensors);
    }

    @Override
    public List<AgentSensor> getSensors() {
        return sensors;
    }

    @Override
    public Double2D step(List<List<Double>> sensorReadings) {
        final MLData input = this.input;

        for (int i = 0, n = input.size(); i < n; i++) {
            input.setData(i, sensorReadings.get(i).get(0));
        }

        MLData output = network.compute(input);
        return new Double2D(output.getData(0) * 2.0 - 1.0, output.getData(1) * 2.0 - 1.0);
    }

    @Override
    public Phenotype clone() {
        return new NEATMPhenotype(network);
    }

    @Override
    public void configure(Map<String, Object> stringObjectMap) {
        throw new UnsupportedOperationException();
    }
}
