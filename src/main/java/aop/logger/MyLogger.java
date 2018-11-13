package aop.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//Аспект для определения времени работы методов
@Component
@Aspect
public class MyLogger {

    //все методы из пакета objects
    @Pointcut("execution(public * * (..)) && within(aop.objects.*)")
    private void allMethods() {
    }

    //до и после выполения метода с аннтоацией ShowTime
    @Around("allMethods() && @annotation(aop.annotations.ShowTime)")
    public Object watchTime(ProceedingJoinPoint joinPoint) {

        System.out.println("Method start: " + joinPoint.getSignature().toShortString());
        long start = System.currentTimeMillis();
        Object output = null;

        try {
            output = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("Method ends:" + joinPoint.getSignature().toShortString() + ", time = " + time + " ms");

        return output;
    }

    //после возвращения значения по аннотации ShowResult
    @AfterReturning(pointcut = "allMethods() && @annotation(aop.annotations.ShowResult)", returning = "obj")
    public void print(Object obj) {
        if (obj instanceof Set) {
            Set set = (Set) obj;
            for (Object object : set) {
                System.out.println(object);
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;

            final long[] i = {0};
            map.forEach((k, v) -> System.out.println(k + " - " + v));

            /*for (Iterator iterator = (Iterator) map.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterator.next();
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }*/

            /*for (Object object : map.keySet()) {
                System.out.println(object + " - " + map.get(object));
            }*/

        }
    }
}
