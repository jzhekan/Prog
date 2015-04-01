/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
/**
 *
 * @author IVoitsekhovskyi
 */
public @interface TestAnnotation {
    int a() default 2;
    int b() default 5;
    
}
