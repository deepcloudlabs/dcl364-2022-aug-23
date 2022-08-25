package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({
	"com.example.service", "com.example.aop"
})	
@EnableAspectJAutoProxy
public class AppConfig {

}
