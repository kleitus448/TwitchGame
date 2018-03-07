package com.ludux.twitchgame;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class OutputSerial extends ObjectOutputStream implements Serializable, Cloneable {

    public OutputSerial(OutputStream out) throws IOException {
        super(out);
    }

    public OutputSerial CloneThis() throws CloneNotSupportedException {
        return (OutputSerial) this.clone();
    }

}
