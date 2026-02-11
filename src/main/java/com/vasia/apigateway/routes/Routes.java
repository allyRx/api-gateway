package com.vasia.apigateway.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(){
        return route("product_service")
                .GET("/api/product/**", http()).before(uri("http://localhost:5050"))
                .POST("/api/product", http()).before(uri("http://localhost:5050"))
                .DELETE("/api/product/**", http()).before(uri("http://localhost:5050"))
                .PUT("/api/product/**", http()).before(uri("http://localhost:5050"))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> orderServiceRoutes(){
        return route("order_service")
                .GET("/api/order", http()).before(uri("http://localhost:8081"))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoutes(){
        return route("inventory_service")
                .GET("/api/inventory/available/**", http()).before(uri("http://localhost:8082"))
                .build();
    }

}
