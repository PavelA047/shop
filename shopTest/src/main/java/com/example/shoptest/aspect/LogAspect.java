package com.example.shoptest.aspect;

import com.example.shoptest.dto.BucketDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    //логгирование методов работы с продуктом с записью в файл
    //закрыл из-за тестирования
//    @After("execution(* com.example.shoptest.service.ProductService.*(..))")
//    public void logProductMethod(JoinPoint joinPoint) {
//
//        try {
//            Files.writeString(Paths.get("shopTest/log/log_file.txt"),
//                    joinPoint.toString() + " " + LocalDateTime.now() + "\r\n",
//                    StandardOpenOption.CREATE,
//                    StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //аггрегация суммы и количества товара
    @AfterReturning(value = "execution(* com.example.shoptest.service.BucketService.getBucketByUser(..))", returning = "res")
    public void aggregateMethod(BucketDto res) {
        res.aggregate();
    }

    //время UserService
    @Around("execution(* com.example.shoptest.service.UserService.*(..))")
    public Object timeTestUserServiceMethod(ProceedingJoinPoint pjp) {
        Object value;
        long begin = System.currentTimeMillis();
        try {
            value = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        long end = System.currentTimeMillis();
        System.out.println("Time of UserService: " + (end - begin));
        return value;
    }

    //время ProductService
    @Around("execution(* com.example.shoptest.service.ProductService.*(..))")
    public Object timeTestProductServiceMethod(ProceedingJoinPoint pjp) {
        Object value;
        long begin = System.currentTimeMillis();
        try {
            value = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        long end = System.currentTimeMillis();
        System.out.println("Time of ProductService: " + (end - begin));
        return value;
    }

    //время BucketService
    @Around("execution(* com.example.shoptest.service.BucketService.*(..))")
    public Object timeTestBucketServiceMethod(ProceedingJoinPoint pjp) {
        Object value;
        long begin = System.currentTimeMillis();
        try {
            value = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        long end = System.currentTimeMillis();
        System.out.println("Time of BucketService: " + (end - begin));
        return value;
    }

    //время CategoryService
    @Around("execution(* com.example.shoptest.service.CategoryService.*(..))")
    public Object timeTestCategoryServiceMethod(ProceedingJoinPoint pjp) {
        Object value;
        long begin = System.currentTimeMillis();
        try {
            value = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        long end = System.currentTimeMillis();
        System.out.println("Time of CategoryService: " + (end - begin));
        return value;
    }
}
