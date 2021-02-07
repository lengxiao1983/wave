package com.wave.expr.network;

import com.wave.expr.exception.WaveException;

import java.io.*;

/**
 * @author liqiu.qlq
 */
public abstract class AbstractSerialize<T extends AbstractMessage> {
    public byte[] serialize(T object) throws IOException {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            throw new WaveException(e);
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (baos != null) {
                baos.close();
            }
        }
    }

    public T deSerialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T)ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new WaveException(e);
        }
    }

}
