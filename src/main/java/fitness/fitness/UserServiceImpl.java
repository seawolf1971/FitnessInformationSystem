package fitness.fitness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;



@Service
public class UserServiceImpl implements UserService {

 @Autowired
 PasswordEncoder passwordEncoder;

 private UserRepository userRepository;

 public UserServiceImpl(UserRepository userRepository) {
  super();
  this.userRepository = userRepository;
 }

 @Override
 public User findByUsername(String username) {
  return userRepository.findByUsername(username);
 }

 @Override
 public User save(UserDto userDto) {
  User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
  return userRepository.save(user);
 }
 @Override
 public void updatePaymentMethod(String username,String paymentMethod){
    
   userRepository.updatePaymentMethod(username,paymentMethod);
 }
@Override
public void addClass(String username, String addClass){
  userRepository.addClass(username,addClass);
}
@Override
public void delClass(String username, String del){
  User user = findByUsername(username);
  if (user!=null && user.getClasses().equals(del)){
      userRepository.delClasses(username);
  }
}

@Override
public List <User> getAllUsers(){
  return userRepository.getAllUsers();
}
@Override
@Transactional
public void removeUser(String username){
  userRepository.removeUser(username);
}

@Override
@Transactional
public void setNewPassword(String username, String newPassword){
  userRepository.setNewPassword(username,passwordEncoder.encode(newPassword));
}

}
