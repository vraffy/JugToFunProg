package it.jugtofunprog.ner;

import it.jugtofunprog.ner.model.NamedEntity;

import java.util.Collection;

public interface NamedEntityRepository {

    //@formatter:off
    /** 
     * Cerca di associare una stringa di testo a una o più (nel caso di termini ambigui) "entità" note.
     * Se non riconosce alcuna istanza associabile, restituisce una collezione vuota.
     */
    //@formatter:on
    public Collection<NamedEntity> recognize(String candidate);


}
