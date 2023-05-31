package ru.qiwi.fortytwo;

public class FortyTwo extends Number implements Comparable<FortyTwo>, TheUltimateQuestionOfLifeTheUniverseAndEverything {

    private final int value = 42;

    @Override
    public int compareTo(FortyTwo o) {
        if(value == o.value)
            return 0;
        else if(value > o.value)
            return 1;
        else
            return -1;
    }
    @Override
    public int intValue() {
        return value;
    }
    @Override
    public long longValue() {
        return value;
    }
    @Override
    public float floatValue() {
        return value;
    }
    @Override
    public double doubleValue() {
        return value;
    }

    public void getAnswer() {
        System.out.println(value);
    }
}
