package com.azaroff.projects.craftsman.token.service.util;

/**
 * Created by Dmitiy on 22.08.2016.
 */

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

@Service("kryoSerializer")
public class KryoSerializer {

    private KryoPool kryoPool;

    public KryoSerializer() {
        kryoPool = initKryoPool();
    }

    public byte[] serialize(final Object object) {
        return kryoPool.run(new KryoCallback<byte[]>() {
            public byte[] execute(Kryo kryo) {
                Output output = new Output(new ByteArrayOutputStream());
                kryo.writeClassAndObject(output, object);
                output.flush();
                return Arrays.copyOfRange(output.getBuffer(), 0, (int) output.total());
            }
        });
    }

    public Object deserialize(final byte[] bytes) {
        return kryoPool.run(new KryoCallback<Object>() {
            public Object execute(Kryo kryo) {
                Input input = new Input(bytes);
                return kryo.readClassAndObject(input);
            }
        });
    }

    private KryoPool initKryoPool() {
        KryoFactory factory = new KryoFactory() {
            public Kryo create() {
                return new Kryo();
            }
        };
        return new KryoPool.Builder(factory).softReferences().build();
    }

}
