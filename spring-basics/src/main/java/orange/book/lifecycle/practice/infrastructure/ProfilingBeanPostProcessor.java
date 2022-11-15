package orange.book.lifecycle.practice.infrastructure;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import orange.book.lifecycle.practice.annotation.EnableLogging;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProfilingBeanPostProcessor implements BeanPostProcessor {

  Map<String, Class<?>> beansToProfileMap = new HashMap<>();

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
  Class<?> clazz = bean.getClass();
  Annotation annotation = clazz.getDeclaredAnnotation(EnableLogging.class);
  if (annotation != null) {
    beansToProfileMap.put(beanName, bean.getClass());
  }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    Class<?> clazz =  beansToProfileMap.get(beanName);
    if ( clazz != null) {
      return Proxy.newProxyInstance(bean.getClass().getClassLoader(), clazz.getInterfaces(),
          new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              System.out.println("Profiling method: " + method.getName());

              return method.invoke(bean, args);
            }
          });
    }
    return bean;
  }
}
