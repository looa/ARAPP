package com.mengdd.location.google;

import com.mengdd.arapp.GlobalARData;
import com.mengdd.arapp.R;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Test Activity for Location ViewModel
 *  
 * @author Dandan Meng <mengdandanno1@163.com>
 * @version 1.0
 * @since 2013-07-01
 * 
 */
public class TestLocationActivity extends Activity
{
	
	private CurrentLocationViewModel mLocationViewModel = null;
	
	private LocationModel mLocationModel2 = null;
	private LocationView mLocationView = null;
	
	private TextView mTextView = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_cur_location);
		
		mLocationViewModel = new CurrentLocationViewModel(this);
		
		mLocationViewModel.onCreate(null);
		
		FrameLayout frameLayout = (FrameLayout)findViewById(R.id.main_frame);
		
		frameLayout.addView(mLocationViewModel.getView(), 0);
		
		//the second Location View Model doesn't have UI, and use multiple source providers
		mLocationModel2 = new LocationModel(this);
		
		mTextView = (TextView)findViewById(R.id.textView);
		
		GlobalARData.addLocationListener(mLocationListener);
		
		//use a LocationView to show the LocationModel data
		mLocationView = new LocationView(this);
		mLocationView.onCreate(null);
		
		FrameLayout frameLayout2 = (FrameLayout)findViewById(R.id.frame2);
		frameLayout2.addView(mLocationView.getView());
		GlobalARData.addLocationListener(mLocationView);
		
		
	}
	
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		mLocationViewModel.onResume(null);
		
		mLocationModel2.registerLocationUpdates();
	}
	
	
	@Override
	protected void onPause()
	{
		super.onPause();
		
		mLocationViewModel.onPause();
		
		mLocationModel2.unregisterLocationUpdates();
		
		GlobalARData.removeLocationListener(mLocationListener);
		GlobalARData.removeLocationListener(mLocationView);
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		
		mLocationViewModel.onStop();
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		
		mLocationViewModel.onDestory();
	}
	
	private LocationListener mLocationListener = new LocationListener()
	{
		
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
			
			mTextView.setText(location.toString());
			
		}
	};

}
