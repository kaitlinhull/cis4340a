public class InterceptExit {
  public static void main(String[] args) {
    // ...
    System.exit(1);  // Abrupt exit
    System.out.println("This never executes");
  }
}
