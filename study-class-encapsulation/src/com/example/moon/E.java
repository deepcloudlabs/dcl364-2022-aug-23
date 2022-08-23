package com.example.moon;

import com.example.earth.A;

public class E {
	public void fun(A a) {
		// a.m_private = 42; ✘
		// a.m_protected = 42; ✘
		// a.m_default = 42; ✘
		a.m_public = 42;
	}
}
