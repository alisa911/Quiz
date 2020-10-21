package quiz.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy=TrueAnswerValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OneTrueAnswer {
    String message() default "Нужно добавить один правильный ответ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
