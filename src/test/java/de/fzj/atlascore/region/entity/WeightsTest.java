package de.fzj.atlascore.region.entity;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WeightsTest {

    @Test
    public void testGetterAndSetter() {
        PojoClass pojoClass = PojoClassFactory.getPojoClass(Weights.class);

        Validator validator = ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new EqualsAndHashCodeMatchRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();

        validator.validate(pojoClass);
    }

    @Test
    public void testEquals() {
        Weights w1 = new Weights("node", 1.0);
        Weights w2 = new Weights("node", 1.0);
        Weights w3 = new Weights("node", 2.0);
        Weights w4 = new Weights("node1", 1.0);
        Weights w5 = new Weights("node1", 2.0);

        assertEquals(w1, w2);
        assertNotEquals(w1, w3);
        assertNotEquals(w1, w4);
        assertNotEquals(w1, w5);
    }

    @Test
    public void testHashCode() {
        Weights w1 = new Weights("node", 1.0);
        Weights w2 = new Weights("node", 2.0);

        assertEquals(Objects.hash(w1.getNode(), w1.getWeight()), w1.hashCode());
        assertEquals(Objects.hash(w2.getNode(), w2.getWeight()), w2.hashCode());
        assertNotEquals(Objects.hash(w1.getNode(), w1.getWeight()), w2.hashCode());
    }
}
