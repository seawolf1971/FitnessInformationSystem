package fitness.fitness;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.TransactionScoped;



public interface UserRepository extends JpaRepository<User, Long> {

 User findByUsername(String username);

 User save(UserDto userDto);
 @Modifying
@Query("UPDATE User u SET u.payment_method = :paymentMethod WHERE u.username = :username")
void updatePaymentMethod(@Param("username") String username, @Param("paymentMethod") String paymentMethod);

@Modifying
@Query("UPDATE User u SET u.classes = :class WHERE u.username = :username")
void addClass(@Param("username") String username, @Param("class") String addClass);

@Modifying
@Query("UPDATE User u SET u.classes = NULL WHERE u.username = :username")
void delClasses(@Param("username") String username);

@Query("SELECT u FROM User u")
List<User> getAllUsers();

@Modifying
@Query("DELETE FROM User u WHERE u.username = :username")
void removeUser(@Param("username") String username);

@Modifying
@Query("UPDATE User u SET u.password = :newPassword WHERE u.username = :username")
void setNewPassword( @Param("username") String username,@Param("newPassword") String newPassword );

}