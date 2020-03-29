package com.ddk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import com.ddk.common.Demo;
import com.ddk.common.Address;

public class SerializationDeserializationDemo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.name = "Karthi";
        demo.age = 46;
        demo.address = new Address();
        demo.address.address1 = "1345";
        demo.address.address2 = "S Olson Ave";
        demo.address.city = "Mountain House";
        demo.address.city = "CA";
        demo.address.zip = 95391;
        demo.address.country = "USA";

/*
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String demoJson = gson.toJson(demo);
        System.out.println(demoJson);
*/

        try ( FileOutputStream file = new FileOutputStream("file.ser"); ObjectOutputStream out = new ObjectOutputStream(file) ){
            out.writeObject(demo);
            System.out.println("Object has been serialized");
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }

        try (FileInputStream file = new FileInputStream("file.ser"); ObjectInputStream in = new ObjectInputStream(file) ){
            Demo demoDeser = (Demo) in.readObject();
            System.out.println("Object has been deserialized");
            System.out.println(demoDeser);

/*
            String demoJsonDeser = gson.fromJson(demoJson, Demo.class);
            System.out.println(demoDeser.toString());
*/
        } catch ( IOException | ClassNotFoundException ioe ) {
            ioe.printStackTrace();
        }
    }
}
