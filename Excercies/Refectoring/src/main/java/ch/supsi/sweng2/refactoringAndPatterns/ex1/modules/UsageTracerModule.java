package ch.supsi.sweng2.refactoringAndPatterns.ex1.modules;

import ch.supsi.sweng2.refactoringAndPatterns.ex1.model.UsageTracer;
import ch.supsi.sweng2.refactoringAndPatterns.ex1.model.UsageTracerInterceptor;
import ch.supsi.sweng2.refactoringAndPatterns.ex1.model.Visitor;
import ch.supsi.sweng2.refactoringAndPatterns.ex1.model.VisitorConcreate;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;

import java.util.regex.Matcher;

public class UsageTracerModule extends AbstractModule {

    @Override
    protected void configure() {
        var interceptor = new UsageTracerInterceptor();

        bind(Visitor.class).to(VisitorConcreate.class).in(Scopes.SINGLETON);
        bindInterceptor(Matchers.subclassesOf(Visitor.class), Matchers.any(), interceptor);


    }

}
