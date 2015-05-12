package com.sen.lib.bluetooth.api;

import com.sen.lib.bluetooth.gatt.BluetoothGattID;

/**
 * Created by Administrator on 15-4-10.
 */
public class BleApiHelper {
    public BleApiHelper() {
    }

    public static BleGattID gatt2BleID(BluetoothGattID gattID) {
        return gattID.getUuidType() == 16?new BleGattID(gattID.getInstanceID(), gattID.getUuid(), gattID.getServiceType()):(gattID.getUuidType() == 2?new BleGattID(gattID.getInstanceID(), gattID.getUuid16(), gattID.getServiceType()):null);
    }
}

