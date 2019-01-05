package org.ljelic.instafram.util.io.stream.file;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.util.io.stream.Stream;
import org.ljelic.instafram.util.io.stream.StreamException;
import org.ljelic.instafram.util.io.stream.Streamable;

public class FileStream<T extends Streamable> implements Stream<T> {

	private static final String ERROR_CAST = FileStream.class.getSimpleName()
			+ " requires streamable parameter to be "
			+ FileStreamable.class.getSimpleName();

	@Override
    public void read(T streamable, String path) throws Exception {
		if(!(streamable instanceof FileStreamable)) {
			throw new StreamException(ERROR_CAST);
		}

		Scanner s = null;

		try {
			s = new Scanner(new File(path));

			while(s.hasNextLine()) {
				((FileStreamable) streamable).read(Config.HASHER.dehash(s.nextLine()));
			}
		}finally {
			if(s != null) {
				s.close();
			}
		}
	}

    @Override
    public void write(T streamable, String path) throws Exception {
		if(!(streamable instanceof FileStreamable)) {
			throw new StreamException(ERROR_CAST);
		}

        PrintWriter writer = null;

		try {
			writer = new PrintWriter(new FileWriter(path, false));
            writer.write(Config.HASHER.hash(((FileStreamable) streamable).write(new StringBuilder())));
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
}