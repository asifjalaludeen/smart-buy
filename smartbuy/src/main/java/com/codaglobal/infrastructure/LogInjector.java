package com.codaglobal.infrastructure;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @author Asif Jalaludeen
 *
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LogInjector implements BeanPostProcessor
{

  @Override
  public Object postProcessAfterInitialization( Object bean, String beanName ) throws BeansException
  {
    return bean;
  }

  @Override
  public Object postProcessBeforeInitialization( final Object bean, String name ) throws BeansException
  {
    ReflectionUtils.doWithFields( bean.getClass(), new ReflectionUtils.FieldCallback() {
      public void doWith( Field field ) throws IllegalArgumentException, IllegalAccessException
      {
        // make the field accessible if defined private
        ReflectionUtils.makeAccessible( field );
        if ( field.getAnnotation( Log.class ) != null )
        {
          Logger log = LoggerFactory.getLogger( bean.getClass() );
          field.set( bean, log );
        }
      }
    } );
    return bean;
  }
}
