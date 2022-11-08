package orange.book.beanlifecycle.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class FrogConfigurrerBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("I am beanPostProcessor and I am doing something with bean before PostConstruct");
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("I am beanPostProcessor and I am doing something with bean after PostConstruct"
        + "for instance I can create proxy");
    return bean;
  }

}
