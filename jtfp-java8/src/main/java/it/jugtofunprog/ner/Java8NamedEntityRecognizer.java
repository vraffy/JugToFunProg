package it.jugtofunprog.ner;

import static java.lang.Math.max;
import it.jugtofunprog.ner.model.NamedEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8NamedEntityRecognizer implements NamedEntityRecognizer {

    private final NamedEntityRepository neRepo;

    public Java8NamedEntityRecognizer(NamedEntityRepository neRepo) {
        this.neRepo = neRepo;
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens) {
        return extractNamedEntities(tokens, 1);
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens, int ngramSize) {

        List<String> ngrams = createNGrams(tokens, ngramSize);

        return ngrams.stream()
                .flatMap(candidate -> neRepo.recognize(candidate).stream())
                .collect(Collectors.toList());
    }

    private List<String> createNGrams(List<String> tokens, int ngramSize) {

        List<String> ngrams = new ArrayList<>();

        IntStream.iterate(1, n -> n + 1)
                .limit(ngramSize)
                .forEach(i -> ngrams.addAll(slide(tokens, i)));

        return ngrams;
    }

    private List<String> slide(List<String> list, int groupSize) {

        long limit = max(list.size() - groupSize + 1, 0);

        return IntStream.iterate(0, i -> i + 1)
                .limit(limit)
                .mapToObj(i -> group(list, i, groupSize).collect(Collectors.joining(" ")))
                .collect(Collectors.toList());
    }

    private Stream<String> group(List<String> list, int offset, int size) {
        return IntStream.iterate(offset, j -> j + 1)
                .limit(size)
                .filter(j -> j < list.size())
                .mapToObj(list::get);
    }

}
