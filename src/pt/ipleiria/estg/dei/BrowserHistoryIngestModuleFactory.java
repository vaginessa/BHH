
package pt.ipleiria.estg.dei;


import org.openide.util.lookup.ServiceProvider;


import org.openide.util.NbBundle;

import org.sleuthkit.autopsy.ingest.IngestModuleFactory;
import org.sleuthkit.autopsy.ingest.DataSourceIngestModule;
import org.sleuthkit.autopsy.ingest.FileIngestModule;
import org.sleuthkit.autopsy.ingest.IngestModuleFactoryAdapter;
import org.sleuthkit.autopsy.ingest.IngestModuleGlobalSettingsPanel;
import org.sleuthkit.autopsy.ingest.IngestModuleIngestJobSettings;
import org.sleuthkit.autopsy.ingest.IngestModuleIngestJobSettingsPanel;
import org.sleuthkit.autopsy.ingest.NoIngestModuleIngestJobSettings;

/**
 * IMPORTANT TIP: This sample ingest module factory directly implements
 * IngestModuleFactory. A practical alternative, recommended if you do not need
 * to provide implementations of all of the IngestModuleFactory methods, is to
 * extend the abstract class IngestModuleFactoryAdapter to get default
 * implementations of most of the IngestModuleFactory methods.
 */

@ServiceProvider(service = IngestModuleFactory.class) // Sample is discarded at runtime 
public class BrowserHistoryIngestModuleFactory extends IngestModuleFactoryAdapter {


    static String getModuleName() {
        return NbBundle.getMessage(BrowserHistoryIngestModuleFactory.class, "BrowserHistoryIngestModuleFactory.moduleName");
    }

    /**
     * @return The module family display name. Used to identify the module in user
     * interface components and log messages
     */
    @Override
    public String getModuleDisplayName() {
        return getModuleName();
    }

    @Override
    public String getModuleDescription() {
        return NbBundle.getMessage(BrowserHistoryIngestModuleFactory.class, "BrowserHistoryIngestModuleFactory.moduleDescription");
    }


    @Override
    public String getModuleVersionNumber() {
        return "1.0.0";
    }


    /**
     * Queries the factory to determine if it is capable of creating data source
     * ingest modules. If the module family does not include data source ingest
     * modules, the factory may extend IngestModuleFactoryAdapter to get an
     * implementation of this method that returns false.
     *
     * @return True if the factory can create data source ingest modules.
     */
    @Override
    public boolean isDataSourceIngestModuleFactory() {
        return true;
    }

    /**
     * @param settings The settings for the ingest job.
     *
     * @return A data source ingest module instance.
     */
    @Override
    public DataSourceIngestModule createDataSourceIngestModule(IngestModuleIngestJobSettings settings) {
        if (!(settings instanceof NoIngestModuleIngestJobSettings)) {
            throw new IllegalArgumentException("Expected settings argument to be instanceof SampleModuleIngestJobSettings");
        }
        return new BrowserHistoryDataSourceIngestModule();
    }

}
