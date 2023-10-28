package com.smartedhub_server.config.security;

import com.smartedhub_server.config.security.filter.JwtAuthenticationTokenFilter;
import com.smartedhub_server.config.security.filter.RestAuthorizationEntryPoint;
import com.smartedhub_server.config.security.filter.RestfulAccessDeniedHandler;
import com.smartedhub_server.pojo.Admin;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.service.IAdminService;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: SMARTEDHUB_Server
 * @description: For the configuration of Spring Security
 * @author: Junxian Cai
 **/

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    //执行自己重写的UserDetailsService，通过getUserByUserName获取用户名
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //Override UserDetailsService method
        return userName -> {
            /**
             *@Override
             * @Bean
             * public UserDetailsService userDetailsService() {
             *     return username -> {
             *         // 尝试从普通用户表中获取用户
             *         User normalUser = userService.getNormalUserByUserName(username);
             *         if (normalUser != null) {
             *             // 可以为普通用户设置权限，例如ROLE_USER
             *             return normalUser;
             *         }
             *
             *         // 尝试从管理员用户表中获取用户
             *         User adminUser = userService.getAdminUserByUserName(username);
             *         if (adminUser != null) {
             *             // 可以为管理员用户设置权限，例如ROLE_ADMIN
             *             return adminUser;
             *         }
             *
             *         // 如果两个表中都没有找到用户，抛出异常
             *         throw new UsernameNotFoundException("User not found with username: " + username);
             *     };
             * }
             * 注意：在上述示例中，我使用了User作为示例，但实际上，由于您有两种类型的用户，您可能有两个不同的实体类，比如NormalUser和AdminUser。
             * 另外，请确保这两个实体类都实现了UserDetails接口，或者您需要将它们转换为实现了UserDetails接口的类，以便Spring Security能够正确处理它们。
             *
             * 最后，请确保您在配置Spring Security时正确处理了这两种用户的权限和角色。
             *
             */
            Teacher teacher = teacherService.getTeacherByUserName(userName);
            Student student = studentService.getStudentByUserName(userName);
            Admin admin = adminService.getAdminByUserName(userName);

            if (null != student) {
                return student;
            } else if (null != teacher) {
                return teacher;
            } else if (null != admin) {
                return admin;
            }
            return null;
        };
    }

    /**
     * Spring Security configuration
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Using JWT, no need for csrf.
        http.cors().and()
                .csrf()
                .disable()
                //It's based on token, no need for the session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();

        // Add jwt. login authorisation filter(JWT登陆授权器)
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // Add custom unauthorised and return for login results
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }



    //放行路径
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/register",
                "/admin/adminLogin",
                "/studentLogin",
                "/studentRegister",
                "/teacherLogin",
                "/teacherRegister",
                "/css/**",
                "/js/**",
                "/index.html",
                "/doc.html",
                "/webjars/**",
                "/doc.html",
                "/webjars/**",
                "/localhost:8085/doc.html",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs/**"
        );
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
         return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
