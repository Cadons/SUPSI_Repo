package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UsageTracerInterceptor implements MethodInterceptor, UsageTracer {



    public Object invoke(MethodInvocation invocation) throws Throwable {
        //track method time execution
        long start = System.nanoTime();
        Object result = invocation.proceed();
        long end = System.nanoTime();
        System.err.println(invocation.getMethod().getName()+" time of call:"+(end - start));
        return result;
    }

}
