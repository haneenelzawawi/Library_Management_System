package org.library_management;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("{} executed in {} ms", joinPoint.getSignature(), endTime - startTime);
        return proceed;
    }

    @AfterThrowing(pointcut = "execution(* com.example.library..*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in {} with cause = {}", joinPoint.getSignature(), ex.getCause() != null ? ex.getCause() : "NULL");
    }
}

