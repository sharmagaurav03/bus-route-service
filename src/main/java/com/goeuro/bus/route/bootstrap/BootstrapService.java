package com.goeuro.bus.route.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.goeuro.bus.route"})
public class BootstrapService 
{
	
    public static void main( String[] args )
    {
    	SpringApplication.run(BootstrapService.class);
    }
    
}
