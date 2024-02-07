package com.example.flashlight;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashlight.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashSwitch(true);
            }
        });

        binding.lightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashSwitch(false);
            }
        });
    }

    private void flashSwitch(boolean input) {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

            CameraManager camManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String cameraID = null;
            try {
                cameraID = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraID, input);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(this, "No Flash Light found", Toast.LENGTH_SHORT).show();
        }
    }
}
