package com.test.image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.ninj.util.WebUtil;

public class TestJCrop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{




			URL yahoo = new URL("http://localhost/NinjWeb/Jcrop/demos/demo_files/flowers.jpg");


			//byte[] b = WebUtil.getBytesFromInputStream(yahoo.openStream());
			MyJCrop j = new MyJCrop("/prac", "/prac");
			j.cropImage(yahoo, "flhyahoo.jpg", 100, 100, 200, 200);



         //   for(int x = 0; x < b.length; x++){
         //   	System.out.println(b[x]);
         //   }



		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();


		}

	}

}
