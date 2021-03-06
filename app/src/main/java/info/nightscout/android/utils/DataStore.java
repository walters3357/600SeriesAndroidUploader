package info.nightscout.android.utils;


import java.util.Date;

import info.nightscout.android.model.medtronicNg.PumpStatusEvent;
import io.realm.Realm;

/**
 * Created by volker on 30.03.2017.
 */

public class DataStore {
    private static DataStore instance;

    private PumpStatusEvent lastPumpStatus;
    private int uploaderBatteryLevel = 0;
    private int unavailableSGVCount = 0;
    private long activePumpMac = 0;

    private DataStore() {}

    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            instance = new DataStore();

            // set some initial dummy values
            PumpStatusEvent dummyStatus = new PumpStatusEvent();
            dummyStatus.setSgvDate(new Date());

            // bypass setter to avoid dealing with a real Realm object
            instance.lastPumpStatus = dummyStatus;
        }

        return instance;
    }

    public PumpStatusEvent getLastPumpStatus() {
        return lastPumpStatus;
    }

    public void setLastPumpStatus(PumpStatusEvent lastPumpStatus) {
        Realm realm = Realm.getDefaultInstance();

        this.lastPumpStatus = realm.copyFromRealm(lastPumpStatus);
        if (!realm.isClosed()) realm.close();
    }

    public int getUploaderBatteryLevel() {
        return uploaderBatteryLevel;
    }

    public void setUploaderBatteryLevel(int uploaderBatteryLevel) {
        this.uploaderBatteryLevel = uploaderBatteryLevel;
    }

    public int getUnavailableSGVCount() {
        return unavailableSGVCount;
    }

    public int incUnavailableSGVCount() {
        return unavailableSGVCount++;
    }

    public void clearUnavailableSGVCount() {
        this.unavailableSGVCount = 0;
    }
    public void setUnavailableSGVCount(int unavailableSGVCount) {
        this.unavailableSGVCount = unavailableSGVCount;
    }

    public long getActivePumpMac() {
        return activePumpMac;
    }

    public void setActivePumpMac(long activePumpMac) {
        this.activePumpMac = activePumpMac;
    }
}
