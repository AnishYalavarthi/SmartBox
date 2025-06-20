package smartbox.components;

import smartbox.*;
import smartbox.components.ICalculator;

import java.util.Set;

public class Calculator extends Component implements ICalculator {

    public Calculator() { super(); }

    public Double add(Double x, Double y) {
        return x + y;
    }

    public Double mul(Double x, Double y) {
        return x * y;
    }

    public Double sub(Double x, Double y) {
        return x - y;
    }

    public Double div(Double x, Double y) { return x / y; }

}