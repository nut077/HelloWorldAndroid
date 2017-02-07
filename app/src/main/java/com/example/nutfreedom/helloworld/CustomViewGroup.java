package com.example.nutfreedom.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.FrameLayout;

public class CustomViewGroup extends FrameLayout {

    private Button btnHello;

    public CustomViewGroup(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.sample_layout, this);
        initInstances();
    }

    private void initInstances() {
        btnHello = (Button) findViewById(R.id.btnCustomViewGroupHello);
    }

    public void setButtonText(String text) {
        btnHello.setText(text);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle childrenStates = new Bundle();
        for (int i = 0; i < getChildCount(); i++) {
            int id = getChildAt(i).getId();
            if (id != 0) {
                SparseArray<Parcelable> childrenState = new SparseArray<>();
                getChildAt(i).saveHierarchyState(childrenState);
                childrenStates.putSparseParcelableArray(String.valueOf(id), childrenState);
            }
        }

        Bundle bundle = new Bundle();
        bundle.putBundle("childrenStates", childrenStates);

        BundleSavedState bundleSavedState = new BundleSavedState(superState);
        bundleSavedState.setBundle(bundle);
        return bundleSavedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState bundleSavedState = (BundleSavedState) state;
        super.onRestoreInstanceState(bundleSavedState.getSuperState());

        Bundle childrenStates = bundleSavedState.getBundle().getBundle("childrenStates");

        for (int i = 0; i < getChildCount(); i++) {
            int id = getChildAt(i).getId();
            if (id != 0) {
                assert childrenStates != null;
                if (childrenStates.containsKey(String.valueOf(id))) {
                    SparseArray<Parcelable> childrenState = childrenStates.getSparseParcelableArray(String.valueOf(id));
                    getChildAt(i).restoreHierarchyState(childrenState);
                }
            }
        }
    }
}
