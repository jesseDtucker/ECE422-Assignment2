package com.jetucker;

/**
 * Created by Jesse on 2014-11-24.
 */
public class Encryptor
{
    public native byte[] Encrypt(byte[] data, long key);
    public native byte[] Decrypt(byte[] data, long key);
}
