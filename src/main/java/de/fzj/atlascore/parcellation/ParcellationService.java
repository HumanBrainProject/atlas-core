package de.fzj.atlascore.parcellation;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ParcellationService {

    public List<Parcellation> getParcellations() {
        return Arrays.asList(
                new Parcellation("Par1"),
                new Parcellation("Par2"),
                new Parcellation("Par3")
        );
    }

    public Parcellation getParcellationByName(String name) {
        return new Parcellation(name);
    }
}
