package com.m2dl.mini_projet.mini_projet_android;

import android.Manifest;
import android.app.ProgressDialog;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.m2dl.mini_projet.mini_projet_android.data.photo.Photo;
import com.m2dl.mini_projet.mini_projet_android.fragment.MarkerDialogFragment;
import com.m2dl.mini_projet.mini_projet_android.fragment.PhotoDialogFragment;
import com.m2dl.mini_projet.mini_projet_android.fragment.TagSelectDialogFragment;
import com.m2dl.mini_projet.mini_projet_android.photos.ServiceGenerator;
import com.m2dl.mini_projet.mini_projet_android.photos.SimpleImageTag;

import com.m2dl.mini_projet.mini_projet_android.provider.IPhotoProvider;
import com.m2dl.mini_projet.mini_projet_android.provider.PhotoProvider;
import com.m2dl.mini_projet.mini_projet_android.utils.BitmapUtil;
import com.m2dl.mini_projet.mini_projet_android.utils.PointInteretManager;
import com.m2dl.mini_projet.mini_projet_android.data.tag.Tag;
import com.m2dl.mini_projet.mini_projet_android.utils.TagUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity
        extends AppCompatActivity
        implements LocationListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String TAG = "UPS-Caring";

    private Uri imageUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Bitmap myBitmap;
    private String imageFilePath;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    private FragmentManager fm;

    private LocationManager locationManager = null;
    private Boolean isGPSOn = false;
    private Double coordLat, coordLong;

    private PointInteretManager pointInteretManager;
    private Set<Tag> allTags;
    private Set<Tag> selectedTags;

    private IPhotoProvider photoProvider;
    private Map<Marker, Photo> myPhotoMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (screenIsLarge()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        myPhotoMarkers = new HashMap<>();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, this);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            isGPSOn = true;
        }

        FloatingActionButton fabPhoto = (FloatingActionButton) findViewById(R.id.fabPhoto);
        fabPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto(view);
            }
        });

        FloatingActionButton fabTagSelect = (FloatingActionButton) findViewById(R.id.fabTagSelect);
        fabTagSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTags();
            }
        });

        fm = getFragmentManager();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        pointInteretManager = new PointInteretManager(this);

        // Temp provider
        photoProvider = new PhotoProvider();

        // Init tag list
        allTags = new TreeSet<>();
        allTags.addAll(pointInteretManager.getPointInterets());
        allTags.addAll(TagUtil.extractTags(photoProvider.getPhotos()));
        selectedTags = new TreeSet<>(allTags);
    }

    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private void selectTags() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = TagSelectDialogFragment.newInstance(new ArrayList<>(allTags), new ArrayList<>(selectedTags));
        newFragment.show(ft, "dialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);

                    ContentResolver cr = getContentResolver();
                    try {
                        myBitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                        myBitmap = BitmapUtil.resize(myBitmap);
                        imageFilePath = imageUri.getPath();
                        ExifInterface exif = new ExifInterface(imageFilePath);
                        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                myBitmap = BitmapUtil.rotateImage(myBitmap, 90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                myBitmap = BitmapUtil.rotateImage(myBitmap, 180);
                                break;
                        }
                        String dialogTitle;
                        String dialogMessage;
                        if (!isGPSOn) {
                            dialogTitle = "Géolocalisation GPS...";
                            dialogMessage = "Veuillez activer votre GPS puis patienter pendant la géolocalisation";
                        } else {
                            dialogTitle = "Géolocalisation GPS...";
                            dialogMessage = "Veuillez patienter pendant la géolocalisation";
                        }
                        final ProgressDialog progDialog = ProgressDialog.show(MainActivity.this, dialogTitle, dialogMessage, true);
                        new Thread() {
                            public void run() {
                                try {
                                    while (coordLat.equals(0.0d) && coordLong.equals(0.0d)) ;
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                                    if (prev != null) {
                                        ft.remove(prev);
                                    }
                                    ft.addToBackStack(null);
                                    DialogFragment newFragment = PhotoDialogFragment.newInstance(myBitmap, coordLat, coordLong, imageFilePath);
                                    newFragment.show(ft, "dialog");
                                } catch (Exception e) {
                                }
                                progDialog.dismiss();
                            }
                        }.start();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Votre GPS semble désactivé, voulez-vous l'activer ? (Le GPS est essentiel pour le bon fonctionnement de l'application)")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        coordLat = location.getLatitude();
        coordLong = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
        //buildAlertMessageNoGps();
        coordLat = 0.0;
        coordLong = 0.0;
        isGPSOn = false;
    }

    @Override
    public void onProviderEnabled(String provider) {
        isGPSOn = true;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in Paul Sab and move the camera
        LatLng paulSab = new LatLng(43.560653, 1.467676);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paulSab, 15));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        mMap.setOnMarkerClickListener(this);

        showDefaultPoint();

        refreshPhoto();
    }

    private void showDefaultPoint() {
        // Add default interest point as polygon/circle to the map
        Map<Tag, List<Object>> mapPointInteret = pointInteretManager.getMapPointInteret();
        for (Tag pointInteret : mapPointInteret.keySet()) {
            List<Object> listOptions = mapPointInteret.get(pointInteret);

            for (Object options : listOptions) {
                if (options instanceof PolygonOptions) {
                    mMap.addPolygon((PolygonOptions) options);
                } else if (options instanceof CircleOptions) {
                    mMap.addCircle((CircleOptions) options);
                }
            }
        }
    }

    /*private void showPhotoMarker() {
        // Place all photos on map
        for (Photo photo : photoProvider.getPhotos()) {
            // Check if photo contains tag in selectedTags
            if (TagUtil.containsOneOf(selectedTags, photo.getTags())) {
                putInPhotoMarkers(photo);
            }
        }
    }*/

    private void showPhotoMarker(List<Photo> photos) {
        // Place all photos on map
        for (Photo photo : photos) {
            putInPhotoMarkers(photo);
        }
    }

    public void putInPhotoMarkers(Photo photo) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(photo.getCoordLat(), photo.getCoordLong())));
        myPhotoMarkers.put(marker, photo);
    }

    public void refreshPhoto() {
        for (Marker marker : myPhotoMarkers.keySet()) {
            marker.remove();
        }
        myPhotoMarkers.clear();
        /*
        AsyncTask<Void,Void,List<Photo>> asyncTask = new AsyncTask<Void, Void, List<Photo>>() {
            @Override
            protected List<Photo> doInBackground(Void... params) {
                return photoProvider.getPhotos();
            }

            @Override
            protected void onPostExecute(List<Photo> photos) {
                showPhotoMarker(photos);
            }
        };
        try {
            asyncTask.execute();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        */
        Callback<List<com.m2dl.mini_projet.mini_projet_android.photos.model.Photo>> callback = new Callback<List<com.m2dl.mini_projet.mini_projet_android.photos.model.Photo>>() {
            @Override
            public void success(List<com.m2dl.mini_projet.mini_projet_android.photos.model.Photo> photoList, Response response) {
                List<Photo> photos = new ArrayList<>();
                for (com.m2dl.mini_projet.mini_projet_android.photos.model.Photo p : photoList) {
                    Photo photo = new Photo(null, p.author, p.coordLat, p.coordLong, p.date, p.getUrl());
                    photo.setTag(p.tags);
                    String[] myTags = p.tags.split(",");
                    for (String tag : myTags) {
                        Tag myTag = new Tag(tag.replaceAll("\\s", ""));
                        photo.putTag(myTag);
                    }
                    photos.add(photo);
                }
                showPhotoMarker(photos);
            }

            @Override
            public void failure(RetrofitError error) {
                System.err.println("not good ...");
            }
        };

        SimpleImageTag imageTag = ServiceGenerator.createService(SimpleImageTag.class);
        imageTag.getAsyncPhotos(callback);


        // TODO: see Charles implementation
        // Store List<Photo> in this class
        //   listPhoto = photoProvider.getPhotos();
        // or
        // Store List<Photo> in provider class
        //   photoProvider.update() ?
    }

    public void setSelectedTags(List<Tag> newSelectedTags) {
        selectedTags.clear();
        selectedTags.addAll(newSelectedTags);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        Photo myPhoto = myPhotoMarkers.get(marker);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialogMarker");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = MarkerDialogFragment.newInstance(myPhoto);
        newFragment.show(ft, "dialogMarker");
        return true;
    }

    private boolean screenIsLarge()
    {
        int screenMask = getResources().getConfiguration().screenLayout;
        if ( ( screenMask & Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return true;
        }

        if ( (screenMask & Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return true;
        }

        return false;
    }
}
