package com.sen.lib.bluetooth.gatt;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

public class BluetoothGattID implements Parcelable {
    private int mInstId;
    private UUID mUuid128;
    private int mUuid16;
    private int mType;
    private int mServiceType;
    private static final String TAG = "BluetoothGattID";
    public static final Creator<BluetoothGattID> CREATOR = new Creator() {
        public BluetoothGattID createFromParcel(Parcel source) {
            int instId = source.readInt();
            int type = source.readInt();
            int serviceType = source.readInt();
            if(type == 16) {
                String uuid1 = source.readString();
                return new BluetoothGattID(instId, uuid1, serviceType);
            } else {
                int uuid = source.readInt();
                return new BluetoothGattID(instId, uuid, serviceType);
            }
        }

        public BluetoothGattID[] newArray(int size) {
            return new BluetoothGattID[size];
        }
    };

    private void initServiceType(int serviceType) {
        if(serviceType == 0 || serviceType == 1) {
            this.mServiceType = serviceType;
        }

    }

    public BluetoothGattID(int instId, UUID uuid) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mInstId = instId;
        this.mUuid128 = uuid;
        this.mType = 16;
    }

    public BluetoothGattID(int instId, UUID uuid, int serviceType) {
        this(instId, uuid);
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(int instId, long uuidLsb, long uuidMsb) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mInstId = instId;
        this.mUuid128 = new UUID(uuidMsb, uuidLsb);
        this.mType = 16;
    }

    public BluetoothGattID(long uuidLsb, long uuidMsb, int uuidType) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        if(uuidType == 16) {
            this.mUuid128 = new UUID(uuidMsb, uuidLsb);
            this.mType = 16;
        } else {
            this.mUuid16 = (int)uuidLsb;
            this.mType = 2;
        }

    }

    public BluetoothGattID(int instId, int uuidType, long uuidLsb, long uuidMsb) {
        this(uuidLsb, uuidMsb, uuidType);
        this.mInstId = instId;
    }

    public BluetoothGattID(int instId, long uuidLsb, long uuidMsb, int serviceType) {
        this(instId, uuidLsb, uuidMsb);
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(int instId, int uuidType, long uuidLsb, long uuidMsb, int serviceType) {
        this(uuidLsb, uuidMsb, uuidType);
        this.mInstId = instId;
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(int instId, String sUUID) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mInstId = instId;
        this.mUuid128 = UUID.fromString(sUUID);
        this.mType = 16;
    }

    public BluetoothGattID(int instId, String sUUID, int serviceType) {
        this(instId, sUUID);
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(int instId, int uuid) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mInstId = instId;
        this.mUuid16 = uuid;
        this.mType = 2;
    }

    public BluetoothGattID(int instId, int iUUID, int serviceType) {
        this(instId, iUUID);
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(UUID uuid) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mUuid128 = uuid;
        this.mType = 16;
    }

    public BluetoothGattID(UUID uuid, int serviceType) {
        this(uuid);
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(String sUUID) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mUuid128 = UUID.fromString(sUUID);
        this.mType = 16;
    }

    public BluetoothGattID(String sUUID, int serviceType) {
        this(sUUID);
        this.initServiceType(serviceType);
    }

    public BluetoothGattID(int uuid) {
        this.mInstId = 0;
        this.mUuid128 = null;
        this.mUuid16 = -1;
        this.mType = -1;
        this.mServiceType = -1;
        this.mUuid16 = uuid;
        this.mType = 2;
    }

    public UUID getUuid() {
        return this.mUuid128;
    }

    public int getUuid16() {
        return this.mUuid16;
    }

    public void setInstId(int instId) {
        this.mInstId = instId;
    }

    public int getUuidType() {
        return this.mType;
    }

    public int getInstanceID() {
        return this.mInstId;
    }

    public int getServiceType() {
        return this.mServiceType;
    }

    public void setServiceType(int serviceType) {
        this.mServiceType = serviceType;
    }

    public long getLeastSignificantBits() {
        return this.mType == 16?this.mUuid128.getLeastSignificantBits():(long)this.mUuid16;
    }

    public long getMostSignificantBits() {
        return this.mType == 16?this.mUuid128.getMostSignificantBits():0L;
    }

    public int hashCode() {
        return this.mType == 16?this.mUuid128.hashCode():(new Integer(this.mUuid16)).hashCode();
    }

    public boolean equals(Object target) {
        if(target == null) {
            return false;
        } else if(this == target) {
            return true;
        } else if(!(target instanceof BluetoothGattID)) {
            return false;
        } else {
            BluetoothGattID targetId = (BluetoothGattID)target;
            return this.mType != targetId.getUuidType()?false:(this.mServiceType != targetId.getServiceType()?false:(this.mType == 16 && targetId.getInstanceID() == this.mInstId && this.mUuid128.equals(targetId.getUuid())?true:this.mType == 2 && targetId.getInstanceID() == this.mInstId && this.mUuid16 == targetId.getUuid16()));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mInstId);
        dest.writeInt(this.mType);
        dest.writeInt(this.mServiceType);
        if(this.mType == 16) {
            dest.writeString(this.mUuid128.toString());
        } else {
            dest.writeInt(this.mUuid16);
        }

    }

    public String toString() {
        return this.mType == 16?(this.mUuid128 == null?null:this.mUuid128.toString()):String.valueOf(String.format("%08x-0000-1000-8000-00805f9b34fb", new Object[]{Integer.valueOf(this.mUuid16)}));
    }
}

