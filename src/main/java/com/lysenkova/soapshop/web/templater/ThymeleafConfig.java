package com.lysenkova.soapshop.web.templater;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThymeleafConfig {
    private static final TemplateEngine templateEngine = ThymeleafConfig.templateEngine();

    private static TemplateEngine templateEngine() {
        final TemplateEngine engine = new TemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    public static void process(String page, WebContext context, HttpServletResponse response) throws IOException {
        templateEngine.process(page, context, response.getWriter());
    }

    private static ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/assets/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        return resolver;
    }

}