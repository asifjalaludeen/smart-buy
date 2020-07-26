package com.codaglobal.infrastructure;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Asif Jalaludeen
 *
 */

@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Log
{
}
