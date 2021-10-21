package test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: jandam
 * Date: Aug 6, 2010
 * Time: 9:58:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestEHCache {
    public static final int DATA_COUNT = 800;
    public static final int ARRAY_SIZE = 1000000;

    public static void main(String[] args) {
         CacheManager manager = CacheManager.create();

        try {
            CacheConfiguration configuration = new CacheConfiguration("test1", 5).memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU).overflowToDisk(true).eternal(true);
            Cache cache = new Cache(configuration);

            manager.addCache(cache);

            List<UUID> idList = new ArrayList<UUID>();
            for(int index = 0; index < DATA_COUNT; index++) {
                UUID uuid = UUID.randomUUID();
                float[] values = new float[ARRAY_SIZE];
                Arrays.fill(values, index);
                Data data = new Data(uuid, values);
                idList.add(uuid);
                cache.put(new Element(uuid, data.getData()));
            }

            for(int index = 0; index < 10; index++) {
                UUID uuid = idList.get(100 + index);
                Element element = cache.get(uuid);
                if(element == null) {
                    System.out.println("Element is null: " + uuid);
                } else {
                    float[] data = (float[]) element.getValue();
                    System.out.println("Data: " + data);
                }
            }
        } finally {
            manager.shutdown();
        }
    }

    public static class Data implements Serializable {

        private static final long serialVersionUID = 2813001373002622309L;

        private final UUID uuid;
        private final float[] data;

        public Data(final UUID uuid, final float[] data) {
            this.uuid = uuid;
            this.data = data;
        }

        public UUID getUuid() {
            return uuid;
        }

        public float[] getData() {
            return data;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Data");
            sb.append("{data=").append(data == null ? "null" : data.length);
            sb.append(", uuid=").append(uuid);
            sb.append('}');
            return sb.toString();
        }
    }
}
