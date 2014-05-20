package it.jugtofunprog.textanalysis;

import it.jugtofunprog.ner.model.NamedEntity.EntityType;
import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Annotation;
import it.jugtofunprog.textanalysis.model.Entity;
import it.jugtofunprog.textanalysis.model.Mood;
import it.jugtofunprog.textanalysis.model.Polarity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JavaAnalyzedTextProcessor implements AnalyzedTextProcessor {

    @Override
    public Mood extractMood(final AnalyzedText analyzedText) {
        List<Annotation> annotations = analyzedText.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof Polarity) {
                Polarity pol = (Polarity) annotation;
                return pol.getMood();
            }
        }
        return Mood.NONE;
    }

    public Mood extractMood_UGLIEST(final AnalyzedText analyzedText) {
        List<Annotation> annotations = analyzedText.getAnnotations();
        Mood mood = null;
        boolean found = false;
        Iterator<Annotation> it = annotations.iterator();
        while (it.hasNext() && found == false) {
            Annotation a = it.next();
            if (a instanceof Polarity) {
                mood = ((Polarity) a).getMood();
                found = true;
            }
        }

        if (mood == null) {
            mood = Mood.NONE;
        }

        return mood;
    }

    @Override
    public Map<Integer, Annotation> indexPersons(AnalyzedText analyzedText) {
        List<Annotation> annotations = analyzedText.getAnnotations();
        return indexEntities(EntityType.PERSON, annotations);
    }


    @Override
    public Map<Integer, Annotation> indexLocations(AnalyzedText analyzedText) {
        List<Annotation> annotations = analyzedText.getAnnotations();
        return indexEntities(EntityType.LOCATION, annotations);
    }

    private Map<Integer, Annotation> indexEntities(EntityType type, List<Annotation> annotations) {
        Map<Integer, Annotation> entities = new HashMap<>();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Entity) {
                Entity en = (Entity) annotation;
                if (en.getType() == type) {
                    entities.put(en.getBegin(), en);
                }
            }
        }
        return entities;
    }

    @Override
    public Map<Integer, Annotation> indexShortEntities(AnalyzedText analyzedText, int maxLength) {
        Map<Integer, Annotation> entities = new HashMap<>();
        for (Annotation annotation : analyzedText.getAnnotations()) {
            if (annotation instanceof Entity) {
                Entity en = (Entity) annotation;
                if ((en.getEnd() - en.getBegin()) < maxLength) {
                    entities.put(en.getBegin(), en);
                }
            }
        }

        return entities;
    }


    @Override
    public Collection<String> mostFrequentEntities(AnalyzedText analyzedText) {
        Collection<String> iris = new ArrayList<>();
        Map<String, Integer> iri2Freq = new HashMap<>();
        int maxFreq = 0;
        for (Annotation annotation : analyzedText.getAnnotations()) {
            if (annotation instanceof Entity) {
                Entity en = (Entity) annotation;
                String iri = en.getIri();
                if (iri2Freq.containsKey(iri)) {
                    Integer freq = iri2Freq.get(iri);
                    freq++;
                    iri2Freq.put(iri, freq);
                    maxFreq = Math.max(maxFreq, freq);
                } else {
                    iri2Freq.put(iri, 1);
                    maxFreq = Math.max(maxFreq, 1);
                }
            }
        }

        for (Entry<String, Integer> iriAndFreq : iri2Freq.entrySet()) {
            int freq = iriAndFreq.getValue();
            if (freq == maxFreq) {
                iris.add(iriAndFreq.getKey());
            }
        }
        return iris;
    }


}
