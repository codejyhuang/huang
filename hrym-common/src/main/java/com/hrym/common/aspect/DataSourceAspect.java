package com.hrym.common.aspect;

import com.hrym.common.annotation.DataSource;
import com.hrym.common.db.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;

/**
 * 设置注解拦截切面，调用时会切换到指定数据源
 *
 * Created by mj on 2017/7/2.
 */
@Aspect
@Component
public class DataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    public DataSourceAspect(){
        System.out.println();
    }
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        if (TransactionSynchronizationManager.isActualTransactionActive()
                && DynamicDataSource.getDataSource() != null)
            return;
        // 获取方法签名
        Method declareMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Method instanceMethod = joinPoint.getTarget().getClass().getMethod(declareMethod.getName(),
                declareMethod.getParameterTypes());
        DataSource methodAnnotation = AnnotationUtils.findAnnotation(instanceMethod, DataSource.class);
        if (methodAnnotation == null)
            return;
        if (methodAnnotation != null) {
            DynamicDataSource.setDataSource(methodAnnotation.value());
        }
    }

    /**
     * 方法执行完后置空
     */
    public void after(JoinPoint joinPoint) {
        if (TransactionSynchronizationManager.isActualTransactionActive())
            return;
        if (TransactionSynchronizationManager.isSynchronizationActive())
            TransactionSynchronizationManager.clearSynchronization();
        DynamicDataSource.clearDataSource();
    }


/*
    @Around("@annotation(dataSourceChange)")
    public Object doAround(ProceedingJoinPoint pjp, DataSource dataSourceChange) {
        Object retVal = null;
        boolean selectedDataSource = false;
        try {
            if (null != dataSourceChange) {
                selectedDataSource = true;
                if (dataSourceChange.slave()) {
                    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
                } else {
                    DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
                }
            }
            String dataSource = DynamicDataSource.getDataSource();

            retVal = pjp.proceed();
        } catch (Throwable e) {
            LOGGER.warn("数据源切换错误", e);
            e.printStackTrace();
        } finally {
            if (selectedDataSource) {
                DynamicDataSource.clearDataSource();
            }
        }
        return retVal;
    }*/



}
