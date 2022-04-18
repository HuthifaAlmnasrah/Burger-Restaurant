package com.example.burger.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burger.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    public static final Pattern PASSWORD_PATTREN = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$"
    );

    private EditText fullName, email, password, rewritePassword, phoneNumber;
    private TextView signUp;
    private Button location;

    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        initViews();

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                        if (isGPSEnabled()) {
                            LocationServices.getFusedLocationProviderClient(RegisterActivity.this).requestLocationUpdates(locationRequest, new LocationCallback() {
                                @SuppressLint("MissingPermission")
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    LocationServices.getFusedLocationProviderClient(RegisterActivity.this).removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() > 0) {
                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        LocationServices.getFusedLocationProviderClient(RegisterActivity.this).getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Location> task) {
                                                Location location1 = task.getResult();
                                                if(location1 != null){
                                                    Geocoder geocoder = new Geocoder(RegisterActivity.this, Locale.getDefault());
                                                    try {
                                                        List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(),1);
                                                        location.setText(addresses.get(0).getCountryName()+" - "+addresses.get(0).getLocality()+" - "+addresses.get(0).getAddressLine(0)+" - "+addresses.get(0).getLatitude()+" - "+addresses.get(0).getLongitude());
                                                        Log.v("location",addresses.get(0).getCountryName()+" - "+addresses.get(0).getLocality()+" - "+addresses.get(0).getAddressLine(0)+" - "+addresses.get(0).getLatitude()+" - "+addresses.get(0).getLongitude());
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                            }, Looper.getMainLooper());
                        }else{
                            turnOnGPS();
                        }
                    }else{
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allInfoExist()) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(RegisterActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(RegisterActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if(locationManager == null){
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return isEnabled;
    }


    private boolean allInfoExist() {
        if(fullName.getText().toString().isEmpty()){
            fullName.setError(getString(R.string.enter_your_name));
            return false;
        }else{
            fullName.setError(null);
        }
        if(phoneNumber.getText().toString().isEmpty()){
            phoneNumber.setError(getString(R.string.enter_your_phone_number));
            return false;
        }else{
            phoneNumber.setError(null);
        }
        if(email.getText().toString().isEmpty()){
            email.setError(getString(R.string.enter_your_email));
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError(getString(R.string.vaildemail));
            return false;
        } else if (!PASSWORD_PATTREN.matcher(password.getText().toString()).matches()) {
            password.setError(getString(R.string.passwordformat));
            return false;
        } else {
            password.setError(null);
        }
        if(location.getText().toString().isEmpty()){
            location.setError(getString(R.string.enter_your_location));
            return false;
        }else{
            location.setError(null);
        }
        if(password.getText().toString().isEmpty()){
            password.setError(getString(R.string.enter_your_password));
            return false;
        }else{
            password.setError(null);
        }
        if(rewritePassword.getText().toString().isEmpty()){
            rewritePassword.setError(getString(R.string.rewrite_password));
            return false;
        }else{
            rewritePassword.setError(null);
        }
        if(!rewritePassword.getText().toString().equals(password.getText().toString())){
            rewritePassword.setError(getString(R.string.rewrite_password_correct));
            return false;
        }else{
            rewritePassword.setError(null);
        }
        return true;
    }

    private void initViews() {
        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rewritePassword = findViewById(R.id.rewrite_password);
        location = findViewById(R.id.location);
        phoneNumber = findViewById(R.id.phone_number);
        signUp = findViewById(R.id.btn_sign_up);
    }
}