package fitness.fitness;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;


@Controller
public class UserController {

 @Autowired
 private UserDetailsService userDetailsService;

 private UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }
 @Autowired
  PasswordEncoder passwordEncoder;

 @GetMapping("/home")
 public String home(Model model, Principal principal) {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     boolean isAuthenticated = authentication.isAuthenticated();
     model.addAttribute("isAuthenticated", isAuthenticated);
  UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
  model.addAttribute("userdetail", userDetails);
  boolean isAdmin = authentication.getAuthorities().stream()
  .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
  model.addAttribute("isAdmin", isAdmin);
  return "home";
 }

 @GetMapping("/admin")
 public String admin(Model model, UserDto userDto){
  List<User> users = userService.getAllUsers(); 
  model.addAttribute("users", users);
    return "admin";
    
 }
 

 @GetMapping("/login")
 public String login(Model model, UserDto userDto) {
 
  model.addAttribute("user", userDto);
  return "login";
 }
 

 @GetMapping("/register")
 public String register(Model model, UserDto userDto) {
  model.addAttribute("user", userDto);
  return "register";
 }

 @PostMapping("/register")
 public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
  User user = userService.findByUsername(userDto.getUsername());
  if (user != null) {
   model.addAttribute("error", "This username is already taken !");
   return "register";
  }
  
  userService.save(userDto);
  model.addAttribute("success", "Successfully registered !");
  return "register";
 }
 @GetMapping("/change-payment")
 public String change_payment(Model model, String pay){
   model.addAttribute("paymentMethod", pay);
   return "/change-payment";
 }
 @PostMapping("/change-payment")
 @Transactional
  public String changePayment( @ModelAttribute("paymentMethod") String pay, Model model)
  {
    
    System.out.println("payment method =============================================== " + pay);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    userService.updatePaymentMethod(username, pay);
   
   return "redirect:/home";
  }


  @GetMapping("/sign-up")
  public String addClass(Model model, String setClass){
    model.addAttribute("class", setClass);
    return "/sign-up";
  }
  @PostMapping("/sign-up")
  @Transactional
  public String addClassToUser(@ModelAttribute("class") String setClass, Model model){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    userService.addClass(username, setClass);

    return "redirect:/home";
  }
  @GetMapping("/sign-out")
  public String delClass(Model model, String setClass){
    model.addAttribute("class", setClass);
    return "/sign-out";
  }
  @PostMapping("/sign-out")
  @Transactional
  public String deleteClassToUser(@ModelAttribute("class") String setClass, Model model){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    userService.delClass(username, setClass);

    return "redirect:/home";
  }
  @GetMapping("/remove-user")
  public String removeUser(Model model,String username){
    model.addAttribute("username", username);
    return "/remove-user";
  }
  @PostMapping("/remove-user")
  public String removeUserr(@ModelAttribute("username") String username, Model model){
    User user = userService.findByUsername(username);
    if (user != null){
      userService.removeUser(username);
    }
    
    return "redirect:/admin";
  }
  @GetMapping("/add-user")
  public String addUser(Model model, UserDto userDto) {
    model.addAttribute("user", userDto);
    return "/add-user";
   }
  @PostMapping("/add-user")
  public String addUser(@ModelAttribute("user") UserDto userDto, Model model){
    User user = userService.findByUsername(userDto.getUsername());
    if (user != null){
      
    }else{
      userService.save(userDto);
    }
    
    return "redirect:/admin";
  }
  @GetMapping("/change-password")
  public String changePassword(Model model,String oldPassword,String newPassword){
   model.addAttribute("oldPassword", oldPassword);
   model.addAttribute("newPassword", newPassword);
  

    return "/change-password";
  }
  
  @Transactional
  @PostMapping("/change-password")
  public String changePasswordd(@ModelAttribute ("oldPassword") String oldPassword,
  @ModelAttribute ("newPassword") String newPassword, Model model)
  {
   
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User user = userService.findByUsername(username);
  
    
    if (passwordEncoder.matches(oldPassword, user.getPassword())){
     
      userService.setNewPassword(username,newPassword);
    }
    else{
     
      model.addAttribute("error", 
      "Old password is not correct !");
    }
    
    return "redirect:/logout";
  }

 
}