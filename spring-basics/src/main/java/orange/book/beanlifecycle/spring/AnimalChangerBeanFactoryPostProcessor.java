package orange.book.beanlifecycle.spring;

import orange.book.beanlifecycle.model.Frog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class AnimalChangerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory( ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    BeanDefinition owlBeanDefinition = configurableListableBeanFactory.getBeanDefinition("owl");
    owlBeanDefinition.setBeanClassName(Frog.class.getName());
    System.out.println("I am bean factory and I am changing bean definition for an Owl to an Frog =)");
  }
}
