package com.inventory.inventory_management;

import com.inventory.inventory_management.model.AppUser;
import com.inventory.inventory_management.repository.AppUserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InventoryManagementApplication {

	@Bean
	ApplicationRunner initializeAdminUser(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (!appUserRepository.existsByUsername("admin")) {
				appUserRepository.save(new AppUser("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN"));
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

}
