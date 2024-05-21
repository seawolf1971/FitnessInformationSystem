package fitness.fitness;

public class UserDto {

    private String username;
    private String password;
    private String role;
    private String classes;
    private String payment_method;

    public UserDto(String username, String password) {
     super();
     this.username = username;
     this.password = password;
     this.role="USER";
    }
    public String getClasses(){
        return this.classes;
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
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role=role;
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
     return "UserDto [username=" + username + ", password=" + password + "]";
    }
   }
