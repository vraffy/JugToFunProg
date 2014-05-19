package it.jugtofunprog.ner.model;


public class NamedEntity {

    public enum EntityType {
        LOCATION, PERSON, ORG
    }

    private final EntityType entityType;
    private final String iri;

    public NamedEntity(String iri, EntityType entityType) {
        this.iri = iri;
        this.entityType = entityType;
    }

    public EntityType getType() {
        return entityType;
    }

    public String getIri() {
        return iri;
    }

}
