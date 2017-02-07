package com.example.nutfreedom.helloworld;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class BundleSavedState extends View.BaseSavedState {

    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public BundleSavedState(Parcelable superState) {
        super(superState);
    }

    public BundleSavedState(Parcel source) {
        super(source);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public BundleSavedState(Parcel source, ClassLoader loader) {
        super(source, loader);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(bundle);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new BundleSavedState(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new BundleSavedState[size];
        }
    };
}
