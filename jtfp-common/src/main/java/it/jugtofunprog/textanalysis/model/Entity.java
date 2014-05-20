package it.jugtofunprog.textanalysis.model;

import it.jugtofunprog.ner.model.NamedEntity.EntityType;

public class Entity extends Annotation {

    private final EntityType type;
    private final String iri;

    public Entity(final int id, final int begin, final int end, final EntityType type, final String iri) {
        super(id, begin, end);

        this.type = type;
        this.iri = iri;
    }

    /**
     * Tipo di entità riconosciuta all'interno del testo.
     */
    public EntityType getType() {
        return type;
    }

    /**
     * IRI associato all'entità riconosciuta all'interno del testo.
     * 
     * @see http://it.wikipedia.org/wiki/Internationalized_Resource_Identifier
     */
    public String getIri() {
        return iri;
    }

}
