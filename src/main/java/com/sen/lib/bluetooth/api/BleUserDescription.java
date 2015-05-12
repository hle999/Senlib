package com.sen.lib.bluetooth.api;

import android.os.Parcel;
import android.os.Parcelable;

public class BleUserDescription extends BleDescriptor implements Parcelable {
    private static final String TAG = "BleUserDescription";
    public static final Creator<BleUserDescription> CREATOR = new Creator() {
        public BleUserDescription createFromParcel(Parcel source) {
            return new BleUserDescription(source);
        }

        public BleUserDescription[] newArray(int size) {
            return new BleUserDescription[size];
        }
    };

    public BleUserDescription(Parcel source) {
        super(source);
    }

    public BleUserDescription() {
        super(new BleGattID(10497));
    }
}

