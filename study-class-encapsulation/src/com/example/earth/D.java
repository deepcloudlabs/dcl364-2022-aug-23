package com.example.earth;

public class D {
	public void fun(A a) {
		// a.m_private = 42; ✘
		a.m_protected = 42; // default
		a.m_default = 42;
		a.m_public = 42;
	}
}
