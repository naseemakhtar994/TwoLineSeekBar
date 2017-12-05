package com.hoanganhtuan95ptit.twolineseekbar.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.hoanganhtuan95ptit.permission.Permission;


/**
 * Created by Hoang Anh Tuan on 3/10/2017.
 */

public abstract class BaseFragment extends Fragment implements Permission.OnPermissionFeedbackListener {

    private Permission permission;
    protected FragmentActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        permission = Permission.Builder.with(activity)
                .setOnPermissionFeedbackListener(this)
                .build();
    }

    /**
     * show notification
     *
     * @param message infor show
     */
    protected void showMessage(int message) {
        Toast.makeText(activity, getString(message), Toast.LENGTH_LONG).show();
    }

    /**
     * ask permission android
     *
     * @param requestCode code
     * @param permissions list permission
     */
    protected void askPermissions(int requestCode, String... permissions) {
        permission.askPermissions(requestCode, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permission.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void accept(int requestCode) {
    }

    @Override
    public void reject(int requestCode) {
    }

}
