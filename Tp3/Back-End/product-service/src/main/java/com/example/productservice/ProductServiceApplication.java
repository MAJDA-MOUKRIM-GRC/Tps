package com.example.productservice;

import com.example.productservice.entites.Product;
import com.example.productservice.repositories.ProductRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Product Service")
                        .description("Test Description")
                        .version("1.0.0"));
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null, "lapTop", 152, 1000));
            productRepository.save(new Product(null, "smartPhone", 1582, 1000));
            productRepository.save(new Product(null, "Speaker", 15, 1000));

            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }

}
