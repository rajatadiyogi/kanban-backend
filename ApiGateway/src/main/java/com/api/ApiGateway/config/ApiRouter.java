package com.api.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiRouter {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p->p //userAuthService
                        .path("/employee/**")
                        .uri("http://localhost:8081"))
                .route(p->p //userKanbanService
                        .path("/kanban/**")
                        .uri("http://localhost:8082"))


                .build();
    }
}
