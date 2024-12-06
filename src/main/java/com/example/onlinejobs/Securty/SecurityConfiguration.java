package com.example.onlinejobs.Securty;

import com.example.onlinejobs.Entity.RoleTable.RoleName;
import com.example.onlinejobs.Service.UserService;
import com.example.onlinejobs.Token.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(@Lazy UserService userService, @Lazy JwtFilter jwtFilter) {
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/**")
                        .permitAll()
                        .requestMatchers("/home/api")
                        .permitAll()
                        .requestMatchers("/profile/**").hasAnyAuthority(
                                RoleName.Admin.getName(),
                                RoleName.Client.getName(),
                                RoleName.Employee.getName()
                        )
                        .requestMatchers("/admin/**").hasAuthority(
                                RoleName.Admin.getName()
                        )
                        .requestMatchers("/client/**").hasAnyAuthority(
                                RoleName.Admin.getName(),
                                RoleName.Client.getName()
                        )
                        .requestMatchers("/employee/**").hasAnyAuthority(
                                RoleName.Admin.getName(),
                                RoleName.Employee.getName()
                        )
                        .requestMatchers("/ws/**").hasAnyAuthority(
                                RoleName.Admin.getName(),
                                RoleName.Client.getName(),
                                RoleName.Employee.getName()
                        )
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Value("${spring.mail.host}")
    private String EmailHost;
    @Value("${spring.mail.port}")
    private Integer EmailPort;
    @Value("${spring.mail.password}")
    private String EmailPassword;
    @Value("${spring.mail.username}")
    private String EmailUsername;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean EmailAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls}")
    private boolean EmailStarttls;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(this.EmailHost);
        mailSenderImpl.setPort(this.EmailPort);

        mailSenderImpl.setUsername(this.EmailUsername);
        mailSenderImpl.setPassword(this.EmailPassword);

        Properties props = mailSenderImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", this.EmailAuth);
        props.put("mail.smtp.starttls.enable", this.EmailStarttls);
        props.put("mail.debug", "true");
        return mailSenderImpl;
    }

}
