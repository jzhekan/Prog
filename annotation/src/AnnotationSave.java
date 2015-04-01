/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value=ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
/**
 *
 * @author IVoitsekhovskyi
 */
public @interface AnnotationSave {
    String passSave() default "D:\\test.txt";
    String nameMethod() default "save";
}