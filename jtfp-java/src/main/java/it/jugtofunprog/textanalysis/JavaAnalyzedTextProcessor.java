package it.jugtofunprog.textanalysis;

import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Annotation;
import it.jugtofunprog.textanalysis.model.Mood;
import it.jugtofunprog.textanalysis.model.Polarity;

import java.util.Iterator;
import java.util.List;

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


}
