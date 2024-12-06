package com.example.onlinejobs.Service;

import com.example.onlinejobs.Dtos.Response.ApiResponse;
import com.example.onlinejobs.Dtos.Authentication.LoginDto;
import com.example.onlinejobs.Dtos.Authentication.UserDto;
import com.example.onlinejobs.Dtos.Authentication.VerifyDto;
import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.RoleTable.Role;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Repository.CategoryRepository;
import com.example.onlinejobs.Repository.RoleRepository;
import com.example.onlinejobs.Repository.UserRepository;
import com.example.onlinejobs.Service.OutLine.UserServiceInterface;
import com.example.onlinejobs.Token.JwtProvider;
import com.example.onlinejobs.Utils.GenerateCode;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService, UserServiceInterface {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UserService(UserRepository userRepository, JavaMailSender mailSender, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager, JwtProvider jwtProvider, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public ApiResponse register(UserDto userDto) throws ClassNotFoundException {
        if (userRepository.existsByUsername(userDto.username())) {
            return new ApiResponse("username already exists", HttpStatus.BAD_REQUEST.value(), false, null);
        } else if (userRepository.existsByEmail(userDto.email())) {
            return new ApiResponse("email already exists", HttpStatus.BAD_REQUEST.value(), false, null);
        }
        String emailCode = GenerateCode.Code(6);
        Role byRoleName = roleRepository.findByRoleName(userDto.role()).orElseThrow(() -> new ClassNotFoundException("Role not found"));
        if (SendEmail(userDto.email(), emailCode)) {
            UserJobs user = UserJobs.builder()
                    .email(userDto.email())
                    .username(userDto.username())
                    .password(passwordEncoder.encode(userDto.password()))
                    .role(Set.of(byRoleName))
                    .firstname(userDto.firstName())
                    .lastname(userDto.lastName())
                    .verificationCode(emailCode)
                    .phone(userDto.phone())
                    .isAccountNonLocked(true)
                    .isAccountNonExpired(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(false)
                    .build();
            Categories categories = categoryRepository.findByName(userDto.categoryType()).orElseThrow(() -> new ClassNotFoundException("Category not found"));
            user.setCategories(categories);
            userRepository.save(user);
            return new ApiResponse("success", HttpStatus.OK.value(), true, null);
        }
        throw new com.example.onlinejobs.Exceptions.ServerErrorException("server error");
    }

    @Override
    public ApiResponse verify(VerifyDto verifyDto) {
        Optional<UserJobs> byEmail = userRepository.findByEmail(verifyDto.getEmail());
        if (byEmail.isPresent()) {
            UserJobs userJobs = byEmail.get();
            if (userJobs.getVerificationCode().equals(verifyDto.getVerificationCode())) {
                userJobs.setVerificationCode(null);
                userJobs.setEnabled(true);
                UserJobs save = userRepository.save(userJobs);
                return new ApiResponse("success", HttpStatus.OK.value(), true, save);
            }
            return new ApiResponse("verification failed", HttpStatus.BAD_REQUEST.value(), false, null);
        }
        return new ApiResponse("user not found", HttpStatus.BAD_REQUEST.value(), false, null);
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {
        try {
            UserDetails userDetails = loadUserByUsername(loginDto.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails, loginDto.getPassword(), userDetails.getAuthorities()));
            if (authentication != null && authentication.isAuthenticated()) {
                UserJobs user = (UserJobs) authentication.getPrincipal();
                String token = jwtProvider.generateToken(user.getUsername(), user.getRole(), user.getEmail());
                return new ApiResponse("success", HttpStatus.OK.value(), true, token);
            }
            return new ApiResponse("login failed", HttpStatus.BAD_REQUEST.value(), false, null);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user not found");
        }
    }

    @Override
    public ApiResponse logout() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                SecurityContextHolder.clearContext();
            }
            return new ApiResponse("Logout successful", HttpStatus.OK.value(), true, null);
        } catch (Exception e) {
            return new ApiResponse("An error occurred during logout: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, null);
        }
    }

    @Value("${spring.mail.username}")
    private String EmailUsername;


    private boolean SendEmail(String to, String message) {
        try {
            String htmlMessage = """
                        <div style="font-family: Arial, sans-serif; color: #333;">
                            <h2 style="color: #2e86de;">Xush kelibsiz!</h2>
                            <p>Hurmatli foydalanuvchi, quyidagi ma'lumotlarni tasdiqlang:</p>
                            <p style="font-size: 14px; padding: 10px 20px; display: inline-block; background-color: blue; color: white;">{message}</p>
                            <p>Hurmat bilan, <br><strong>Shukurjon tomonidan tasdiqlandi</strong></p>
                            <footer style="font-size: 12px; color: #777;">
                                Agar bu xabarni sizga noqulaylik tug'dirayotgan bo'lsa, iltimos biz bilan bog'laning.
                            </footer>
                        </div>
                    """.replace("{message}", message);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(EmailUsername);
            helper.setText(htmlMessage, true);
            helper.setSubject("verified by Shukurjon");
            helper.setTo(to);
            mailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            throw new ServerErrorException("don't send message server error", e.getCause());
        }
    }
}
