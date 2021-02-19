package com.cry.token.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-29 8:23
 * @Modified By:
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {

    @Bean("threadPoolExecutor")
    public Executor threadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(10);
        threadPoolExecutor.setMaxPoolSize(50);
        threadPoolExecutor.setQueueCapacity(200);
        threadPoolExecutor.setKeepAliveSeconds(60);
        threadPoolExecutor.setThreadNamePrefix("taskExecutor--");
        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolExecutor.setAwaitTerminationSeconds(60);
        return threadPoolExecutor;
    }

}
