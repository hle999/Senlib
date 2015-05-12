package com.sen.lib.bluetooth.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.sen.lib.bluetooth.gatt.BluetoothGattID;

public interface IBleServiceCallback extends IInterface {
    void onServiceCreated(byte var1, int var2) throws RemoteException;

    void onServiceRegistered(byte var1, BluetoothGattID var2) throws RemoteException;

    void onIncludedServiceAdded(byte var1, int var2) throws RemoteException;

    void onCharacteristicAdded(byte var1, BluetoothGattID var2, int var3) throws RemoteException;

    void onCharacteristicDescrAdded(byte var1, BluetoothGattID var2, int var3) throws RemoteException;

    void onServiceDeleted(byte var1) throws RemoteException;

    void onServiceStarted(byte var1) throws RemoteException;

    void onServiceStopped(byte var1) throws RemoteException;

    void onHandleValueIndicationCompleted(byte var1, int var2) throws RemoteException;

    void onHandleValueNotificationCompleted(byte var1, int var2) throws RemoteException;

    void onResponseSendCompleted(byte var1, int var2) throws RemoteException;

    void onAttributeRequestRead(String var1, int var2, int var3, int var4, int var5, boolean var6) throws RemoteException;

    void onAttributeRequestWrite(String var1, int var2, int var3, int var4, boolean var5, int var6, boolean var7, int var8, byte[] var9) throws RemoteException;

    void onAttributeExecWrite(String var1, int var2, int var3, int var4) throws RemoteException;

    public abstract static class Stub extends Binder implements IBleServiceCallback {
        private static final String DESCRIPTOR = "com.broadcom.bt.le.api.IBleServiceCallback";
        static final int TRANSACTION_onServiceCreated = 1;
        static final int TRANSACTION_onServiceRegistered = 2;
        static final int TRANSACTION_onIncludedServiceAdded = 3;
        static final int TRANSACTION_onCharacteristicAdded = 4;
        static final int TRANSACTION_onCharacteristicDescrAdded = 5;
        static final int TRANSACTION_onServiceDeleted = 6;
        static final int TRANSACTION_onServiceStarted = 7;
        static final int TRANSACTION_onServiceStopped = 8;
        static final int TRANSACTION_onHandleValueIndicationCompleted = 9;
        static final int TRANSACTION_onHandleValueNotificationCompleted = 10;
        static final int TRANSACTION_onResponseSendCompleted = 11;
        static final int TRANSACTION_onAttributeRequestRead = 12;
        static final int TRANSACTION_onAttributeRequestWrite = 13;
        static final int TRANSACTION_onAttributeExecWrite = 14;

        public Stub() {
            this.attachInterface(this, "com.broadcom.bt.le.api.IBleServiceCallback");
        }

        public static IBleServiceCallback asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                return (IBleServiceCallback)(iin != null && iin instanceof IBleServiceCallback?(IBleServiceCallback)iin:new IBleServiceCallback.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String _arg0;
            int _arg1;
            int _arg2;
            int _arg3;
            byte _arg01;
            BluetoothGattID _arg11;
            switch(code) {
                case 1:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    _arg1 = data.readInt();
                    this.onServiceCreated(_arg01, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    if(0 != data.readInt()) {
                        _arg11 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg11 = null;
                    }

                    this.onServiceRegistered(_arg01, _arg11);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    _arg1 = data.readInt();
                    this.onIncludedServiceAdded(_arg01, _arg1);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    if(0 != data.readInt()) {
                        _arg11 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg11 = null;
                    }

                    _arg2 = data.readInt();
                    this.onCharacteristicAdded(_arg01, _arg11, _arg2);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    if(0 != data.readInt()) {
                        _arg11 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg11 = null;
                    }

                    _arg2 = data.readInt();
                    this.onCharacteristicDescrAdded(_arg01, _arg11, _arg2);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    this.onServiceDeleted(_arg01);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    this.onServiceStarted(_arg01);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    this.onServiceStopped(_arg01);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    _arg1 = data.readInt();
                    this.onHandleValueIndicationCompleted(_arg01, _arg1);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    _arg1 = data.readInt();
                    this.onHandleValueNotificationCompleted(_arg01, _arg1);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg01 = data.readByte();
                    _arg1 = data.readInt();
                    this.onResponseSendCompleted(_arg01, _arg1);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg0 = data.readString();
                    _arg1 = data.readInt();
                    _arg2 = data.readInt();
                    _arg3 = data.readInt();
                    int _arg41 = data.readInt();
                    boolean _arg51 = 0 != data.readInt();
                    this.onAttributeRequestRead(_arg0, _arg1, _arg2, _arg3, _arg41, _arg51);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg0 = data.readString();
                    _arg1 = data.readInt();
                    _arg2 = data.readInt();
                    _arg3 = data.readInt();
                    boolean _arg4 = 0 != data.readInt();
                    int _arg5 = data.readInt();
                    boolean _arg6 = 0 != data.readInt();
                    int _arg7 = data.readInt();
                    byte[] _arg8 = data.createByteArray();
                    this.onAttributeRequestWrite(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleServiceCallback");
                    _arg0 = data.readString();
                    _arg1 = data.readInt();
                    _arg2 = data.readInt();
                    _arg3 = data.readInt();
                    this.onAttributeExecWrite(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.broadcom.bt.le.api.IBleServiceCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IBleServiceCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.broadcom.bt.le.api.IBleServiceCallback";
            }

            public void onServiceCreated(byte status, int svcHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    _data.writeInt(svcHandle);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onServiceRegistered(byte status, BluetoothGattID svcId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    if(svcId != null) {
                        _data.writeInt(1);
                        svcId.writeToParcel(_data, 0);
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

            public void onIncludedServiceAdded(byte status, int incSvcHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    _data.writeInt(incSvcHandle);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onCharacteristicAdded(byte status, BluetoothGattID charId, int charHdl) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(charHdl);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onCharacteristicDescrAdded(byte status, BluetoothGattID charId, int chardescHdl) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(chardescHdl);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onServiceDeleted(byte status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onServiceStarted(byte status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onServiceStopped(byte status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onHandleValueIndicationCompleted(byte status, int attrHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    _data.writeInt(attrHandle);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onHandleValueNotificationCompleted(byte status, int attrHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    _data.writeInt(attrHandle);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onResponseSendCompleted(byte status, int attrHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeByte(status);
                    _data.writeInt(attrHandle);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onAttributeRequestRead(String address, int connId, int transId, int attrHandle, int offset, boolean isLong) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeString(address);
                    _data.writeInt(connId);
                    _data.writeInt(transId);
                    _data.writeInt(attrHandle);
                    _data.writeInt(offset);
                    _data.writeInt(isLong?1:0);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onAttributeRequestWrite(String address, int connId, int transId, int attrHandle, boolean isPrep, int len, boolean needRsp, int offset, byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeString(address);
                    _data.writeInt(connId);
                    _data.writeInt(transId);
                    _data.writeInt(attrHandle);
                    _data.writeInt(isPrep?1:0);
                    _data.writeInt(len);
                    _data.writeInt(needRsp?1:0);
                    _data.writeInt(offset);
                    _data.writeByteArray(data);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onAttributeExecWrite(String address, int connId, int transId, int execWrite) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleServiceCallback");
                    _data.writeString(address);
                    _data.writeInt(connId);
                    _data.writeInt(transId);
                    _data.writeInt(execWrite);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }
}

