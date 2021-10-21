/**
  All content copyright (c) 2003-2007 Terracotta, Inc.,
  except as may otherwise be noted in a separate copyright notice.
  All rights reserved.
*/

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main
{
    public static final int COUNT = 200;

    public static Main instance = new Main ();
    private AtomicInteger counter = new AtomicInteger(0);
    private Map map = new HashMap();

    private byte[] read() throws Exception
    {
        File file = new File("big.xml");
        if (!file.exists()) {
            throw new Exception("can't find file: " + file);
        }

        FileInputStream fis = new FileInputStream(file); 
        byte[] buffer = new byte[(int) file.length()];
        fis.read(buffer);

        System.out.println("Read buffer.  Len = " + buffer.length);        

        return buffer;
    }

    public void load() throws Exception
    {
        byte[] file = read();
  
        System.out.println("writing to map"); 
        for (int i = 0; i < COUNT; i++) {
            if (i % 1000 == 0) { System.out.print("."); System.out.flush(); }
            synchronized (map) { 
              map.put(i, new String(file));
            }
        }

        Thread.currentThread().join();
    }

    public void print() throws Exception
    {
        Thread.sleep(10000);
        for (int i = 0; i < COUNT; i++) {
            if (i % 1000 == 0) { System.out.print("."); System.out.flush(); }
            synchronized (map) { 
              System.out.println(map.get(i).hashCode());
            }
            System.gc(); 
            Thread.sleep(100);
        }

        Thread.currentThread().join();
    }


    public void run() throws Exception
    {
        if (counter.getAndIncrement() == 0) {
            load();
        } else {
            print();
        }
    }

    public static void main(String[] args) throws Exception
    {
        instance.run();
    }
}
