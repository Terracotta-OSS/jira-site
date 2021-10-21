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





public class TxmodeTester {
	

	
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
	    foo rl1 = new foo();
		    CacheManager manager = new CacheManager("ehcache.xml");
		    Cache cache = manager.getCache("fooCache");	
		    cache.getCacheConfiguration().setTransactionalMode("LOCAL");
		    
		    transactionManager = manager.getTransactionController();
	             for (int i=1; i<=9999;i++ ){
		          int o = TxmodeTester.genint(i); 
		          Random o1 = new Random();
				   String charset = "abcdefghijklmnopqrstuwvxyz";
				   String name = generateString(o1, charset, 256 );
		          
		          
		          foo knil = new foo(o,name); 
		 
		          Element element = new Element(i, knil);
		          //transactionManager.begin();
		          cache.put(element);
		       //   transactionManager.commit();
		       
		          
		      }
		    
		  
		      

		  
		    System.out.println("After Cache Loaded");
	 }}
