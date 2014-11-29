package com.jetucker;

/**
 * Created by Jesse on 2014-11-24.
 */
public class Encryptor
{
    public static native int GetKeySize();
    public native byte[] Encrypt(byte[] data, byte[] key);
    public native byte[] Decrypt(byte[] data, byte[] key);
}
