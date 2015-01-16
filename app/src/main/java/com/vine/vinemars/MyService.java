package com.vine.vinemars;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.vine.vinemars.aidl.ISynchronous;

/**
 * Created by chengfei on 14/12/11.
 */
public class MyService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final ISynchronous.Stub mBinder = new ISynchronous.Stub() {

        @Override
        public String getThreadNameFast() throws RemoteException {
            return null;
        }

        @Override
        public String getThreadNameSlow(long sleep) throws RemoteException {
            return null;
        }

        @Override
        public String getThreadNameBlocking() throws RemoteException {
            return null;
        }

        @Override
        public String getThreadNameUnblock() throws RemoteException {
            return null;
        }
    };
}
