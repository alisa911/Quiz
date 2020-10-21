package quiz.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy=UniqueQuestionValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueQuestion {
    String message() default "Такой вопрос уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
