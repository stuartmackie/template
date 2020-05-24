package template.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * template
 * 
 * @author Stuart Mackie (stuart@foobar.fyi).
 * @version May 2020.
 */
public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {

    /*
     * Initialisation:
     */

    logger.info("template");

    CompositeConfiguration config = new CompositeConfiguration();

    try {

      // System properties (-Dfoo=bar):
      config.addConfiguration(new SystemConfiguration());

      // (config.xml,config.properties):
      Configurations configs = new Configurations();
      config.addConfiguration(configs.xml("config.xml"));
      config.addConfiguration(configs.properties("config.properties"));

      /*
       * DEBUG:
       */

      Iterator<String> configkeys = config.getKeys();
      List<String> properties = new ArrayList<String>();

      while (configkeys.hasNext())
        properties.add(configkeys.next());
      Collections.sort(properties);

      for (String property : properties)
        logger.debug(property + "=" + config.getProperty(property));

    } catch (ConfigurationException e) {
      logger.error("Configuration error.", e);
      System.exit(-1);
    }

  }
}
