import java.util.List;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.Iterator;
 
 public class HowdyWorld {
     
     String r = Math.random() + "";
     Integer counter=0;
  
     public void sayHello() {
             System.out.println(counter++ +" @ "+new Date());
     }
 
     public static void main(String[] args) {
         HowdyWorld hw = new HowdyWorld();
         for (int i = 1; i < 100; i++) {
             hw.sayHello();
         }
     }
  }
