package com.rain.security.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 想要编写自定义校验注解的逻辑，就要继承ConstraintValidator<T,M>，
 * 这两个范型T代表该类编写的校验逻辑适用与哪个注解，M代表该校验注解能标识在什么类型上，这里限定只能标识在String类型上。
 * 另外需要说明的是，实现了ConstraintValidator的类（本类）无需适用@Component即可加入到Spring容器，
 * 因为该逻辑在ConstraintValidator中已经为我们做好了；由于本类已经是Spring容器的一个组件了，因此它可以注入Spring容器的其他组件。
 *
 * @author 左边
 * @date 2019/8/24 11:58 PM
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {

    // 校验类可以注入Spring容器中的组件，因为它本身也是Spring容器的一个组件。
//    @Autowired
//    private UserService userService;

    /**
     * 校验之前的初始化方法
     * @param myConstraint 自定义校验注解对象
     */
    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("validate init..");
    }

    /**
     * 校验逻辑
     * @param value 注解所标识的字段传入的值
     * @param cvContext 注解的上下文
     * @return 校验结果
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvContext) {
        System.out.println("注解所标识的字段传入的值是：" + value);

        return true;
    }
}
