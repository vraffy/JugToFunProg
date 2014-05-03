package it.jugtofunprog.textanalysis.model;

public class Polarity extends Annotation {

    private final int value;

    public Polarity(final int id, final int begin, final int end, final int value) {
        super(id, begin, end);

        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("value must be an integer between 0 and 100");
        }
        this.value = value;
    }


    /**
     * Valore che rappresenta l'intensità della polarità associata a un testo: 0 = massimo della
     * negatività; 100 = massimo della positività.
     */
    public int getValue() {
        return value;
    }



}
