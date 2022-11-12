package orange.book.lifecycle.practice.infrastructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import orange.book.lifecycle.practice.annotation.InjectFromProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class RandomIntBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  public Map<Class<?>, String> classWithAnnotationsWhereNeedToInjectSomeFields = new HashMap<>();
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    String[] beanDefinitionsNames = configurableListableBeanFactory.getBeanDefinitionNames();
    for(String name: beanDefinitionsNames) {
      BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
      String beanClassName = beanDefinition.getBeanClassName();
      try {
        Class<?> clazz = Class.forName(beanClassName);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
          InjectFromProperties injectFromProperties = field.getAnnotation(InjectFromProperties.class);
          if (injectFromProperties != null) {
            String propertyName = injectFromProperties.value();
            String value = getValueFromProperties(propertyName);
            classWithAnnotationsWhereNeedToInjectSomeFields.put(clazz, value);
          }
        }

      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  private String getValueFromProperties(String value) {
    Pattern pattern = Pattern.compile("\\&(.+)\\&");
    Matcher matcher = pattern.matcher(value);
    String propertyValue = null;
    if(matcher.find()) {
      String propertyName = matcher.group(1);
      Properties properties = new Properties();
      try {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
        properties.load(new FileInputStream(rootPath + "whealsnumbers.properties"));
        propertyValue = properties.getProperty(propertyName);
      } catch (IOException e) {
        System.out.println("Property file not found");
      }
    }
    return propertyValue;
  }
}
