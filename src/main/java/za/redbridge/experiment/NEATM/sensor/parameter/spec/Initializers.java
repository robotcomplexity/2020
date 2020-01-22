package za.redbridge.experiment.NEATM.sensor.parameter.spec;

import java.util.Random;

public final class Initializers {

    private Initializers() {
    }

    public static Initializer random() {
        return new Initializer() {
            @Override
            public float initializeValue(Range range, Random random) {
                return range.randomValueWithinRange(random);
            }

            @Override
            public boolean shouldCopyExistingValue() {
                return false;
            }
        };
    }

    public static Initializer copyExistingOrRandom() {
        return new Initializer() {
            @Override
            public float initializeValue(Range range, Random random) {
                return range.randomValueWithinRange(random);
            }

            @Override
            public boolean shouldCopyExistingValue() {
                return true;
            }
        };
    }

}
