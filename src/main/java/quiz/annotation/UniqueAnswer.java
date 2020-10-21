package quiz.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy=UniqueAnswerValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAnswer {
    String message() default "Такой ответ у этого вопроса уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
