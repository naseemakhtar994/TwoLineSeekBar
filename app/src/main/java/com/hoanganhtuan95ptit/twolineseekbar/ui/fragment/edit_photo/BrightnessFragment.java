package com.hoanganhtuan95ptit.twolineseekbar.ui.fragment.edit_photo;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoanganhtuan95ptit.library.TwoLineSeekBar;
import com.hoanganhtuan95ptit.twolineseekbar.R;
import com.hoanganhtuan95ptit.twolineseekbar.ui.view.crop.BrightnessImageView;
import com.hoanganhtuan95ptit.twolineseekbar.ui.view.crop.EditImageView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;

/**
 * Created by Hoang Anh Tuan on 11/22/2017.
 */

public class BrightnessFragment extends Fragment implements TwoLineSeekBar.OnSeekChangeListener,
        EditImageView.TransformImageListener {

    BrightnessImageView brightnessImageView;
    AVLoadingIndicatorView ivLoading;
    TwoLineSeekBar seekBar;
    TextView tvProcess;
    LinearLayout llProcess;

    private boolean start = true;

    public static BrightnessFragment create() {
        BrightnessFragment fragment = new BrightnessFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brightness, container, false);

        brightnessImageView = view.findViewById(R.id.brightnessImageView);
        ivLoading = view.findViewById(R.id.ivLoading);
        seekBar = view.findViewById(R.id.seekBar);
        tvProcess = view.findViewById(R.id.tvProcess);
        llProcess = view.findViewById(R.id.llProcess);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        brightnessImageView.setTransformImageListener(this);

        seekBar.reset();
        seekBar.setSeekLength(-50, 50, 0, 0.5f);
        seekBar.setOnSeekChangeListener(this);
        seekBar.setValue(0);

        ivLoading.show();
        String inputUrl = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()
                + "/CamDoc.png";
        brightnessImageView.setImageUri(Uri.parse("https://picsum.photos/750/1334/?image=7"), Uri.fromFile(new File(inputUrl)));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSeekChanged(float value, float step) {
        if (llProcess.getVisibility() != View.VISIBLE && !start)
            llProcess.setVisibility(View.VISIBLE);

        start = false;
        tvProcess.setText(Integer.toString((int) (value * 2)));
        brightnessImageView.postBrightness(value);
    }

    @Override
    public void onSeekStopped(float value, float step) {
        if (llProcess.getVisibility() != View.GONE) llProcess.setVisibility(View.GONE);
    }

    @Override
    public void onLoadComplete() {
        ivLoading.hide();
    }

    @Override
    public void onLoadFailure() {
        ivLoading.hide();
    }

    @Override
    public void onBrightness(float currentBrightness) {
    }

}
