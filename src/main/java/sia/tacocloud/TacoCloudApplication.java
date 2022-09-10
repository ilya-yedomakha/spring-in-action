package sia.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.User;
import sia.tacocloud.repositories.IngredientRepository;
import sia.tacocloud.repositories.UserRepository;

import java.util.Arrays;

@SpringBootApplication
//@ComponentScan(basePackages = {"sia.tacocloud.*"})
public class TacoCloudApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TacoCloudApplication.class, args);
        System.out.println("test_out");
//        String[] str = ctx.getBeanDefinitionNames();
//        for(String st: str){
//            System.out.println(st);
//        }
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

            userRepository.save(new User("username",passwordEncoder.encode("123321"),"Ilya Yedomakha", "Street str", "Chernihiv",
                    "USA","123","+380238690"));

            userRepository.save(new User("buzz",passwordEncoder.encode("password"),"Buzz Lightyear", "Galaxy str", "Mars",
                    "ZRU","123","+093894082"));

            userRepository.save(new User("heisenberg",passwordEncoder.encode("bluemeth"),"Walter White", "Baker str", "Albuquerque",
                    "New Mexico","1234","+678238690"));
        };
    }

}
