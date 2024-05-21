package fitness.fitness;

import java.util.List;

public interface UserService {
 User findByUsername(String username);

 User save(UserDto userDto);
 void updatePaymentMethod(String username, String paymentMethod);
 void addClass(String username, String addClass);
 void delClass(String username, String del);
 List <User> getAllUsers();
 void removeUser(String username);
 void setNewPassword(String username, String newPassword);
}
