package com.sen.lib.bluetooth.api;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.sen.lib.bluetooth.gatt.IBluetoothGatt;

public class BleAdapter {
    private static final String TAG = "BleAdapter";
    private static final int API_LEVEL = 5;
    private IBluetoothGatt mService;
    private BleAdapter.GattServiceConnection mSvcConn;
    private Context mContext;
    public static final String EXTRA_DEVICE_TYPE = "android.bluetooth.device.extra.DEVICE_TYPE";
    public static final byte DEVICE_TYPE_BREDR = 1;
    public static final byte DEVICE_TYPE_BLE = 2;
    public static final byte DEVICE_TYPE_DUMO = 3;
    public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";
    public static final String EXTRA_UUID = "android.bluetooth.device.extra.UUID";
    public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";

    public static int getApiLevel() {
        return 5;
    }

    public static byte getDeviceType(BluetoothDevice device) {
        if (device != null)
        /**
         * TODO: fixme!
         */
            // return device.getDeviceType();
            return BleAdapter.DEVICE_TYPE_BLE;
        return 0;
    }

    public static boolean getRemoteServices(String deviceAddress) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null)
            return false;
        /**
         * TODO: fixme
         */
        return true;
        // return adapter.getRemoteServices(deviceAddress);
    }

    public BleAdapter(Context context) {
        this.mContext = context;
        this.mSvcConn = new BleAdapter.GattServiceConnection();
    }

    public void init() {
        Log.d("BleAdapter", "init");
        Intent i = new Intent();
        i.setClassName("com.broadcom.bt.app.system", "com.broadcom.bt.app.system.GattService");
        this.mContext.bindService(i, this.mSvcConn, Context.BIND_AUTO_CREATE);
    }

    public synchronized void finish() {
        if(this.mSvcConn != null) {
            this.mContext.unbindService(this.mSvcConn);
            this.mSvcConn = null;
        }

    }

    public void finalize() {
        this.finish();
    }

    public void setScanParameters(int scanInterval, int scanWindow) {
        try {
            this.mService.setScanParameters(scanInterval, scanWindow);
        } catch (RemoteException var4) {
            Log.e("BleAdapter", var4.toString());
        }

    }

    void observeStart(int duration) {
        try {
            if(this.mService != null) {
                this.mService.observe(true, duration);
            }
        } catch (RemoteException var3) {
            Log.d("BleAdapter", "Error calling observe: " + var3.toString());
        }

    }

    void observeStop() {
        try {
            if(this.mService != null) {
                this.mService.observe(false, 0);
            }
        } catch (RemoteException var2) {
            Log.d("BleAdapter", "Error calling observe: " + var2.toString());
        }

    }

    void filterEnable(boolean enable) {
        try {
            if(this.mService != null) {
                this.mService.filterEnable(enable);
            }
        } catch (RemoteException var3) {
            Log.d("BleAdapter", "Error calling filterEnable: " + var3.toString());
        }

    }

    void filterEnableBDA(boolean enable, int addr_type, String address) {
        try {
            if(this.mService != null) {
                this.mService.filterEnableBDA(enable, addr_type, address);
            }
        } catch (RemoteException var5) {
            Log.d("BleAdapter", "Error calling filterEnableBDA: " + var5.toString());
        }

    }

    void filterManufacturerData(int company, byte[] data1, byte[] data2, byte[] data3, byte[] data4) {
        try {
            if(this.mService != null) {
                this.mService.filterManufacturerData(company, data1, data2, data3, data4);
            }
        } catch (RemoteException var7) {
            Log.d("BleAdapter", "Error calling filterManufacturerData: " + var7.toString());
        }

    }

    void filterManufacturerDataBDA(int company, byte[] data1, byte[] data2, byte[] data3, byte[] data4, boolean has_bda, int addr_type, String address) {
        try {
            if(this.mService != null) {
                this.mService.filterManufacturerDataBDA(company, data1, data2, data3, data4, has_bda, addr_type, address);
            }
        } catch (RemoteException var10) {
            Log.d("BleAdapter", "Error calling filterManufacturerDataBDA: " + var10.toString());
        }

    }

    void clearManufacturerData() {
        try {
            if(this.mService != null) {
                this.mService.clearManufacturerData();
            }
        } catch (RemoteException var2) {
            Log.d("BleAdapter", "Error calling observe: " + var2.toString());
        }

    }

    public void onInitialized(boolean success) {
    }

    private class GattServiceConnection implements ServiceConnection {
        private GattServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            if(service != null) {
                try {
                    BleAdapter.this.mService = IBluetoothGatt.Stub.asInterface(service);
                    BleAdapter.this.onInitialized(true);
                } catch (Throwable var4) {
                    Log.e("BleAdapter", "Unable to get Binder to GattService", var4);
                    BleAdapter.this.onInitialized(false);
                }
            }

        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d("BleAdapter", "Disconnected from GattService!");
        }
    }
}

