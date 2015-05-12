package com.sen.lib.bluetooth.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBleProfileEventCallback extends IInterface {
    void onClientConnected(int var1, String var2, boolean var3) throws RemoteException;

    void onAppRegisterCompleted(int var1, int var2) throws RemoteException;

    public abstract static class Stub extends Binder implements IBleProfileEventCallback {
        private static final String DESCRIPTOR = "com.broadcom.bt.le.api.IBleProfileEventCallback";
        static final int TRANSACTION_onClientConnected = 1;
        static final int TRANSACTION_onAppRegisterCompleted = 2;

        public Stub() {
            this.attachInterface(this, "com.broadcom.bt.le.api.IBleProfileEventCallback");
        }

        public static IBleProfileEventCallback asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.broadcom.bt.le.api.IBleProfileEventCallback");
                return (IBleProfileEventCallback)(iin != null && iin instanceof IBleProfileEventCallback?(IBleProfileEventCallback)iin:new IBleProfileEventCallback.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            switch(code) {
                case 1:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleProfileEventCallback");
                    _arg0 = data.readInt();
                    String _arg11 = data.readString();
                    boolean _arg2 = 0 != data.readInt();
                    this.onClientConnected(_arg0, _arg11, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.broadcom.bt.le.api.IBleProfileEventCallback");
                    _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    this.onAppRegisterCompleted(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.broadcom.bt.le.api.IBleProfileEventCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IBleProfileEventCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.broadcom.bt.le.api.IBleProfileEventCallback";
            }

            public void onClientConnected(int connId, String bdaddr, boolean fConnected) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleProfileEventCallback");
                    _data.writeInt(connId);
                    _data.writeString(bdaddr);
                    _data.writeInt(fConnected?1:0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onAppRegisterCompleted(int status, int serIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.le.api.IBleProfileEventCallback");
                    _data.writeInt(status);
                    _data.writeInt(serIf);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }
}

