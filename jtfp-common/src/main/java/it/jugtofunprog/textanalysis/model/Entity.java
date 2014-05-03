package it.jugtofunprog.textanalysis.model;

public class Entity extends Annotation {

    public enum EntityType {
        LOCATION, PERSON
    }

    private final String iri;
    private final EntityType type;

    public Entity(final int id, final int begin, final int end, final EntityType type, final String iri) {
        super(id, begin, end);

        this.type = type;
        this.iri = iri;
    }

    /**
     * IRI associato all'entità riconosciuta all'interno del testo.
     * 
     * @see http://it.wikipedia.org/wiki/Internationalized_Resource_Identifier
     */
    public String getIri() {
        return iri;
    }

    /**
     * Tipo di entità riconosciuta all'interno del testo.
     */
    public EntityType getType() {
        return type;
    }

}
