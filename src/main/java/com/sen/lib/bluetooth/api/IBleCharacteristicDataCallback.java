package com.sen.lib.bluetooth.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.sen.lib.bluetooth.gatt.BluetoothGattID;

public interface IBleCharacteristicDataCallback extends IInterface {
    void onGetFirstCharacteristic(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    void onGetFirstCharacteristicDescriptor(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4, BluetoothGattID var5) throws RemoteException;

    void onGetNextCharacteristic(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    void onGetNextCharacteristicDescriptor(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4, BluetoothGattID var5) throws RemoteException;

    void onReadCharacteristicValue(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4, byte[] var5) throws RemoteException;

    void onReadCharDescriptorValue(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4, BluetoothGattID var5, byte[] var6) throws RemoteException;

    void onWriteCharValue(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    void onWriteCharDescrValue(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4, BluetoothGattID var5) throws RemoteException;

    void onRegForNotifications(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    void onUnregisterNotifications(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    void onNotify(int var1, String var2, BluetoothGattID var3, BluetoothGattID var4, boolean var5, byte[] var6) throws RemoteException;

    void onGetFirstIncludedService(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    void onGetNextIncludedService(int var1, int var2, BluetoothGattID var3, BluetoothGattID var4) throws RemoteException;

    public abstract static class Stub extends Binder implements IBleCharacteristicDataCallback {
        private static final String DESCRIPTOR = "com.broadcom.bt.le.api.IBleCharacteristicDataCallback";
        static final int TRANSACTION_onGetFirstCharacteristic = 1;
        static final int TRANSACTION_onGetFirstCharacteristicDescriptor = 2;
        static final int TRANSACTION_onGetNextCharacteristic = 3;
        static final int TRANSACTION_onGetNextCharacteristicDescriptor = 4;
        static final int TRANSACTION_onReadCharacteristicValue = 5;
        static final int TRANSACTION_onReadCharDescriptorValue = 6;
        static final int TRANSACTION_onWriteCharValue = 7;
        static final int TRANSACTION_onWriteCharDescrValue = 8;
        static final int TRANSACTION_onRegForNotifications = 9;
        static final int TRANSACTION_onUnregisterNotifications = 10;
        static final int TRANSACTION_onNotify = 11;
        static final int TRANSACTION_onGetFirstIncludedService = 12;
        static final int TRANSACTION_onGetNextIncludedService = 13;

        public Stub() {
            this.attachInterface(this, "com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
        }

        public static IBleCharacteristicDataCallback asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                return (IBleCharacteristicDataCallback)(iin != null && iin instanceof IBleCharacteristicDataCallback?(IBleCharacteristicDataCallback)iin:new IBleCharacteristicDataCallback.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            int _arg1;
            BluetoothGattID _arg2;
            BluetoothGattID _arg3;
            byte[] _arg5;
            BluetoothGattID _arg41;
            switch(code) {
                case 1:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onGetFirstCharacteristic(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg41 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg41 = null;
                    }

                    this.onGetFirstCharacteristicDescriptor(_arg0, _arg1, _arg2, _arg3, _arg41);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onGetNextCharacteristic(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg41 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg41 = null;
                    }

                    this.onGetNextCharacteristicDescriptor(_arg0, _arg1, _arg2, _arg3, _arg41);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    byte[] _arg42 = data.createByteArray();
                    this.onReadCharacteristicValue(_arg0, _arg1, _arg2, _arg3, _arg42);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg41 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg41 = null;
                    }

                    _arg5 = data.createByteArray();
                    this.onReadCharDescriptorValue(_arg0, _arg1, _arg2, _arg3, _arg41, _arg5);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onWriteCharValue(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg41 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg41 = null;
                    }

                    this.onWriteCharDescrValue(_arg0, _arg1, _arg2, _arg3, _arg41);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onRegForNotifications(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onUnregisterNotifications(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    String _arg11 = data.readString();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    boolean _arg4 = 0 != data.readInt();
                    _arg5 = data.createByteArray();
                    this.onNotify(_arg0, _arg11, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onGetFirstIncludedService(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg2 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg3 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }

                    this.onGetNextIncludedService(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IBleCharacteristicDataCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.broadcom.bt.le.api.IBleCharacteristicDataCallback";
            }

            public void onGetFirstCharacteristic(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onGetFirstCharacteristicDescriptor(int connID, int status, BluetoothGattID svcId, BluetoothGattID characteristicID, BluetoothGattID descriptorID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcId != null) {
                        _data.writeInt(1);
                        svcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(descriptorID != null) {
                        _data.writeInt(1);
                        descriptorID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onGetNextCharacteristic(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onGetNextCharacteristicDescriptor(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID, BluetoothGattID descriptorID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(descriptorID != null) {
                        _data.writeInt(1);
                        descriptorID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onReadCharacteristicValue(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID, byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeByteArray(data);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onReadCharDescriptorValue(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID, BluetoothGattID descrID, byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(descrID != null) {
                        _data.writeInt(1);
                        descrID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeByteArray(data);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onWriteCharValue(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onWriteCharDescrValue(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID, BluetoothGattID descrID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(descrID != null) {
                        _data.writeInt(1);
                        descrID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onRegForNotifications(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onUnregisterNotifications(int connID, int status, BluetoothGattID svcID, BluetoothGattID characteristicID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onNotify(int connID, String address, BluetoothGattID svcID, BluetoothGattID characteristicID, boolean isNotify, byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeString(address);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(characteristicID != null) {
                        _data.writeInt(1);
                        characteristicID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(isNotify?1:0);
                    _data.writeByteArray(data);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onGetFirstIncludedService(int connID, int status, BluetoothGattID svcID, BluetoothGattID inclsvcID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(inclsvcID != null) {
                        _data.writeInt(1);
                        inclsvcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onGetNextIncludedService(int connID, int status, BluetoothGattID svcID, BluetoothGattID inclsvcID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleCharacteristicDataCallback");
                    _data.writeInt(connID);
                    _data.writeInt(status);
                    if(svcID != null) {
                        _data.writeInt(1);
                        svcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(inclsvcID != null) {
                        _data.writeInt(1);
                        inclsvcID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }
}

