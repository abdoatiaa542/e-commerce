package com.example.e_commerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrfConfigurer) -> csrfConfigurer.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/banners/**").permitAll()
                        .requestMatchers("/api/auth/refresh-token").permitAll()
                        .requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated()


                ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /*
    *
    * في مشروع يعتمد على JWT (JSON Web Token)، قد تحتاج إلى تعريف AuthenticationManager بشكل صريح في الكلاس بتاع SecurityConfig إذا كنت تقوم بإنشاء عملية مصادقة مخصصة (مثل تسجيل الدخول) باستخدام UsernamePasswordAuthenticationToken، حيث ستحتاج إلى استخدام AuthenticationManager للتحقق من بيانات المستخدم وكلمة المرور.

    ما هي فائدة AuthenticationManager؟
    AuthenticationManager هو الجزء المسؤول عن إدارة عمليات المصادقة في Spring Security. عندما تقوم بتسجيل الدخول عبر POST طلب يحتوي على بيانات المصادقة (مثل اسم المستخدم وكلمة المرور)، يتم استخدام AuthenticationManager للتأكد من أن البيانات المقدمة تتطابق مع بيانات المستخدم الموجودة في النظام.
    لماذا لم تكن تحتاجها في Basic Authentication؟
    في Basic Authentication، Spring Security يقوم بكل شيء تلقائيًا تقريبًا. عند تلقي طلب يحتوي على اسم المستخدم وكلمة المرور، يتم معالجة هذه البيانات داخليًا باستخدام الفلتر الخاص بـ Basic Authentication، لذا لا تحتاج لتعريف AuthenticationManager بشكل صريح.*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
