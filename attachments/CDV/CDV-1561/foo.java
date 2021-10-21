package com.example.object;

import java.io.Serializable;

public class foo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118693111291910537L;
	/**
	 * 
	 */
	
	int a;
	String b;
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public foo(int x, String y){
		this.a = x;
		this.b = y;
	}

	public foo() {
		// TODO Auto-generated constructor stub
	}
}
