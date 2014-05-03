package it.jugtofunprog.textanalysis.model;

/**
 * Rappresenta una generica "annotazione" associata a un testo
 * 
 */
public class Annotation {

    private final int id;
    private final int begin;
    private final int end;

    public Annotation(final int id, final int begin, final int end) {
        this.id = id;

        if (begin < 0 || begin >= end) {
            throw new IllegalArgumentException("begin must be greater than 0 and lesser than end");
        }
        this.begin = begin;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    /**
     * Posizione iniziale (in caratteri) della porzione di testo alla quale si riferisce
     * l'annotazione.
     */
    public int getBegin() {
        return begin;
    }

    /**
     * Posizione immediatamente successiva all'ultimo carattere della porzione di testo alla quale
     * si riferisce l'annotazione.
     */
    public int getEnd() {
        return end;
    }

}
