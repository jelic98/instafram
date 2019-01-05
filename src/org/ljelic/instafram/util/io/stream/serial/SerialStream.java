package org.ljelic.instafram.util.io.stream.serial;

import org.ljelic.instafram.util.io.stream.Stream;
import org.ljelic.instafram.util.io.stream.StreamException;
import org.ljelic.instafram.util.io.stream.Streamable;
import java.io.*;

public class SerialStream<T extends Streamable> implements Stream<T> {

    private static final String ERROR_CAST = SerialStream.class.getSimpleName()
            + " requires streamable parameter to be "
            + SerialStreamable.class.getSimpleName();

    @Override
    public void read(T streamable, String path) throws Exception {
        if(!(streamable instanceof SerialStreamable)) {
            throw new StreamException(ERROR_CAST);
        }

        File file = new File(path);

        if(!file.exists() || file.length() == 0) {
            return;
        }

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            Object obj = ois.readUnshared();

            SerialStreamable serial = (SerialStreamable) streamable;
            serial.handle(obj);
        }finally {
            if(ois != null) {
                ois.close();
            }

            if(fis != null) {
                fis.close();
            }
        }
    }

    @Override
    public void write(T streamable, String path) throws Exception {
        if(path == null) {
            return;
        }

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(new File(path));
            oos = new ObjectOutputStream(fos);
            oos.writeUnshared(streamable.get());
        }finally {
            if(oos != null) {
                oos.close();
            }

            if(fos != null) {
                fos.close();
            }
        }
    }
}