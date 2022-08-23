package com.example.moon;

import com.example.earth.A;

public class C extends A {
	public void fun() {
		// m_private = 42; ✘
		m_protected = 42;
		// m_default = 42; ✘
		m_public = 42;		
	}
}
