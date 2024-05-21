package fitness.fitness;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 // @Column(unique = true)
 private String username;
 private String password;
 private String role;
 private String classes;
 private String payment_method;

 public User() {

 }

 public User(String username, String password) {
  super();
  this.username = username;
  this.password = password;
  this.role = "USER";
  
 }
 public String getClasses(){
    return this.classes != null ? this.classes : "No active classes";
 }
 public void setClasses(String classes){
    this.classes= classes;
 }
 public void addClasses(String classes){
    this.classes=this.classes+classes;
 }
 public String getPayment_Method(){
    return this.payment_method;
 }
 public void setPayment_Method(String payment_method){
    this.payment_method=payment_method;
 }
public String getRole()
{
    return this.role;
}
public void setRole(String role)
{
    this.role=role;
}
 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }
 

 


 @Override
 public String toString() {
  return "User [id=" + id + ", username=" + username + ", password=" + password +"]";
 }

}

