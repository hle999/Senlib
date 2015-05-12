package com.sen.lib.bluetooth.gatt;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothGattCharID implements Parcelable {
    private final BluetoothGattID mSrvcId;
    private final BluetoothGattID mCharId;
    public static final Creator<BluetoothGattCharID> CREATOR = new Creator() {
        public BluetoothGattCharID createFromParcel(Parcel source) {
            int instId = source.readInt();
            int uuidtype = source.readInt();
            int serviceType = source.readInt();
            BluetoothGattID serviceId;
            String charUuid;
            int charUuid1;
            if(uuidtype == 16) {
                charUuid = source.readString();
                serviceId = new BluetoothGattID(instId, charUuid, serviceType);
            } else {
                charUuid1 = source.readInt();
                serviceId = new BluetoothGattID(instId, charUuid1, serviceType);
            }

            instId = source.readInt();
            uuidtype = source.readInt();
            BluetoothGattID charId;
            if(uuidtype == 16) {
                charUuid = source.readString();
                charId = new BluetoothGattID(instId, charUuid);
            } else {
                charUuid1 = source.readInt();
                charId = new BluetoothGattID(instId, charUuid1);
            }

            return new BluetoothGattCharID(serviceId, charId);
        }

        public BluetoothGattCharID[] newArray(int size) {
            return new BluetoothGattCharID[size];
        }
    };

    public BluetoothGattCharID(BluetoothGattID srvcId, BluetoothGattID charId) {
        this.mSrvcId = srvcId;
        this.mCharId = charId;
    }

    public BluetoothGattID getSrvcId() {
        return this.mSrvcId;
    }

    public BluetoothGattID getCharId() {
        return this.mCharId;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(BluetoothGattCharID target) {
        return target.getCharId().equals(this.getCharId()) && target.getSrvcId().equals(this.getSrvcId());
    }

    public void writeToParcel(Parcel dest, int flags) {
        int serviceUuidType = this.mSrvcId.getUuidType();
        int charUuidType = this.mCharId.getUuidType();
        dest.writeInt(this.mSrvcId.getInstanceID());
        dest.writeInt(this.mSrvcId.getUuidType());
        dest.writeInt(this.mSrvcId.getServiceType());
        if(serviceUuidType == 16) {
            dest.writeString(this.mSrvcId.toString());
        } else {
            dest.writeInt(this.mSrvcId.getUuid16());
        }

        dest.writeInt(this.mCharId.getInstanceID());
        dest.writeInt(this.mCharId.getUuidType());
        if(charUuidType == 16) {
            dest.writeString(this.mCharId.toString());
        } else {
            dest.writeInt(this.mCharId.getUuid16());
        }

    }
}

