package com.sen.lib.bluetooth.api;

import android.os.ParcelUuid;

public interface IBleServiceEventHandler {
    void onServiceRegistered(byte var1, int var2);

    void onServiceCreated(byte var1, int var2);

    void onIncludedServiceAdded(byte var1, int var2);

    void onCharacteristicAdded(byte var1, ParcelUuid var2, int var3);

    void onCharacteristicDescrAdded(byte var1, ParcelUuid var2, int var3);

    void onServiceDeleted(byte var1);

    void onServiceStarted(byte var1, byte var2);

    void onServiceStopped(byte var1);

    void onHandleValueIndicationCompleted(byte var1, int var2);

    void onHandleValueNotificationCompleted(byte var1, int var2);

    void onResponseSendCompleted(byte var1, int var2);

    void onAttributeRequestRead(String var1, int var2, int var3, int var4, int var5, boolean var6);

    void onAttributeRequestWrite(String var1, int var2, int var3, int var4, boolean var5, int var6, boolean var7, int var8, byte[] var9);
}

