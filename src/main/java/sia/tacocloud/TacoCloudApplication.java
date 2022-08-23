package sia.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TacoCloudApplication.class, args);
//        String[] str = ctx.getBeanDefinitionNames();
//        for(String st: str){
//            System.out.println(st);
//        }
    }

}
