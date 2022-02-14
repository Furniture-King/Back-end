package com.FurnitureKing.Project;

import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	@Autowired
	public ProjectApplication(ProductRepository productRepository){
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {SpringApplication.run(ProjectApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {

		if (productRepository.findAll().isEmpty()) {
			productRepository.save(new Product(
					"Rohat manual",
					"Bleu",
					1,
					5,
					60,
					60));
		}

		for(Product product : productRepository.findAll()){
			System.out.println(product);
		}
	}
}