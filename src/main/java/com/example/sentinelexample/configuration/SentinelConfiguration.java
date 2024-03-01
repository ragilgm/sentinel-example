package com.example.sentinelexample.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.config.WebServletConfig;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import org.springframework.cloud.alibaba.sentinel.datasource.annotation.SentinelDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SentinelConfiguration {

    @Bean
    public boolean systemRuleConfig(){
        List<SystemRule> rules = new ArrayList<>();
        SystemRule rule = new SystemRule();
        rule.setHighestSystemLoad(0.1);
        rule.setMaxThread(100); //
        rule.setAvgRt(10000); // Batas waktu respons rata-rata 10 second
        rule.setQps(1); // Maximum query per secon 1000
        rules.add(rule);
        SystemRuleManager.loadRules(rules);

        return true;
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}