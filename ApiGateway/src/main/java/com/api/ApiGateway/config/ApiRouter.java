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
                .route(p->p //kanbanProjectService
                        .path("/kan/**")
                        .uri("http://localhost:8082"))
                .route(p->p //KanbanUserService
                        .path("/kanban/**")
                        .uri("http://localhost:8083"))
                .route(p->p //EmailNotification
                        .path("/emailProcess/**")
                        .uri("http://localhost:8084"))
                .build();

//            return routeLocatorBuilder.routes()
//                    .route(p->p //userAuthService
//                            .path("/employee/**")
//                            .uri("http://user-auth:8081"))
//                    .route(p->p //kanbanProjectService
//                            .path("/kan/**")
//                            .uri("http://kanban-project:8082"))
//                    .route(p->p //KanbanUserService
//                            .path("/kanban/**")
//                            .uri("http://kanban-user:8083"))
//                    .route(p->p //EmailNotification
//                            .path("/emailProcess/**")
//                            .uri("http://email-sender:8084"))
//                    .build();

    }
}