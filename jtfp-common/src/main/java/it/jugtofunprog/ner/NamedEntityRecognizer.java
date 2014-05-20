package it.jugtofunprog.ner;

import it.jugtofunprog.ner.model.NamedEntity;

import java.util.Collection;
import java.util.List;

public interface NamedEntityRecognizer {

    //@formatter:off
    /** 
     * Esamina una lista sequenziale di "token" estratti da un testo e restituisce l'elenco di tutte le
     * NamedEntity riconosciute, mantenendo le eventuali ambiguità. 
     */
    //@formatter:on
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens);

    //@formatter:off
    /** 
     * Esamina una lista sequenziale di "token" estratti da un testo e restituisce l'elenco di tutte le
     * NamedEntity riconosciute, mantenendo le eventuali ambiguità. 
     * Riconosce anche entità costituite da più token consecutivi, fino a un massimo indicato dal valore
     * del parametro "ngramSize".
     */
    //@formatter:on
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens, int ngramSize);

}
