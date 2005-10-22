/****************************************************************************
Copyright (c) 2005, Colorado School of Mines and others. All rights reserved.
This program and accompanying materials are made available under the terms of
the Common Public License - v1.0, which accompanies this distribution, and is
available at http://www.eclipse.org/legal/cpl-v10.html
****************************************************************************/
package edu.mines.jtk.dsp.test;

import junit.framework.*;
import java.util.Random;
import edu.mines.jtk.dsp.*;
import edu.mines.jtk.util.Array;
import static edu.mines.jtk.util.MathPlus.*;

/**
 * Tests {@link edu.mines.jtk.dsp.Histogram}.
 * @author Dave Hale, Colorado School of Mines
 * @version 2005.10.18
 */
public class HistogramTest extends TestCase {
  public static void main(String[] args) {
    TestSuite suite = new TestSuite(HistogramTest.class);
    junit.textui.TestRunner.run(suite);
  }

  public void testConstant() {
    int n = 1001;
    float vfill = 2.0f;
    float[] v = Array.fillfloat(vfill,n);

    // Test with default range and number of bins.
    float vmin = vfill;
    float vmax = vfill;
    int nbin = 1;
    double dbin = 1.0f;
    double fbin = vmin+0.5*dbin;
    Histogram h = new Histogram(v);
    assertEquals(vmin,h.getMinValue());
    assertEquals(vmax,h.getMaxValue());
    assertEquals(nbin,h.getBinCount());
    assertEquals(dbin,h.getBinDelta());
    assertEquals(fbin,h.getBinFirst());
    assertEquals(0,h.getLowCount());
    assertEquals(n,h.getInCount());
    assertEquals(0,h.getHighCount());

    // Test with specified number of bins.
    vmin = vfill;
    vmax = vfill;
    nbin = 3;
    dbin = 1.0;
    fbin = vmin+0.5*dbin;
    h = new Histogram(v,nbin);
    assertEquals(vmin,h.getMinValue());
    assertEquals(vmax,h.getMaxValue());
    assertEquals(nbin,h.getBinCount());
    assertEquals(dbin,h.getBinDelta());
    assertEquals(fbin,h.getBinFirst());
    assertEquals(0,h.getLowCount());
    assertEquals(n,h.getInCount());
    assertEquals(0,h.getHighCount());

    // Test with specified range too low.
    vmin = vfill-2.0f;
    vmax = vfill-1.0f;
    nbin = 1;
    dbin = vmax-vmin;
    fbin = 0.5*(vmin+vmax);
    h = new Histogram(v,vmin,vmax);
    assertEquals(vmin,h.getMinValue());
    assertEquals(vmax,h.getMaxValue());
    assertEquals(nbin,h.getBinCount());
    assertEquals(dbin,h.getBinDelta());
    assertEquals(fbin,h.getBinFirst());
    assertEquals(0,h.getLowCount());
    assertEquals(0,h.getInCount());
    assertEquals(n,h.getHighCount());

    // Test with specified range too high.
    vmin = vfill+1.0f;
    vmax = vfill+2.0f;
    nbin = 1;
    dbin = vmax-vmin;
    fbin = 0.5*(vmin+vmax);
    h = new Histogram(v,vmin,vmax);
    assertEquals(vmin,h.getMinValue());
    assertEquals(vmax,h.getMaxValue());
    assertEquals(nbin,h.getBinCount());
    assertEquals(dbin,h.getBinDelta());
    assertEquals(fbin,h.getBinFirst());
    assertEquals(n,h.getLowCount());
    assertEquals(0,h.getInCount());
    assertEquals(0,h.getHighCount());
  }

  public void testRamp() {
    int n = 1001;
    float[] v = Array.rampfloat(0.0f,1.0f,n);
    float vmin = Array.min(v);
    float vmax = Array.max(v);
    int nbin = 10;
    double dbin = (vmax-vmin)/nbin;
    double fbin = vmin+0.5*dbin;
    Histogram h = new Histogram(v,nbin);
    assertEquals(nbin,h.getBinCount());
    assertEquals(dbin,h.getBinDelta());
    assertEquals(fbin,h.getBinFirst());
    assertEquals(n,h.getInCount());
    assertEquals(0,h.getLowCount());
    assertEquals(0,h.getHighCount());
  }

  public void testGaussian() {
    Random r = new Random();
    int n = 1000;
    float[] v = new float[n];
    for (int i=0; i<n; ++i)
      v[i] = (float)r.nextGaussian();
    Histogram h = new Histogram(v);
    //Array.dump(h.getDensities());
    int nbin = h.getBinCount();
    double dbin = h.getBinDelta();
    double fbin = h.getBinFirst();
    //System.out.println("nbin="+nbin+" dbin="+dbin+" fbin="+fbin);
  }
}