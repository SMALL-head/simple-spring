package com.zyc.springframework.context.support;

/**
 * @author zyc
 * @version 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocations;

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
