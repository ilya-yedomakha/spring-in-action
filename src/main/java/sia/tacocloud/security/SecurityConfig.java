package sia.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
        .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Autowired
//    DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("buzz")
//                .password("{noop}infinity")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("{noop}bullseye")
//                .authorities("ROLE_USER");
//    }

//
//    public static final String DEF_USERS_BY_USERNAME_QUERY =
//            "select username,password,enabled " +
//                    "from users " +
//                    "where username = ?";
//    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
//            "select username,authority " +
//                    "from authorities " +
//                    "where username = ?";
//    public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
//            "select g.id, g.group_name, ga.authority " +
//                    "from groups g, group_members gm, group_authorities ga " +
//                    "where gm.username = ? " +
//                    "and g.id = ga.group_id " +
//                    "and g.id = gm.group_id";


//    @Bean
//    public PasswordEncoder encoder() {
//        return new StandardPasswordEncoder("53cr3t");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, 'true' as enabled from Users " +
//                                "where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities " +
//                                "where username=?")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
//    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .ldapAuthentication()
                .userSearchFilter("(uid={0})")
                .groupSearchFilter("member={0}");
    }
*/
//@Bean
//public PasswordEncoder encoder() {
//    return new StandardPasswordEncoder("53cr3t");
//}
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//
//    }

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**","/orders/current","/design", "/orders")
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
        ;
    }


}
