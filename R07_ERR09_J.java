class PasswordSecurityManager extends SecurityManager {
  private boolean isExitAllowedFlag;
   
  public PasswordSecurityManager(){
    super();
    isExitAllowedFlag = false; 
  }
  
  public boolean isExitAllowed(){
    return isExitAllowedFlag;   
  }
  
  @Override
  public void checkExit(int status) {
    if (!isExitAllowed()) {
      throw new SecurityException();
    }
    super.checkExit(status);
  }
  
  public void setExitAllowed(boolean f) {
    isExitAllowedFlag = f;  
  }
}
 
public class InterceptExit {
  public static void main(String[] args) {
    PasswordSecurityManager secManager =
        new PasswordSecurityManager();
    System.setSecurityManager(secManager);
    try {
      // ...
      System.exit(1);  // Abrupt exit call
    } catch (Throwable x) {
      if (x instanceof SecurityException) {
        System.out.println("Intercepted System.exit()");
        // Log exception
      } else {
        // Forward to exception handler
      }
    }
 
    // ...
    secManager.setExitAllowed(true);  // Permit exit
    // System.exit() will work subsequently
    // ...
  }
}
