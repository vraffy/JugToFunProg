package it.jugtofunprog.textanalysis.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Contiene il risultato dell'analisi linguistica di un testo.
 * 
 */
public class AnalyzedText {

    private final Collection<Annotation> annotations;
    private final String text;

    public AnalyzedText(final String text) {
        this.text = text;

        annotations = new ArrayList<>();
    }

    /**
     * Aggiunge una nuova annotazione al testo
     */
    public AnalyzedText addAnnotation(final Annotation annotation) {
        annotations.add(annotation);
        return this;
    }

    /**
     * @return l'elenco delle annotazioni (eventualmente vuoto) estratte dall'analisi linguistica
     */
    public Collection<Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * @return il testo originale
     */
    public String getText() {
        return text;
    }

}
