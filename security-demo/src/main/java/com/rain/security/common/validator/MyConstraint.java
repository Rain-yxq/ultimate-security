package com.rain.security.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义validate校验注解
 *
 * @author 左边
 * @date 2019/8/24 11:56 PM
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    /** 自定义校验注解中必须包含下边三个属性 */

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
