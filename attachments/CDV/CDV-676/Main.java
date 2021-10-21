/**
  All content copyright (c) 2003-2007 Terracotta, Inc.,
  except as may otherwise be noted in a separate copyright notice.
  All rights reserved.
*/

import java.util.Properties;

public class Main
{
    public static Main instance = new Main ();
    private Properties properties = new Properties();

    public synchronized void run() 
    {
        System.out.println("props: " + properties.clone().getClass());
        properties = (Properties) properties.clone();
    }

    public static void main(String[] args) 
    {
        instance.run();
    }
}
