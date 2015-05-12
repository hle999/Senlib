package com.sen.lib.bluetooth.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.sen.lib.bluetooth.gatt.BluetoothGattID;

public interface IBleClientCallback extends IInterface {
    void onAppRegistered(byte var1, byte var2) throws RemoteException;

    void onAppDeregistered(byte var1) throws RemoteException;

    void onConnected(String var1, int var2) throws RemoteException;

    void onDisconnected(int var1, String var2) throws RemoteException;

    void onSearchResult(int var1, BluetoothGattID var2) throws RemoteException;

    void onSearchCompleted(int var1, int var2) throws RemoteException;

    public abstract static class Stub extends Binder implements IBleClientCallback {
        private static final String DESCRIPTOR = "com.broadcom.bt.le.api.IBleClientCallback";
        static final int TRANSACTION_onAppRegistered = 1;
        static final int TRANSACTION_onAppDeregistered = 2;
        static final int TRANSACTION_onConnected = 3;
        static final int TRANSACTION_onDisconnected = 4;
        static final int TRANSACTION_onSearchResult = 5;
        static final int TRANSACTION_onSearchCompleted = 6;

        public Stub() {
            this.attachInterface(this, "com.broadcom.bt.le.api.IBleClientCallback");
        }

        public static IBleClientCallback asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.broadcom.bt.le.api.IBleClientCallback");
                return (IBleClientCallback)(iin != null && iin instanceof IBleClientCallback?(IBleClientCallback)iin:new IBleClientCallback.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            int _arg1;
            byte _arg02;
            switch(code) {
                case 1:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleClientCallback");
                    _arg02 = data.readByte();
                    byte _arg13 = data.readByte();
                    this.onAppRegistered(_arg02, _arg13);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleClientCallback");
                    _arg02 = data.readByte();
                    this.onAppDeregistered(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleClientCallback");
                    String _arg01 = data.readString();
                    _arg1 = data.readInt();
                    this.onConnected(_arg01, _arg1);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleClientCallback");
                    _arg0 = data.readInt();
                    String _arg12 = data.readString();
                    this.onDisconnected(_arg0, _arg12);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleClientCallback");
                    _arg0 = data.readInt();
                    BluetoothGattID _arg11;
                    if(0 != data.readInt()) {
                        _arg11 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg11 = null;
                    }

                    this.onSearchResult(_arg0, _arg11);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleClientCallback");
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    this.onSearchCompleted(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.broadcom.bt.le.api.IBleClientCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IBleClientCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.broadcom.bt.le.api.IBleClientCallback";
            }

            public void onAppRegistered(byte status, byte clientIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleClientCallback");
                    _data.writeByte(status);
                    _data.writeByte(clientIf);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onAppDeregistered(byte clientIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleClientCallback");
                    _data.writeByte(clientIf);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onConnected(String deviceAddress, int connId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleClientCallback");
                    _data.writeString(deviceAddress);
                    _data.writeInt(connId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onDisconnected(int connId, String deviceAddress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleClientCallback");
                    _data.writeInt(connId);
                    _data.writeString(deviceAddress);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onSearchResult(int connId, BluetoothGattID srvcId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleClientCallback");
                    _data.writeInt(connId);
                    if(srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onSearchCompleted(int connId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleClientCallback");
                    _data.writeInt(connId);
                    _data.writeInt(status);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }
}

