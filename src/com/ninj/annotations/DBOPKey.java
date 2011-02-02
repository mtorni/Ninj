package com.ninj.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface DBOPKey {
	public String name();	
}
