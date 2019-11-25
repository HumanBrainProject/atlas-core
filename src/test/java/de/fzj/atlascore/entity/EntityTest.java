package de.fzj.atlascore.entity;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Before;
import org.junit.Test;


public class EntityTest {

    private String packageName = "de.fzj.atlascore.entity";
    private Validator validator;
    private PojoClassFilter filterTestClasses = new FilterTestClasses();

    @Before
    public void setup() {
        validator = ValidatorBuilder.create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
    }

    @Test
    public void validate() {


        validator.validate(packageName, filterTestClasses);
    }

    private static class FilterTestClasses implements PojoClassFilter {
        @Override
        public boolean include(PojoClass pojoClass) {
            return !pojoClass.getSourcePath().contains("/test/");
        }
    }

//    @Test
//    public void validateSetterAndGetter() {
//        PojoClass averageOrientationPojo = PojoClassFactory.getPojoClass(AverageOrientation.class);
//
//        Validator pojoValidator = ValidatorBuilder
//                .create()
//                .with(new SetterMustExistRule())
//                .with(new GetterMustExistRule())
//                .with(new SetterTester())
//                .with(new GetterTester())
//                .build();
//
//        pojoValidator.validate(averageOrientationPojo);
//    }
}