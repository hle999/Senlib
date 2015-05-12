package com.sen.lib.bluetooth.gatt;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothGattCharDescrID implements Parcelable {
    private final BluetoothGattID mSrvcId;
    private final BluetoothGattID mCharId;
    private final BluetoothGattID mDescrId;
    public static final Creator<BluetoothGattCharDescrID> CREATOR = new Creator() {
        public BluetoothGattCharDescrID createFromParcel(Parcel source) {
            int instId = source.readInt();
            int uuidtype = source.readInt();
            int serviceType = source.readInt();
            BluetoothGattID serviceId;
            String descrUuid;
            int descrUuid1;
            if(uuidtype == 16) {
                descrUuid = source.readString();
                serviceId = new BluetoothGattID(instId, descrUuid, serviceType);
            } else {
                descrUuid1 = source.readInt();
                serviceId = new BluetoothGattID(instId, descrUuid1, serviceType);
            }

            instId = source.readInt();
            uuidtype = source.readInt();
            BluetoothGattID charId;
            if(uuidtype == 16) {
                descrUuid = source.readString();
                charId = new BluetoothGattID(instId, descrUuid);
            } else {
                descrUuid1 = source.readInt();
                charId = new BluetoothGattID(instId, descrUuid1);
            }

            uuidtype = source.readInt();
            BluetoothGattID descrId;
            if(uuidtype == 16) {
                descrUuid = source.readString();
                descrId = new BluetoothGattID(descrUuid);
            } else {
                descrUuid1 = source.readInt();
                descrId = new BluetoothGattID(descrUuid1);
            }

            return new BluetoothGattCharDescrID(serviceId, charId, descrId);
        }

        public BluetoothGattCharDescrID[] newArray(int size) {
            return new BluetoothGattCharDescrID[size];
        }
    };

    public BluetoothGattCharDescrID(BluetoothGattID srvcId, BluetoothGattID charId, BluetoothGattID descrId) {
        this.mSrvcId = srvcId;
        this.mCharId = charId;
        this.mDescrId = descrId;
    }

    public BluetoothGattID getSrvcId() {
        return this.mSrvcId;
    }

    public BluetoothGattID getCharId() {
        return this.mCharId;
    }

    public BluetoothGattID getDescrId() {
        return this.mDescrId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int serviceUuidType = this.mSrvcId.getUuidType();
        int charUuidType = this.mCharId.getUuidType();
        int descrUuidType = this.mDescrId.getUuidType();
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

        dest.writeInt(this.mDescrId.getUuidType());
        if(charUuidType == 16) {
            dest.writeString(this.mDescrId.toString());
        } else {
            dest.writeInt(this.mDescrId.getUuid16());
        }

    }
}

