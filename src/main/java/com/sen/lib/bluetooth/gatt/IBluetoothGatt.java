package com.sen.lib.bluetooth.gatt;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.sen.lib.bluetooth.api.IBleCharacteristicDataCallback;
import com.sen.lib.bluetooth.api.IBleClientCallback;
import com.sen.lib.bluetooth.api.IBleProfileEventCallback;
import com.sen.lib.bluetooth.api.IBleServiceCallback;

public interface IBluetoothGatt extends IInterface {
    void registerApp(BluetoothGattID var1, IBleClientCallback var2) throws RemoteException;

    void unregisterApp(byte var1) throws RemoteException;

    void setEncryption(String var1, byte var2) throws RemoteException;

    void setScanParameters(int var1, int var2) throws RemoteException;

    void filterEnable(boolean var1) throws RemoteException;

    void filterEnableBDA(boolean var1, int var2, String var3) throws RemoteException;

    void clearManufacturerData() throws RemoteException;

    void filterManufacturerData(int var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5) throws RemoteException;

    void filterManufacturerDataBDA(int var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, boolean var6, int var7, String var8) throws RemoteException;

    void observe(boolean var1, int var2) throws RemoteException;

    void open(byte var1, String var2, boolean var3) throws RemoteException;

    void close(byte var1, String var2, int var3, boolean var4) throws RemoteException;

    void registerServiceDataCallback(int var1, BluetoothGattID var2, String var3, IBleCharacteristicDataCallback var4) throws RemoteException;

    void searchService(int var1, BluetoothGattID var2) throws RemoteException;

    void getFirstChar(int var1, BluetoothGattID var2, BluetoothGattID var3) throws RemoteException;

    void getNextChar(int var1, BluetoothGattCharID var2, BluetoothGattID var3) throws RemoteException;

    void getFirstCharDescr(int var1, BluetoothGattCharID var2, BluetoothGattID var3) throws RemoteException;

    void getNextCharDescr(int var1, BluetoothGattCharDescrID var2, BluetoothGattID var3) throws RemoteException;

    void getFirstIncludedService(int var1, BluetoothGattID var2, BluetoothGattID var3) throws RemoteException;

    void getNextIncludedService(int var1, BluetoothGattInclSrvcID var2, BluetoothGattID var3) throws RemoteException;

    void readChar(int var1, BluetoothGattCharID var2, byte var3) throws RemoteException;

    void readCharDescr(int var1, BluetoothGattCharDescrID var2, byte var3) throws RemoteException;

    void writeCharValue(int var1, BluetoothGattCharID var2, int var3, byte var4, byte[] var5) throws RemoteException;

    void writeCharDescrValue(int var1, BluetoothGattCharDescrID var2, int var3, byte var4, byte[] var5) throws RemoteException;

    void sendIndConfirm(int var1, BluetoothGattCharID var2) throws RemoteException;

    void prepareWrite(int var1, BluetoothGattCharID var2, int var3, int var4, byte[] var5) throws RemoteException;

    void executeWrite(int var1, boolean var2) throws RemoteException;

    void registerForNotifications(byte var1, String var2, BluetoothGattCharID var3) throws RemoteException;

    void deregisterForNotifications(byte var1, String var2, BluetoothGattCharID var3) throws RemoteException;

    void registerServerServiceCallback(BluetoothGattID var1, BluetoothGattID var2, IBleServiceCallback var3) throws RemoteException;

    void registerServerProfileCallback(BluetoothGattID var1, IBleProfileEventCallback var2) throws RemoteException;

    void unregisterServerServiceCallback(int var1) throws RemoteException;

    void unregisterServerProfileCallback(int var1) throws RemoteException;

    void GATTServer_CreateService(byte var1, BluetoothGattID var2, int var3) throws RemoteException;

    void GATTServer_AddIncludedService(int var1, int var2) throws RemoteException;

    void GATTServer_AddCharacteristic(int var1, BluetoothGattID var2, int var3, int var4, boolean var5, int var6) throws RemoteException;

    void GATTServer_AddCharDescriptor(int var1, int var2, BluetoothGattID var3) throws RemoteException;

    void GATTServer_DeleteService(int var1) throws RemoteException;

    void GATTServer_StartService(int var1, byte var2) throws RemoteException;

    void GATTServer_StopService(int var1) throws RemoteException;

    void GATTServer_HandleValueIndication(int var1, int var2, byte[] var3) throws RemoteException;

    void GATTServer_HandleValueNotification(int var1, int var2, byte[] var3) throws RemoteException;

    void GATTServer_SendRsp(int var1, int var2, byte var3, int var4, int var5, byte[] var6, byte var7, boolean var8) throws RemoteException;

    void GATTServer_Open(byte var1, String var2, boolean var3) throws RemoteException;

    void GATTServer_CancelOpen(byte var1, String var2, boolean var3) throws RemoteException;

    void GATTServer_Close(int var1) throws RemoteException;

    public abstract static class Stub extends Binder implements IBluetoothGatt {
        private static final String DESCRIPTOR = "com.broadcom.bt.service.gatt.IBluetoothGatt";
        static final int TRANSACTION_registerApp = 1;
        static final int TRANSACTION_unregisterApp = 2;
        static final int TRANSACTION_setEncryption = 3;
        static final int TRANSACTION_setScanParameters = 4;
        static final int TRANSACTION_filterEnable = 5;
        static final int TRANSACTION_filterEnableBDA = 6;
        static final int TRANSACTION_clearManufacturerData = 7;
        static final int TRANSACTION_filterManufacturerData = 8;
        static final int TRANSACTION_filterManufacturerDataBDA = 9;
        static final int TRANSACTION_observe = 10;
        static final int TRANSACTION_open = 11;
        static final int TRANSACTION_close = 12;
        static final int TRANSACTION_registerServiceDataCallback = 13;
        static final int TRANSACTION_searchService = 14;
        static final int TRANSACTION_getFirstChar = 15;
        static final int TRANSACTION_getNextChar = 16;
        static final int TRANSACTION_getFirstCharDescr = 17;
        static final int TRANSACTION_getNextCharDescr = 18;
        static final int TRANSACTION_getFirstIncludedService = 19;
        static final int TRANSACTION_getNextIncludedService = 20;
        static final int TRANSACTION_readChar = 21;
        static final int TRANSACTION_readCharDescr = 22;
        static final int TRANSACTION_writeCharValue = 23;
        static final int TRANSACTION_writeCharDescrValue = 24;
        static final int TRANSACTION_sendIndConfirm = 25;
        static final int TRANSACTION_prepareWrite = 26;
        static final int TRANSACTION_executeWrite = 27;
        static final int TRANSACTION_registerForNotifications = 28;
        static final int TRANSACTION_deregisterForNotifications = 29;
        static final int TRANSACTION_registerServerServiceCallback = 30;
        static final int TRANSACTION_registerServerProfileCallback = 31;
        static final int TRANSACTION_unregisterServerServiceCallback = 32;
        static final int TRANSACTION_unregisterServerProfileCallback = 33;
        static final int TRANSACTION_GATTServer_CreateService = 34;
        static final int TRANSACTION_GATTServer_AddIncludedService = 35;
        static final int TRANSACTION_GATTServer_AddCharacteristic = 36;
        static final int TRANSACTION_GATTServer_AddCharDescriptor = 37;
        static final int TRANSACTION_GATTServer_DeleteService = 38;
        static final int TRANSACTION_GATTServer_StartService = 39;
        static final int TRANSACTION_GATTServer_StopService = 40;
        static final int TRANSACTION_GATTServer_HandleValueIndication = 41;
        static final int TRANSACTION_GATTServer_HandleValueNotification = 42;
        static final int TRANSACTION_GATTServer_SendRsp = 43;
        static final int TRANSACTION_GATTServer_Open = 44;
        static final int TRANSACTION_GATTServer_CancelOpen = 45;
        static final int TRANSACTION_GATTServer_Close = 46;

        public Stub() {
            this.attachInterface(this, "com.broadcom.bt.service.gatt.IBluetoothGatt");
        }

        public static IBluetoothGatt asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                return (IBluetoothGatt)(iin != null && iin instanceof IBluetoothGatt?(IBluetoothGatt)iin:new IBluetoothGatt.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            String _arg1;
            boolean _arg2;
            int _arg3;
            byte _arg01;
            int _arg11;
            byte[] _arg21;
            byte _arg12;
            byte _arg22;
            BluetoothGattID _arg13;
            BluetoothGattID _arg23;
            byte _arg31;
            BluetoothGattID _arg02;
            byte[] _arg42;
            int _arg24;
            byte[] _arg34;
            BluetoothGattCharID _arg26;
            boolean _arg03;
            BluetoothGattCharID _arg16;
            BluetoothGattCharDescrID _arg17;
            byte[] _arg19;
            String _arg27;
            switch(code) {
                case 1:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    if(0 != data.readInt()) {
                        _arg02 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }

                    IBleClientCallback _arg110 = com.sen.lib.bluetooth.api.IBleClientCallback.Stub.asInterface(data.readStrongBinder());
                    this.registerApp(_arg02, _arg110);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    this.unregisterApp(_arg01);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    String _arg04 = data.readString();
                    _arg12 = data.readByte();
                    this.setEncryption(_arg04, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg11 = data.readInt();
                    this.setScanParameters(_arg0, _arg11);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg03 = 0 != data.readInt();
                    this.filterEnable(_arg03);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg03 = 0 != data.readInt();
                    _arg11 = data.readInt();
                    _arg27 = data.readString();
                    this.filterEnableBDA(_arg03, _arg11, _arg27);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    this.clearManufacturerData();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg19 = data.createByteArray();
                    _arg21 = data.createByteArray();
                    _arg34 = data.createByteArray();
                    _arg42 = data.createByteArray();
                    this.filterManufacturerData(_arg0, _arg19, _arg21, _arg34, _arg42);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg19 = data.createByteArray();
                    _arg21 = data.createByteArray();
                    _arg34 = data.createByteArray();
                    _arg42 = data.createByteArray();
                    boolean _arg52 = 0 != data.readInt();
                    int _arg61 = data.readInt();
                    String _arg71 = data.readString();
                    this.filterManufacturerDataBDA(_arg0, _arg19, _arg21, _arg34, _arg42, _arg52, _arg61, _arg71);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg03 = 0 != data.readInt();
                    _arg11 = data.readInt();
                    this.observe(_arg03, _arg11);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    _arg1 = data.readString();
                    _arg2 = 0 != data.readInt();
                    this.open(_arg01, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    _arg1 = data.readString();
                    _arg24 = data.readInt();
                    boolean _arg32 = 0 != data.readInt();
                    this.close(_arg01, _arg1, _arg24, _arg32);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    _arg27 = data.readString();
                    IBleCharacteristicDataCallback _arg33 = com.sen.lib.bluetooth.api.IBleCharacteristicDataCallback.Stub.asInterface(data.readStrongBinder());
                    this.registerServiceDataCallback(_arg0, _arg13, _arg27, _arg33);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    this.searchService(_arg0, _arg13);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.getFirstChar(_arg0, _arg13, _arg23);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg16 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.getNextChar(_arg0, _arg16, _arg23);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg16 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.getFirstCharDescr(_arg0, _arg16, _arg23);
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg17 = (BluetoothGattCharDescrID)BluetoothGattCharDescrID.CREATOR.createFromParcel(data);
                    } else {
                        _arg17 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.getNextCharDescr(_arg0, _arg17, _arg23);
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.getFirstIncludedService(_arg0, _arg13, _arg23);
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    BluetoothGattInclSrvcID _arg18;
                    if(0 != data.readInt()) {
                        _arg18 = (BluetoothGattInclSrvcID)BluetoothGattInclSrvcID.CREATOR.createFromParcel(data);
                    } else {
                        _arg18 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.getNextIncludedService(_arg0, _arg18, _arg23);
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg16 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }

                    _arg22 = data.readByte();
                    this.readChar(_arg0, _arg16, _arg22);
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg17 = (BluetoothGattCharDescrID)BluetoothGattCharDescrID.CREATOR.createFromParcel(data);
                    } else {
                        _arg17 = null;
                    }

                    _arg22 = data.readByte();
                    this.readCharDescr(_arg0, _arg17, _arg22);
                    reply.writeNoException();
                    return true;
                case 23:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg16 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }

                    _arg24 = data.readInt();
                    _arg31 = data.readByte();
                    _arg42 = data.createByteArray();
                    this.writeCharValue(_arg0, _arg16, _arg24, _arg31, _arg42);
                    reply.writeNoException();
                    return true;
                case 24:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg17 = (BluetoothGattCharDescrID)BluetoothGattCharDescrID.CREATOR.createFromParcel(data);
                    } else {
                        _arg17 = null;
                    }

                    _arg24 = data.readInt();
                    _arg31 = data.readByte();
                    _arg42 = data.createByteArray();
                    this.writeCharDescrValue(_arg0, _arg17, _arg24, _arg31, _arg42);
                    reply.writeNoException();
                    return true;
                case 25:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg16 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }

                    this.sendIndConfirm(_arg0, _arg16);
                    reply.writeNoException();
                    return true;
                case 26:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg16 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }

                    _arg24 = data.readInt();
                    _arg3 = data.readInt();
                    _arg42 = data.createByteArray();
                    this.prepareWrite(_arg0, _arg16, _arg24, _arg3, _arg42);
                    reply.writeNoException();
                    return true;
                case 27:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    boolean _arg15 = 0 != data.readInt();
                    this.executeWrite(_arg0, _arg15);
                    reply.writeNoException();
                    return true;
                case 28:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    _arg1 = data.readString();
                    if(0 != data.readInt()) {
                        _arg26 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg26 = null;
                    }

                    this.registerForNotifications(_arg01, _arg1, _arg26);
                    reply.writeNoException();
                    return true;
                case 29:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    _arg1 = data.readString();
                    if(0 != data.readInt()) {
                        _arg26 = (BluetoothGattCharID)BluetoothGattCharID.CREATOR.createFromParcel(data);
                    } else {
                        _arg26 = null;
                    }

                    this.deregisterForNotifications(_arg01, _arg1, _arg26);
                    reply.writeNoException();
                    return true;
                case 30:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    if(0 != data.readInt()) {
                        _arg02 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }

                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    IBleServiceCallback _arg25 = com.sen.lib.bluetooth.api.IBleServiceCallback.Stub.asInterface(data.readStrongBinder());
                    this.registerServerServiceCallback(_arg02, _arg13, _arg25);
                    reply.writeNoException();
                    return true;
                case 31:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    if(0 != data.readInt()) {
                        _arg02 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }

                    IBleProfileEventCallback _arg14 = com.sen.lib.bluetooth.api.IBleProfileEventCallback.Stub.asInterface(data.readStrongBinder());
                    this.registerServerProfileCallback(_arg02, _arg14);
                    reply.writeNoException();
                    return true;
                case 32:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    this.unregisterServerServiceCallback(_arg0);
                    reply.writeNoException();
                    return true;
                case 33:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    this.unregisterServerProfileCallback(_arg0);
                    reply.writeNoException();
                    return true;
                case 34:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    _arg24 = data.readInt();
                    this.GATTServer_CreateService(_arg01, _arg13, _arg24);
                    reply.writeNoException();
                    return true;
                case 35:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg11 = data.readInt();
                    this.GATTServer_AddIncludedService(_arg0, _arg11);
                    reply.writeNoException();
                    return true;
                case 36:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg13 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }

                    _arg24 = data.readInt();
                    _arg3 = data.readInt();
                    boolean _arg41 = 0 != data.readInt();
                    int _arg51 = data.readInt();
                    this.GATTServer_AddCharacteristic(_arg0, _arg13, _arg24, _arg3, _arg41, _arg51);
                    reply.writeNoException();
                    return true;
                case 37:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg11 = data.readInt();
                    if(0 != data.readInt()) {
                        _arg23 = (BluetoothGattID)BluetoothGattID.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }

                    this.GATTServer_AddCharDescriptor(_arg0, _arg11, _arg23);
                    reply.writeNoException();
                    return true;
                case 38:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    this.GATTServer_DeleteService(_arg0);
                    reply.writeNoException();
                    return true;
                case 39:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg12 = data.readByte();
                    this.GATTServer_StartService(_arg0, _arg12);
                    reply.writeNoException();
                    return true;
                case 40:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    this.GATTServer_StopService(_arg0);
                    reply.writeNoException();
                    return true;
                case 41:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg11 = data.readInt();
                    _arg21 = data.createByteArray();
                    this.GATTServer_HandleValueIndication(_arg0, _arg11, _arg21);
                    reply.writeNoException();
                    return true;
                case 42:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg11 = data.readInt();
                    _arg21 = data.createByteArray();
                    this.GATTServer_HandleValueNotification(_arg0, _arg11, _arg21);
                    reply.writeNoException();
                    return true;
                case 43:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    _arg11 = data.readInt();
                    _arg22 = data.readByte();
                    _arg3 = data.readInt();
                    int _arg4 = data.readInt();
                    byte[] _arg5 = data.createByteArray();
                    byte _arg6 = data.readByte();
                    boolean _arg7 = 0 != data.readInt();
                    this.GATTServer_SendRsp(_arg0, _arg11, _arg22, _arg3, _arg4, _arg5, _arg6, _arg7);
                    reply.writeNoException();
                    return true;
                case 44:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    _arg1 = data.readString();
                    _arg2 = 0 != data.readInt();
                    this.GATTServer_Open(_arg01, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 45:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg01 = data.readByte();
                    _arg1 = data.readString();
                    _arg2 = 0 != data.readInt();
                    this.GATTServer_CancelOpen(_arg01, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 46:
                    data.enforceInterface("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _arg0 = data.readInt();
                    this.GATTServer_Close(_arg0);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IBluetoothGatt {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.broadcom.bt.service.gatt.IBluetoothGatt";
            }

            public void registerApp(BluetoothGattID appID, IBleClientCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    if(appID != null) {
                        _data.writeInt(1);
                        appID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeStrongBinder(callback != null?callback.asBinder():null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void unregisterApp(byte clientIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(clientIf);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void setEncryption(String address, byte action) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeString(address);
                    _data.writeByte(action);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void setScanParameters(int scanInterval, int scanWindow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(scanInterval);
                    _data.writeInt(scanWindow);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void filterEnable(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(enable?1:0);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void filterEnableBDA(boolean enable, int addr_type, String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(enable?1:0);
                    _data.writeInt(addr_type);
                    _data.writeString(address);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void clearManufacturerData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void filterManufacturerData(int company, byte[] data1, byte[] data2, byte[] data3, byte[] data4) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(company);
                    _data.writeByteArray(data1);
                    _data.writeByteArray(data2);
                    _data.writeByteArray(data3);
                    _data.writeByteArray(data4);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void filterManufacturerDataBDA(int company, byte[] data1, byte[] data2, byte[] data3, byte[] data4, boolean has_bda, int addr_type, String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(company);
                    _data.writeByteArray(data1);
                    _data.writeByteArray(data2);
                    _data.writeByteArray(data3);
                    _data.writeByteArray(data4);
                    _data.writeInt(has_bda?1:0);
                    _data.writeInt(addr_type);
                    _data.writeString(address);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void observe(boolean start, int duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(start?1:0);
                    _data.writeInt(duration);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void open(byte clientIf, String address, boolean isDirect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(clientIf);
                    _data.writeString(address);
                    _data.writeInt(isDirect?1:0);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void close(byte clientIf, String address, int connId, boolean isDirect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(clientIf);
                    _data.writeString(address);
                    _data.writeInt(connId);
                    _data.writeInt(isDirect?1:0);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void registerServiceDataCallback(int connId, BluetoothGattID svcUuid, String bdaddr, IBleCharacteristicDataCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(svcUuid != null) {
                        _data.writeInt(1);
                        svcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeString(bdaddr);
                    _data.writeStrongBinder(callback != null?callback.asBinder():null);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void searchService(int connId, BluetoothGattID uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void getFirstChar(int connId, BluetoothGattID serviceId, BluetoothGattID condCharUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(serviceId != null) {
                        _data.writeInt(1);
                        serviceId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(condCharUuid != null) {
                        _data.writeInt(1);
                        condCharUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void getNextChar(int connId, BluetoothGattCharID startCharId, BluetoothGattID condCharUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(startCharId != null) {
                        _data.writeInt(1);
                        startCharId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(condCharUuid != null) {
                        _data.writeInt(1);
                        condCharUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void getFirstCharDescr(int connId, BluetoothGattCharID charId, BluetoothGattID condDescrUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(condDescrUuid != null) {
                        _data.writeInt(1);
                        condDescrUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void getNextCharDescr(int connId, BluetoothGattCharDescrID descrId, BluetoothGattID condDescrUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(descrId != null) {
                        _data.writeInt(1);
                        descrId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(condDescrUuid != null) {
                        _data.writeInt(1);
                        condDescrUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void getFirstIncludedService(int connId, BluetoothGattID srvcId, BluetoothGattID condSrvcUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(condSrvcUuid != null) {
                        _data.writeInt(1);
                        condSrvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void getNextIncludedService(int connId, BluetoothGattInclSrvcID startSrvcId, BluetoothGattID condSrvcUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(startSrvcId != null) {
                        _data.writeInt(1);
                        startSrvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(condSrvcUuid != null) {
                        _data.writeInt(1);
                        condSrvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void readChar(int connId, BluetoothGattCharID charId, byte authReq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeByte(authReq);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void readCharDescr(int connId, BluetoothGattCharDescrID charDescr, byte authReq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charDescr != null) {
                        _data.writeInt(1);
                        charDescr.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeByte(authReq);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void writeCharValue(int connId, BluetoothGattCharID charId, int writeType, byte authReq, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(writeType);
                    _data.writeByte(authReq);
                    _data.writeByteArray(value);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void writeCharDescrValue(int connId, BluetoothGattCharDescrID charDescr, int writeType, byte authReq, byte[] charDescrVal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charDescr != null) {
                        _data.writeInt(1);
                        charDescr.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(writeType);
                    _data.writeByte(authReq);
                    _data.writeByteArray(charDescrVal);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void sendIndConfirm(int connId, BluetoothGattCharID charId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void prepareWrite(int connId, BluetoothGattCharID charId, int offset, int len, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(offset);
                    _data.writeInt(len);
                    _data.writeByteArray(value);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void executeWrite(int connId, boolean isExecute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    _data.writeInt(isExecute?1:0);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void registerForNotifications(byte clientIf, String address, BluetoothGattCharID charID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(clientIf);
                    _data.writeString(address);
                    if(charID != null) {
                        _data.writeInt(1);
                        charID.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void deregisterForNotifications(byte clientIf, String address, BluetoothGattCharID charId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(clientIf);
                    _data.writeString(address);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void registerServerServiceCallback(BluetoothGattID serviceUuid, BluetoothGattID appUuid, IBleServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    if(serviceUuid != null) {
                        _data.writeInt(1);
                        serviceUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    if(appUuid != null) {
                        _data.writeInt(1);
                        appUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeStrongBinder(callback != null?callback.asBinder():null);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void registerServerProfileCallback(BluetoothGattID appUuid, IBleProfileEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    if(appUuid != null) {
                        _data.writeInt(1);
                        appUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeStrongBinder(callback != null?callback.asBinder():null);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void unregisterServerServiceCallback(int svcHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void unregisterServerProfileCallback(int serIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(serIf);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_CreateService(byte serIf, BluetoothGattID serviceId, int numHandles) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(serIf);
                    if(serviceId != null) {
                        _data.writeInt(1);
                        serviceId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(numHandles);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_AddIncludedService(int svcHandle, int includedSvcHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    _data.writeInt(includedSvcHandle);
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_AddCharacteristic(int svcHandle, BluetoothGattID charId, int permissions, int charProperty, boolean dirtyFlag, int dirtDescNum) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    if(charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeInt(permissions);
                    _data.writeInt(charProperty);
                    _data.writeInt(dirtyFlag?1:0);
                    _data.writeInt(dirtDescNum);
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_AddCharDescriptor(int svcHandle, int permissions, BluetoothGattID descrUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    _data.writeInt(permissions);
                    if(descrUuid != null) {
                        _data.writeInt(1);
                        descrUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_DeleteService(int svcHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_StartService(int svcHandle, byte supTransport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    _data.writeByte(supTransport);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_StopService(int svcHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(svcHandle);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_HandleValueIndication(int connId, int attrHandle, byte[] val) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    _data.writeInt(attrHandle);
                    _data.writeByteArray(val);
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_HandleValueNotification(int connId, int attrHandle, byte[] val) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    _data.writeInt(attrHandle);
                    _data.writeByteArray(val);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_SendRsp(int conn_id, int transId, byte responseStatus, int handle, int offset, byte[] val, byte authReq, boolean write_req) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(conn_id);
                    _data.writeInt(transId);
                    _data.writeByte(responseStatus);
                    _data.writeInt(handle);
                    _data.writeInt(offset);
                    _data.writeByteArray(val);
                    _data.writeByte(authReq);
                    _data.writeInt(write_req?1:0);
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_Open(byte serIf, String bdaddr, boolean isDirect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(serIf);
                    _data.writeString(bdaddr);
                    _data.writeInt(isDirect?1:0);
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_CancelOpen(byte serIf, String bdaddr, boolean isDirect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeByte(serIf);
                    _data.writeString(bdaddr);
                    _data.writeInt(isDirect?1:0);
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void GATTServer_Close(int connId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.broadcom.bt.service.gatt.IBluetoothGatt");
                    _data.writeInt(connId);
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }
}

