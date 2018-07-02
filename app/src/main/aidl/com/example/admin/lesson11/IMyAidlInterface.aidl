// IMyAidlInterface.aidl
package com.example.admin.lesson11;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String read();
    void write(String string);

}
