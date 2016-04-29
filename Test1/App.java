public class App { 

   public static void main(String[] args) { 
      Test a = new Test();
      a.print();
      Test b = new Test("SECRET");
      b.print();
      b.setSecret("SECRET 2");
      b.print();

      ClassInterface c = new Test();
   }

}