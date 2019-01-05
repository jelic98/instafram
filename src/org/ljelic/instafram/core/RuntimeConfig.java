package org.ljelic.instafram.core;

import org.ljelic.instafram.observer.command.CommandQueue;
import org.ljelic.instafram.observer.command.ThreadOptions;
import org.ljelic.instafram.observer.command.WriteSettingsAction;
import org.ljelic.instafram.util.Machine;
import org.ljelic.instafram.util.io.stream.Streamable;
import org.ljelic.instafram.util.io.stream.file.FileStreamable;
import org.ljelic.instafram.util.io.stream.serial.SerialStreamable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RuntimeConfig implements FileStreamable<RuntimeConfig>, SerialStreamable<RuntimeConfig>, Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<String, String> parameters;

    RuntimeConfig() {
        parameters = new HashMap<>();

        for(Parameters p : Parameters.values()) {
            parameters.put(p.name(), p.defaultValue());
        }
    }

    public String get(Parameters p) {
        return parameters.get(p.name());
    }

    public String get(String key) {
        return parameters.get(key);
    }

    public void set(Parameters p, String value) {
        parameters.put(p.name(), value);

        CommandQueue.push(new WriteSettingsAction(), ThreadOptions.SINGLE_THREAD);
    }

    public void reset(Parameters p) {
        set(p, p.defaultValue());
    }

    public Set<String> getKeys() {
        Set<String> keys = new HashSet<>();

        for(String key : parameters.keySet()) {
            Parameters p;

            try {
                p = Parameters.valueOf(key);
            }catch(IllegalArgumentException e) {
                continue;
            }

            if(p.isEditable()) {
                keys.add(key);
            }
        }

        return keys;
    }

    @Override
    public void read(String line) {
        String[] tokens = line.split("=");

        parameters.put(tokens[0], tokens[1]);
    }

    @Override
    public String write(StringBuilder builder) {
        for(String key : parameters.keySet()) {
            builder.append(key);
            builder.append("=");
            builder.append(parameters.get(key));
            builder.append(Machine.lineSeparator());
        }

        return builder.toString();
    }

    @Override
    public void handle(Object obj) {
        if(obj instanceof RuntimeConfig) {
            parameters.clear();
            parameters.putAll(((RuntimeConfig) obj).parameters);
        }
    }

    @Override
    public Streamable<RuntimeConfig> from(RuntimeConfig streamable) {
        return this;
    }

    @Override
    public RuntimeConfig get() {
        return this;
    }
}