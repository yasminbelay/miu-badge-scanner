package BadgeAndMembership.advice;

import contracts.dto.CustomErrorMessage;
import edu.miu.common.exception.ResourceNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Aspect
@Configuration
public class ControllerAdvice {
    @Autowired
    private Logger logger;

    @Pointcut("execution(* *..*Controller.*(..))")
    public void myPointcut() {
    }

    @Around("execution(* *..*Controller.*(..)) ")
    public Object executeControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ResourceNotFoundException ex) {
            String errorMessage = "Resource not found";
            logger.error(joinPoint.getSignature().getName() + ": " + errorMessage + " - " + ex.getMessage());
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            if (methodSignature.getReturnType() != ResponseEntity.class)
                return null;
            return new ResponseEntity<>(new CustomErrorMessage(errorMessage), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            String errorMessage = "Something went wrong. Please contact System Admin";
            logger.error(joinPoint.getSignature().getName() + ": " + errorMessage + " - " + ex.getMessage());
            return new ResponseEntity<>(new CustomErrorMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
