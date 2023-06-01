package ru.qiwi.pair;

public final class Pair<One, Two> {
    private One first;
    private Two second;

    private Pair() {}
    private Pair(One first, Two second) {
        this.first = first;
        this.second = second;
    }
    public static <One, Two>  Pair<One, Two> of(One first, Two second) {
        Pair pair = new Pair(first, second);
        return pair;
    }

    public One getFirst() {
        return first;
    }

    public Two getSecond() {
        return second;
    }

    public boolean equals(Pair obj) {
        return (this.first == obj.first &&
                this.second == obj.second);
    }
}

