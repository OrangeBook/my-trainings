package orange.book.lifecycle.practice.infrastructure;

import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PropertyInjectorBeanPostProcessor implements BeanPostProcessor {

  @Autowired
  RandomIntBeanFactoryPostProcessor randomIntBeanFactoryPostProcessor;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

    Class<?> beanClass = bean.getClass();
    String propertyValue = randomIntBeanFactoryPostProcessor.classWithAnnotationsWhereNeedToInjectSomeFields.get(beanClass);
    if(propertyValue != null) {
      Class<?> clazz = bean.getClass();
      try {
        Field field = clazz.getDeclaredField("wheals");
        field.setAccessible(true);
        field.set(bean, Integer.parseInt(propertyValue));
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }
}
