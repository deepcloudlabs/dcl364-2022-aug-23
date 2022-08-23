package com.example.earth;

public class B extends A {
	public void fun() {
		// m_private = 42; ✘
		m_protected = 42;
		m_default = 42;
		m_public = 42;
	}
}
