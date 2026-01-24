package br.com.band.band;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithStructureTest {

    @Test
    void verifyModularStructure() {
        ApplicationModules.of(Application.class).verify();
    }
}
