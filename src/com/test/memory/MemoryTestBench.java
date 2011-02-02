package com.test.memory;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MemoryTestBench {
	  public long calculateMemoryUsage(Object factory) {
	    Object handle = new Object();
	    long mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    long mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    handle = null;
	    System.gc(); System.gc(); System.gc(); System.gc();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    handle = new Object();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    System.gc(); System.gc(); System.gc(); System.gc();
	    mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    return mem1 - mem0;
	  }
	  public void showMemoryUsage(Object factory) {
	    long mem = calculateMemoryUsage(factory);
	    System.out.println(factory.getClass().getName() + " produced " +factory.getClass().getName() +  " which took " + mem + " bytes");
	  }
	}

    