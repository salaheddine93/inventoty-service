package com.example.inventotyservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class InventotyServiceApplication implements CommandLineRunner{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(InventotyServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        restConfiguration.exposeIdsFor(Product.class);

        productRepository.save(new Product(null,"Laptop",17,2000));
        productRepository.save(new Product(null,"Printer",18,3000));
        productRepository.save(new Product(null,"Scanner",19,4000));

        productRepository.findAll().forEach(product -> {
            System.out.println(product.toString());
        });
    }
}

    @Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Product{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private double quantity;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long>{

}

