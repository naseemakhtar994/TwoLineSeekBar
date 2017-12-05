package com.hoanganhtuan95ptit.twolineseekbar.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hoanganhtuan95ptit.library.TwoLineSeekBar;
import com.hoanganhtuan95ptit.twolineseekbar.R;
import com.hoanganhtuan95ptit.twolineseekbar.ui.fragment.edit_photo.BrightnessFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermissions(1,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void accept(int requestCode) {
        super.accept(requestCode);
        addFragment(BrightnessFragment.create());
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.rootMain, fragment);
        ft.commit();
    }
}
