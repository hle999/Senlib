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

import com.sen.lib.bluetooth.gatt.BluetoothGattID;
import com.sen.lib.bluetooth.gatt.IBluetoothGatt;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BleClientProfile {
    private static final boolean D = true;
    private static final String TAG = "BleClientProfile";
    private ArrayList<BleClientService> mRequiredServices;
    private ArrayList<BleClientService> mOptionalServices;
    private ArrayList<BluetoothDevice> mConnectedDevices;
    private ArrayList<BluetoothDevice> mConnectingDevices;
    private ArrayList<BluetoothDevice> mDisconnectingDevices;
    private BleGattID mAppUuid;
    private byte mClientIf = 0;
    private IBluetoothGatt mService;
    private BleClientProfile.BleClientCallback mCallback;
    private HashMap<Integer, BluetoothDevice> mClientIDToDeviceMap;
    private HashMap<BluetoothDevice, Integer> mDeviceToClientIDMap;
    private ArrayList<BleGattID> mPeerServices = new ArrayList();
    private BleClientProfile.GattServiceConnection mSvcConn;
    private Context mContext;

    public BleClientProfile(Context context, BleGattID profileUuid) {
        Log.d("BleClientProfile", "BleClientProfile " + profileUuid.toString());
        this.mContext = context;
        this.mAppUuid = profileUuid;
        this.mConnectedDevices = new ArrayList();
        this.mConnectingDevices = new ArrayList();
        this.mDisconnectingDevices = new ArrayList();
        this.mClientIDToDeviceMap = new HashMap();
        this.mDeviceToClientIDMap = new HashMap();
        this.mCallback = new BleClientProfile.BleClientCallback();
        this.mSvcConn = new BleClientProfile.GattServiceConnection();
    }

    public void init(ArrayList<BleClientService> requiredServices, ArrayList<BleClientService> optionalServices) {
        Log.d("BleClientProfile", "init (" + this.mAppUuid + ")");
        this.mRequiredServices = requiredServices;
        this.mOptionalServices = optionalServices;
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

    public boolean isProfileRegistered() {
        Log.d("BleClientProfile", "isProfileRegistered (" + this.mAppUuid + ")");
        return this.mClientIf != 0;
    }

    public int registerProfile() {
        byte ret = 0;
        Log.d("BleClientProfile", "registerProfile (" + this.mAppUuid + ")");
        if(this.mClientIf == 0) {
            try {
                this.mService.registerApp(this.mAppUuid, this.mCallback);
            } catch (RemoteException var3) {
                Log.e("BleClientProfile", var3.toString());
                ret = 1;
            }
        }

        return ret;
    }

    public void deregisterProfile() {
        Log.d("BleClientProfile", "deregisterProfile (" + this.mAppUuid + ")");
        if(this.mClientIf != 0) {
            try {
                this.mService.unregisterApp(this.mClientIf);
            } catch (RemoteException var2) {
                Log.e("BleClientProfile", "deregisterProfile() - " + var2.toString());
            }
        }

    }

    public void setEncryption(BluetoothDevice device, byte action) {
        try {
            this.mService.setEncryption(device.getAddress(), action);
        } catch (RemoteException var4) {
            Log.e("BleClientProfile", var4.toString());
        }

    }

    public void setScanParameters(int scanInterval, int scanWindow) {
        try {
            this.mService.setScanParameters(scanInterval, scanWindow);
        } catch (RemoteException var4) {
            Log.e("BleClientProfile", var4.toString());
        }

    }

    public int connect(BluetoothDevice device) {
        Log.d("BleClientProfile", "connect (" + this.mAppUuid + ")" + device.getAddress());
        byte ret = 0;
        ArrayList e = this.mConnectingDevices;
        synchronized(this.mConnectingDevices) {
            this.mConnectingDevices.add(device);
        }

        e = this.mDisconnectingDevices;
        synchronized(this.mDisconnectingDevices) {
            this.mDisconnectingDevices.remove(device);
        }

        try {
            this.mService.open(this.mClientIf, device.getAddress(), true);
        } catch (RemoteException var6) {
            Log.e("BleClientProfile", var6.toString());
            ret = 1;
        }

        return ret;
    }

    public int connectBackground(BluetoothDevice device) {
        Log.d("BleClientProfile", "connectBackground (" + this.mAppUuid + ")" + device.getAddress());
        byte ret = 0;
        ArrayList e = this.mConnectingDevices;
        synchronized(this.mConnectingDevices) {
            this.mConnectingDevices.add(device);
        }

        e = this.mDisconnectingDevices;
        synchronized(this.mDisconnectingDevices) {
            this.mDisconnectingDevices.remove(device);
        }

        try {
            this.mService.open(this.mClientIf, device.getAddress(), false);
        } catch (RemoteException var6) {
            Log.e("BleClientProfile", var6.toString());
            ret = 1;
        }

        return ret;
    }

    public int cancelBackgroundConnection(BluetoothDevice device) {
        Log.d("BleClientProfile", "cancelBackgroundConnection (" + this.mAppUuid + ") - device " + device.getAddress());
        byte ret = 0;

        try {
            this.mService.close(this.mClientIf, device.getAddress(), 0, false);
        } catch (RemoteException var4) {
            Log.e("BleClientProfile", var4.toString());
            ret = 1;
        }

        return ret;
    }

    public int disconnect(BluetoothDevice device) {
        Log.d("BleClientProfile", "disconnect (" + this.mAppUuid + ") - device " + device.getAddress());
        ArrayList ret = this.mDisconnectingDevices;
        synchronized(this.mDisconnectingDevices) {
            this.mDisconnectingDevices.add(device);
        }

        byte ret1 = 0;

        try {
            this.mService.close(this.mClientIf, device.getAddress(), ((Integer)this.mDeviceToClientIDMap.get(device)).intValue(), true);
        } catch (RemoteException var4) {
            Log.e("BleClientProfile", var4.toString());
            ret1 = 1;
        }

        return ret1;
    }

    public int refresh(BluetoothDevice device) {
        Log.d("BleClientProfile", "refresh (" + this.mAppUuid + ") - address = " + device.getAddress());
        if(this.isDeviceDisconnecting(device)) {
            Log.d("BleClientProfile", "refresh (" + this.mAppUuid + ") - Device unavailable!");
            return 1;
        } else {
            ((BleClientService)this.mRequiredServices.get(0)).refresh(device);
            return 0;
        }
    }

    public int refreshService(BluetoothDevice device, BleClientService service) {
        Log.d("BleClientProfile", "refreshService (" + this.mAppUuid + ") address = s " + device.getAddress() + "service = " + service.getServiceId());
        return 0;
    }

    public BluetoothDevice[] getConnectedDevices() {
        return (BluetoothDevice[])this.mConnectedDevices.toArray(new BluetoothDevice[0]);
    }

    public BluetoothDevice findConnectedDevice(String address) {
        BluetoothDevice ret = null;
        ArrayList var3 = this.mConnectedDevices;
        synchronized(this.mConnectedDevices) {
            for(int i = 0; i != this.mConnectedDevices.size(); ++i) {
                BluetoothDevice d = (BluetoothDevice)this.mConnectedDevices.get(i);
                if(address.equalsIgnoreCase(d.getAddress())) {
                    ret = d;
                    break;
                }
            }

            return ret;
        }
    }

    public BluetoothDevice[] getPendingConnections() {
        return (BluetoothDevice[])this.mConnectingDevices.toArray(new BluetoothDevice[0]);
    }

    public BluetoothDevice findDeviceWaitingForConnection(String address) {
        BluetoothDevice ret = null;
        ArrayList var3 = this.mConnectingDevices;
        synchronized(this.mConnectingDevices) {
            for(int i = 0; i < this.mConnectingDevices.size(); ++i) {
                BluetoothDevice d = (BluetoothDevice)this.mConnectingDevices.get(i);
                if(address.equalsIgnoreCase(d.getAddress())) {
                    ret = d;
                    break;
                }
            }

            return ret;
        }
    }

    IBluetoothGatt getGattService() {
        return this.mService;
    }

    int getConnIdForDevice(BluetoothDevice d) {
        return !this.mDeviceToClientIDMap.containsKey(d)?'\uffff':((Integer)this.mDeviceToClientIDMap.get(d)).intValue();
    }

    byte getClientIf() {
        return this.mClientIf;
    }

    BluetoothDevice getDeviceforConnId(int connId) {
        return (BluetoothDevice)this.mClientIDToDeviceMap.get(new Integer(connId));
    }

    boolean isDeviceDisconnecting(BluetoothDevice device) {
        return this.mDisconnectingDevices.indexOf(device) != -1;
    }

    void onServiceRefreshed(BleClientService s, BluetoothDevice device) {
        int i = this.mRequiredServices.indexOf(s);
        if(i + 1 < this.mRequiredServices.size()) {
            Log.d("BleClientProfile", "Refreshing next service");
            ((BleClientService)this.mRequiredServices.get(i + 1)).refresh(device);
        } else {
            this.onRefreshed(device);
        }

    }

    public void onInitialized(boolean success) {
        Log.d("BleClientProfile", "onInitialized");
        if(success) {
            this.registerProfile();
        }

    }

    public void onProfileRegistered() {
        Log.d("BleClientProfile", "onProfileRegistered");
    }

    public void onProfileDeregistered() {
        Log.d("BleClientProfile", "onProfileDeregistered");
    }

    public void onDeviceConnected(BluetoothDevice device) {
        Log.d("BleClientProfile", "onDeviceConnected");
        this.refresh(device);
    }

    public void onDeviceDisconnected(BluetoothDevice device) {
        Log.d("BleClientProfile", "onDeviceDisconnected");
    }

    public void onRefreshed(BluetoothDevice device) {
        Log.d("BleClientProfile", "onRefreshed");
    }

    private class GattServiceConnection implements ServiceConnection {
        private GattServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("BleClientProfile", "Connected to GattService!");
            if(service != null) {
                try {
                    BleClientProfile.this.mService = IBluetoothGatt.Stub.asInterface(service);

                    int t;
                    for(t = 0; t < BleClientProfile.this.mRequiredServices.size(); ++t) {
                        ((BleClientService)BleClientProfile.this.mRequiredServices.get(t)).setProfile(BleClientProfile.this);
                    }

                    if(BleClientProfile.this.mOptionalServices != null) {
                        for(t = 0; t < BleClientProfile.this.mOptionalServices.size(); ++t) {
                            ((BleClientService)BleClientProfile.this.mOptionalServices.get(t)).setProfile(BleClientProfile.this);
                        }
                    }

                    BleClientProfile.this.onInitialized(true);
                } catch (Throwable var4) {
                    Log.e("BleClientProfile", "Unable to get Binder to GattService", var4);
                    BleClientProfile.this.onInitialized(false);
                }
            }

        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d("BleClientProfile", "Disconnected from GattService!");
        }
    }

    class BleClientCallback extends com.sen.lib.bluetooth.api.IBleClientCallback.Stub {
        BleClientCallback() {
        }

        public void onAppRegistered(byte status, byte client_if) {
            Log.d("BleClientProfile", "BleClientCallback::onAppRegistered (" + BleClientProfile.this.mAppUuid + ") status = " + status + " client_if = " + client_if);
            BleClientProfile.this.mClientIf = client_if;
            BleClientProfile.this.onProfileRegistered();
        }

        public void onAppDeregistered(byte client_if) {
            Log.d("BleClientProfile", "BleClientCallback::onAppDeregistered (" + BleClientProfile.this.mAppUuid + ") client_if = " + client_if);
            BleClientProfile.this.mClientIf = 0;
            BleClientProfile.this.onProfileDeregistered();
        }

        public void onConnected(String deviceAddress, int connID) {
            Log.d("BleClientProfile", "BleClientCallback::OnConnected (" + BleClientProfile.this.mAppUuid + ") " + deviceAddress + "connID = " + connID);
            BluetoothDevice d = BleClientProfile.this.findDeviceWaitingForConnection(deviceAddress);
            if(null == d) {
                d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
                synchronized(BleClientProfile.this.mConnectedDevices) {
                    BleClientProfile.this.mConnectedDevices.add(d);
                }

                synchronized(BleClientProfile.this.mConnectingDevices) {
                    BleClientProfile.this.mConnectingDevices.remove(d);
                }

                synchronized(BleClientProfile.this.mDisconnectingDevices) {
                    BleClientProfile.this.mDisconnectingDevices.remove(d);
                }
            }

            if(d.getBondState() == 12) {
                Log.d("BleClientProfile", "onConnected device is bonded start encrypt the  link");
                BleClientProfile.this.setEncryption(d, (byte)1);
            }

            BleClientProfile.this.mClientIDToDeviceMap.put(new Integer(connID), d);
            BleClientProfile.this.mDeviceToClientIDMap.put(d, new Integer(connID));
            BleClientProfile.this.mPeerServices.clear();

            try {
                BleClientProfile.this.mService.searchService(connID, (BluetoothGattID)null);
            } catch (RemoteException var8) {
                Log.d("BleClientProfile", "Error calling searchService " + var8.toString());
            }

        }

        public void onDisconnected(int connID, String deviceAddress) {
            Log.d("BleClientProfile", "BleClientCallback::onDisconnected (" + BleClientProfile.this.mAppUuid + ") connID = " + connID);
            BleClientProfile.this.onDeviceDisconnected((BluetoothDevice)BleClientProfile.this.mClientIDToDeviceMap.get(new Integer(connID)));
            BluetoothDevice d = (BluetoothDevice)BleClientProfile.this.mClientIDToDeviceMap.get(new Integer(connID));
            BleClientProfile.this.mDeviceToClientIDMap.remove(d);
            BleClientProfile.this.mClientIDToDeviceMap.remove(new Integer(connID));
            BleClientProfile.this.mConnectedDevices.remove(d);
            BleClientProfile.this.mDisconnectingDevices.remove(d);
        }

        public void onSearchResult(int connID, BluetoothGattID srvcId) {
            Log.d("BleClientProfile", "BleClientCallback::onSearchResult (" + BleClientProfile.this.mAppUuid + ") connID = " + connID + " svcId: id = " + srvcId.toString() + " inst id = " + srvcId.getInstanceID());
            BleClientProfile.this.mPeerServices.add(BleApiHelper.gatt2BleID(srvcId));
        }

        public void onSearchCompleted(int connID, int status) {
            Log.d("BleClientProfile", "BleClientCallback::onSearchCompleted (" + BleClientProfile.this.mAppUuid + ") connID = " + connID + "status = " + status);
            int nServicesFound = 0;
            if(BleClientProfile.this.mRequiredServices == null) {
                Log.d("BleClientProfile", "mRequiredServices is null");
            } else if(BleClientProfile.this.mPeerServices == null) {
                Log.d("BleClientProfile", "mPeerServices is null");
            } else {
                for(int device = 0; device < BleClientProfile.this.mRequiredServices.size(); ++device) {
                    for(int j = 0; j < BleClientProfile.this.mPeerServices.size(); ++j) {
                        if(((BleGattID)BleClientProfile.this.mPeerServices.get(j)).toString().equalsIgnoreCase(((BleClientService)BleClientProfile.this.mRequiredServices.get(device)).getServiceId().toString())) {
                            ((BleClientService)BleClientProfile.this.mRequiredServices.get(device)).setInstanceID((BluetoothDevice)BleClientProfile.this.mClientIDToDeviceMap.get(new Integer(connID)), ((BleGattID)BleClientProfile.this.mPeerServices.get(j)).getInstanceID());
                            ++nServicesFound;
                            break;
                        }
                    }
                }

                Log.d("BleClientProfile", "BleClientCallback::onSearchResult - found " + nServicesFound + " out of " + BleClientProfile.this.mRequiredServices.size() + " services needed for this profile");
                BluetoothDevice var6 = (BluetoothDevice)BleClientProfile.this.mClientIDToDeviceMap.get(new Integer(connID));
                if(var6 == null) {
                    Log.d("BleClientProfile", "No bluetooth device in the device map for connid = " + connID);
                } else if(BleClientProfile.this.isDeviceDisconnecting(var6)) {
                    Log.d("BleClientProfile", "Device disconnecting...");
                } else if(nServicesFound == BleClientProfile.this.mRequiredServices.size()) {
                    Log.d("BleClientProfile", "the num of Srvs found match the required srv size ");
                    BleClientProfile.this.onDeviceConnected(var6);
                } else {
                    Log.d("BleClientProfile", "the num of Srvs found DOES NOT match the required srv size ");
                }

            }
        }
    }
}

