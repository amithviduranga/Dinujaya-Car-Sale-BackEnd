package com.pokemonreview.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ShedulerConfig {
    @Bean
        public TaskScheduler taskScheduler() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(10);  // Set the thread pool size
            scheduler.setThreadNamePrefix("TaskScheduler-");
            scheduler.initialize();
            return scheduler;
        }// Simple implementation, you can customize as needed
    }

