import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// === 7. Главный класс ===
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// === 4. Сущность ===
@Entity
class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private LocalDateTime createdAt;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

// === 5. Репозиторий ===
interface UserRepository extends JpaRepository<User, Long> {
}

// === 6. Сервис ===
@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void demo() {

        // CREATE
        User user = new User("Иван", "ivan@mail.com");
        userRepository.save(user);

        // READ
        User found = userRepository.findById(1L).orElse(null);

        // UPDATE
        if (found != null) {
            found.setName("Иван Обновленный");
            userRepository.save(found);
        }

        // DELETE
        userRepository.deleteById(1L);
    }
}