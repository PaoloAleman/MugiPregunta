package com.project;

import com.project.config.DatabaseInitializationConfig;
import com.project.config.HibernateConfig;
import com.project.config.SpringWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // services and data sources
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // controller, view resolver, handler mapping
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class, HibernateConfig.class, DatabaseInitializationConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
