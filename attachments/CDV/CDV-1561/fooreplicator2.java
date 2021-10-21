package com.ehcache.performance.test;




import com.example.object.foo;




import java.math.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.TransactionController;





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
		   // ArrayList<String> namelist = new ArrayList<String>();  //ArrayList to hold names which will then be used to lookup Objects from the Cache
		   // ArrayList<int> idlist = new ArrayList<int>();
		  //  RunStatistics r = new RunStatistics(); 
		  //  long Objectloadtime;
		 //   ArrayList<Long> timeList= new ArrayList<Long>();
		    foo rl1 = new foo();
		    CacheManager manager = new CacheManager("ehcache_test.xml");
		    Cache cache = manager.getCache("fooCache");	
		  //  int cacheSize = cache.getKeys().size();
		 //   System.out.println("Cache size at Startup is " + " " + cacheSize);  //Get no of Objects in Cache
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

		for (; ;){
	       for(int i=1; i<=10; i++){
	       transactionManager.begin();
	       Element elementget = cache.get(i);
	       
	       transactionManager.commit();
	   //    System.out.println(i);
	     //  System.out.println(elementget.getObjectValue().toString());
	       if (elementget != null){
	       rl1 = (foo) elementget.getObjectValue();  
	       System.out.println(rl1.getB());
	       }
	       else{
	    	   System.out.println("No element exists");
	       }
	      // System.out.println(rl1.toString());
	      // deepactlink rl2 = rl1.clone();
	       
	      
		  
	       }
	
		}		  //  System.out.println("About to end ");
}
}


