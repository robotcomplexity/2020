package za.redbridge.experiment.NEATM.sensor.parameter.spec;

import java.io.Serializable;

public interface Limiter extends Serializable {
    /**
     * Clamps a value to a given range
     * @param oldValue the current value
     * @param newValue the new value
     * @param range the valid range
     * @return the value limited to within its range
     */
    float limitValue(float oldValue, float newValue, Range range);
}
