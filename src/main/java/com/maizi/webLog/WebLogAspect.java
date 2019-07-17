package com.maizi.webLog;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName WebLogAspect
 * @Description 切面类
 * 1、@Aspect:声明该类标识为一个切面供容器读取
 * 2、@Pointcut:Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是public及void型。
 * 可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为此表达式命名。
 * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码
 * 3、@Around:环绕增强，相当于MethodInterceptor
 * 4、@AfterReturning:后置增强，相当于AfterReturningAdvice,方法正常退出时执行
 * 5、@Before:标识一个前置增强方法，相当于BeforeAdvice的功能
 * 6、@AfterThrowing:异常抛出增强，相当于ThrowsAdvice
 * 7、@After：final增强，不管是抛出异常或者正常退出都会执行
 * @Author Liuys
 * @Date 2019/7/16 11:01
 * @Verson 1.0
 **/
@Aspect
@Component
public class WebLogAspect {
    private final static Logger LOG = LoggerFactory.getLogger(WebLogAspect.class);
    /*换行符*/
    private static final String LINE_SEPARATOR = System.lineSeparator();
    /**以自定义 @WebLog注解为切点*/
    @Pointcut("@annotation(com.maizi.webLog.WebLog)")
    public void webLog(){

    }
    /**
     * 环绕
     * @author      Liuys
     * @param proceedingJoinPoint
     * @return      java.lang.Object
     * @throws      Throwable
     * @date        2019/7/17 10:50
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        //执行切点
        Object result = proceedingJoinPoint.proceed();
        //打包出参
        LOG.info("Response Args : {}",new Gson().toJson(result));
        //LOG.info("Request Args  : {}", JSON.toJSONString(result));
        //执行耗时
        LOG.info("Time-Consuming: {}",System.currentTimeMillis() - startTime);
        return result;

    }
    /**
     * 在切点之前织入
     * @author      Liuys
     * @param joinPoint
     * @return      void
     * @throws
     * @date        2019/7/17 10:53
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取 @webLog 注解的描述信息
        String methodDescriptor = getAspectLogDescription(joinPoint);

        //打印请求相关参数
        LOG.info("================================start================================");
        //打印请求url
        LOG.info("URL         : {}",request.getRequestURL().toString());
        //打印描述信息
        LOG.info("Description : {}",methodDescriptor);
        //打印Http method
        LOG.info("HTTP Method : {}",request.getMethod());
        //打印调用  controller 的全路径以及执行方法
        LOG.info("Class Method: {}.{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        //打印请求的IP
        LOG.info("IP          : {}",request.getRemoteAddr());
        //打印请求入参
        //有些参数，转变成json有问题，而且参数会有多个
        //LOG.info("Request Args: {}",JSON.toJSONString(joinPoint.getArgs()));
        if(joinPoint.getArgs() != null && joinPoint.getArgs().length > 0){
            for(int i = 0; i < joinPoint.getArgs().length; i++){
                //LOG.info("Request Args: {}",new Gson().toJson(joinPoint.getArgs()[i]));
                LOG.info("Request Args: {}",joinPoint.getArgs()[i]);
            }
        }
    }
    /**
     * 在切点之后织入
     * @author      Liuys
     * @param
     * @return      void
     * @throws
     * @date        2019/7/17 10:56
     */
    @After("webLog()")
    public void doAfter() throws Throwable{
        //接口结束后执行，方便分割查看
        LOG.info("================================end================================"+ LINE_SEPARATOR);
    }

    /**
     * 获取切面注解的描述
     * @author      Liuys
     * @param joinPoint 切点
     * @return      java.lang.String
     * @throws      Exception
     * @date        2019/7/17 10:57
     */
    public String getAspectLogDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description  = new StringBuilder("");
        for(Method method:methods){
            if(method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if(clazzs.length == arguments.length){
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}
