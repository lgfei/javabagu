package com.lgfei.javabagu.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //testNoUID();
        testWithUID();
    }

    private static void testNoUID(){
        String filePath = "E:\\Test\\serialize\\NoUID.ser";
        NoUID obj = new NoUID();
        obj.setId(123);
        obj.setName("NoUID");
        obj.setFlag(true);
        obj.setTime(new Date());
        // 序列化
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
//            oos.writeObject(obj);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        // 反序列化
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            NoUID newObj = (NoUID) ois.readObject();
            System.out.println(newObj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void testWithUID(){
        String filePath = "E:\\Test\\serialize\\WithUID.ser";
        WithUID obj = new WithUID();
        obj.setId(123);
        obj.setName("WithUID");
        obj.setFlag(true);
        obj.setTime(new Date());
        // 序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 反序列化
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            WithUID newObj = (WithUID) ois.readObject();
            System.out.println(newObj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
