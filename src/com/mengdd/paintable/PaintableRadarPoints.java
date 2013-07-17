package com.mengdd.paintable;



import com.mengdd.ar.ui.Marker;
import com.mengdd.ar.ui.Radar;
import com.mengdd.arapp.GlobalARData;

import android.graphics.Canvas;

/**
 * This class extends PaintableObject to draw all the Markers at their
 * appropriate locations.
 * 
 * The source of the codes:
 * 1."android-augment-reality-framework"
 * project link: http://code.google.com/p/android-augment-reality-framework/
 * 
 * 
 * 2.The book: "Pro Android Augmented Reality"
 * http://www.apress.com/9781430239451
 * Official repository for Pro Android Augmented Reality:
 * https://github.com/RaghavSood/ProAndroidAugmentedReality
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 * @author Dandan Meng <mengdandanno1@163.com>
 * @version 1.0
 * @since 2013-07-01
 */
public class PaintableRadarPoints extends PaintableObject
{

	private final float[] locationArray = new float[3];
	private PaintablePoint paintablePoint = null;
	private PaintablePosition pointContainer = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint(Canvas canvas)
	{
		if (canvas == null)
			throw new NullPointerException();

		// Draw the markers in the circle
		float range = GlobalARData.getRadius() * 1000;
		float scale = range / Radar.RADIUS;
		for (Marker pm : GlobalARData.getMarkers())
		{
			pm.getLocation().get(locationArray);
			float x = locationArray[0] / scale;
			float y = locationArray[2] / scale;
			if ((x * x + y * y) < (Radar.RADIUS * Radar.RADIUS))
			{
				if (paintablePoint == null)
					paintablePoint = new PaintablePoint(pm.getColor(), true);
				else
					paintablePoint.set(pm.getColor(), true);

				if (pointContainer == null)
					pointContainer = new PaintablePosition(paintablePoint, (x
							+ Radar.RADIUS - 1), (y + Radar.RADIUS - 1), 0, 1);
				else
					pointContainer.set(paintablePoint, (x + Radar.RADIUS - 1),
							(y + Radar.RADIUS - 1), 0, 1);

				pointContainer.paint(canvas);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getWidth()
	{
		return Radar.RADIUS * 2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getHeight()
	{
		return Radar.RADIUS * 2;
	}
}
