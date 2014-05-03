package it.jugtofunprog.textanalysis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene il risultato dell'analisi linguistica di un testo.
 * 
 */
public class AnalyzedText {

    private final String text;
    private final List<Annotation> annotations;

    public AnalyzedText(final String text) {
        this.text = text;

        annotations = new ArrayList<>();
    }

    /**
     * @return il testo originale
     */
    public String getText() {
        return text;
    }


    /**
     * @return l'elenco delle annotazioni (eventualmente vuoto) estratte dall'analisi linguistica
     */
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * Aggiunge una nuova annotazione al testo
     */
    public AnalyzedText addAnnotation(final Annotation annotation) {
        annotations.add(annotation);
        return this;
    }
}
