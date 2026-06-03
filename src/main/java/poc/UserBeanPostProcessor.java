package poc;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
// @Scope("prototype") // Uncomment this for the second run.
public class UserBeanPostProcessor implements BeanPostProcessor {

    private static final AtomicInteger IDS = new AtomicInteger();

    private final int id = IDS.incrementAndGet();

    public UserBeanPostProcessor() {
        System.out.printf("CONSTRUCT UserBeanPostProcessor id=%d%n", id);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        log("before", bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        log("after", bean, beanName);
        return bean;
    }

    private void log(String phase, Object bean, String beanName) {
        if (bean instanceof UserBeanPostProcessor || bean instanceof UserService) {
            Integer beanId = bean instanceof UserBeanPostProcessor bpp ? bpp.id : null;

            System.out.printf(
                    "%s: processorId=%d, beanName=%s, beanClass=%s, beanId=%s, sameInstance=%s%n",
                    phase,
                    this.id,
                    beanName,
                    bean.getClass().getSimpleName(),
                    beanId,
                    bean == this
            );
        }
    }
}