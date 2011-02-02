package com.test.encryption;

import java.io.ByteArrayOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import com.sun.org.apache.xml.internal.security.utils.Base64;




public class EncodeDecode {


    private static void copy(InputStream in, OutputStream out) throws IOException {

        byte[] barr = new byte[1024];

        while(true) {

                int r = in.read(barr);

                if(r <= 0) {

                        break;

                }

                out.write(barr, 0, r);

        }

    }


    private static byte[] loadFile(File file) throws IOException {

        InputStream in = new FileInputStream(file);

        try {

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                copy(in, buffer);

                return buffer.toByteArray();

        } finally {

                in.close();

        }

    }

    

    

    private static void writeBytesToFile(byte[] b) throws IOException {

    OutputStream fos = new FileOutputStream("./src/com/spazz/test/xml/gaming-computer-rendered.jpg");

    fos.write(b);

    }


    public static void main(String[] args) throws Exception {

        File file = new File("./src/com/spazz/test/xml/gaming-computer.jpg");

        byte[] bytes = loadFile(file);


        //all chars in encoded are guaranteed to be 7-bit ASCII

       String encoded = Base64.encode(bytes);



        System.out.println(encoded);


        byte[] decoded = Base64.decode(encoded);


        writeBytesToFile(decoded);

        

        // check

        for (int i = 0; i < bytes.length; i++) {

          

                assert bytes[i] == decoded[i];

        }

    }


}