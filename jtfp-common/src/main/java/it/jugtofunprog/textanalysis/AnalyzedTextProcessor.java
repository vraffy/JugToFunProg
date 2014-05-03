package it.jugtofunprog.textanalysis;

import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Mood;

public interface AnalyzedTextProcessor {

    //@formatter:off
    /** 
     * Estrae il "mood" associato a un testo, in base alla polarità complessiva assegnata dalla fase di analisi.
     * 
     * Tra tutte le annotazioni presenti devono essere prese in considerazione solo quelle di tipo Polarity.
     * Nel caso ce ne sia più di una, si sceglie la prima (nell'ordine restituito da getAnnotations()).
     * Il Mood restituito è calcolato nel seguente modo:
     *    NEGATIVE se la prima Polarity ha un valore compreso tra 0 e 50
     *    POSITIVE se la prima Polarity ha un valore compreso tra 51 e 100
     *    NONE in tutti gli altri casi
     *
     */
    //@formatter:on
    public Mood extractMood(AnalyzedText analyzedText);

}
