package com.example.lookup;



import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.TransactionController;
import net.sf.ehcache.distribution.RMICacheManagerPeerListener;
import net.sf.ehcache.distribution.RMICacheManagerPeerProvider;

import com.example.object.foo;





public class fooreplicator {
	
	private static TransactionController transactionManager;
	
	
	
	public static String generateString(Random rng, String characters, int length) 
	{ 
	    char[] text = new char[length]; 
	    for (int i = 0; i < length; i++) 
	    { 
	        text[i] = characters.charAt(rng.nextInt(characters.length())); 
	    } 
	    return new String(text); 
	} 
	
	public static int genint(int k) {
		int j = (int) (Math.random()*k);
	    return j;
	}
	
	
	 public static void main(String[] args) {

		   int stm = 2000;
		   int port = 5595;
		    foo rl1 = new foo();
		    CacheManager manager = new CacheManager("ehcache_test.xml");
		    Cache cache = manager.getCache("fooCache");	

		    transactionManager = manager.getTransactionController();
	      
		    
		    for (int i=1; i<=9999;i++ ){
		          int o = fooreplicator.genint(i); 
		          Random o1 = new Random();
				   String charset = "abcdefghijklmnopqrstuwvxyz";
				   String name = generateString(o1, charset, 256 );
		          
		          
		          foo knil = new foo(o,name); 
		 
		          Element element = new Element(i, knil);
		          transactionManager.begin();
		          cache.put(element);
		          transactionManager.commit();
		       
		          
		      }
		    
		  
		      

		  
		    System.out.println("After Cache Loaded");
	     try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	
	       for(int i=1; i<=10; i++){
	    	   transactionManager.begin();
	      // cache.remove(i);
	       Element elementget = cache.get(i);
	       transactionManager.commit();
	   //    System.out.println(i);
	     //  System.out.println(elementget.getObjectValue().toString());
	      rl1 = (foo) elementget.getObjectValue();  
	     //  System.out.println(rl1.getB());
	     //  deepactlink rl2 = rl1.clone();
	       
	      
		  String name = "Pete Sampras";
	
	       rl1.setB(name);

	       Element element1 = new Element(i, rl1);
	       transactionManager.begin();
	       cache.put(element1);
	       transactionManager.commit(); 
	       
	    /*  transactionManager.begin();
	       cache.remove(i);
	       transactionManager.commit();*/
	       } 
	//	}
	   
		    System.out.println("About to end ");
}
}

/*class foo{

String app;
foo(){
	app= "djkasj";
}


}*/
