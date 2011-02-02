package com.ninj.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface DBOAutoKey {
	public String name();	
}
