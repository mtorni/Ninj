package com.ninj.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface DBOFKey {
	public String name();	
}
