package com.mengdd.location.google;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mengdd.arapp.R;
import com.mengdd.components.ViewModel;

public class LocationView extends ViewModel implements LocationListener
{

	private View mRootView = null;

	private TextView mLatitude = null;
	private TextView mLongitude = null;
	private TextView mProvider = null;
	private TextView mAccuracy = null;

	public LocationView(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onCreate(Intent intent)
	{
		super.onCreate(intent);

		mRootView = mInflater.inflate(R.layout.location_view, null);
		
		mLatitude = (TextView)mRootView.findViewById(R.id.latitudeValue);
		mLongitude = (TextView)mRootView.findViewById(R.id.longitudeValue);
		mProvider = (TextView)mRootView.findViewById(R.id.providerValue);
		mAccuracy = (TextView)mRootView.findViewById(R.id.accuracyValue);

	}

	@Override
	public View getView()
	{
		return mRootView;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{

	}

	@Override
	public void onProviderEnabled(String provider)
	{

	}

	@Override
	public void onProviderDisabled(String provider)
	{

	}

	@Override
	public void onLocationChanged(Location location)
	{
		setLocationInfo(location);

	}

	public void setLocationInfo(Location location)
	{
		if (null != location)
		{
			mLongitude.setText(String.valueOf(location.getLongitude()));
			mLatitude.setText(String.valueOf(location.getLatitude()));

			mProvider.setText(location.getProvider());

			mAccuracy.setText(String.valueOf(location.getAccuracy()));
		}

	}

}