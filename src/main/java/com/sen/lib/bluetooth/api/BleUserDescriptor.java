package com.sen.lib.bluetooth.api;

import android.os.Parcel;
import android.os.Parcelable;

public class BleUserDescriptor extends BleDescriptor implements Parcelable {
    private static final String TAG = "BleUserDescriptor";
    public static final Creator<BleUserDescriptor> CREATOR = new Creator() {
        public BleUserDescriptor createFromParcel(Parcel source) {
            return new BleUserDescriptor(source);
        }

        public BleUserDescriptor[] newArray(int size) {
            return new BleUserDescriptor[size];
        }
    };

    public BleUserDescriptor(Parcel source) {
        super(source);
    }

    public BleUserDescriptor(BleGattID id) {
        super(id);
    }
}

