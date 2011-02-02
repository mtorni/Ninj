package com.ninj.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface DBOTable {
	public String name();

}
