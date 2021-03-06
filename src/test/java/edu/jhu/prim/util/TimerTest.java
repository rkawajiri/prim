package edu.jhu.prim.util;

import junit.framework.Assert;

import org.junit.Test;

import edu.jhu.util.Timer;


public class TimerTest {

    private static final double ERROR_IN_MS = 4;
    private static final double ERROR_IN_SEC = ERROR_IN_MS / 1000.0;

    @Test
    public void testTotMs() throws InterruptedException {
        Timer timer = new Timer();
        timer.start();
        Thread.sleep(40);
        timer.stop();
        timer.start();
        Thread.sleep(30);
        timer.stop();
        Assert.assertEquals(70, timer.totMs(), ERROR_IN_MS);
        Assert.assertEquals(.070, timer.totSec(), ERROR_IN_SEC);       

    }
    
    @Test
    public void testAvg() throws InterruptedException {
        Timer timer = new Timer();
        for (int i=10; i<=30; i += 10) {
            timer.start();
            Thread.sleep(i);
            timer.stop();
        }
        Assert.assertEquals(20, timer.avgMs(), ERROR_IN_MS);       
        Assert.assertEquals(.020, timer.avgSec(), ERROR_IN_SEC);       
    }
    
    @Test
    public void testSplit() throws InterruptedException {
        Timer timer = new Timer();
        timer.start();
        for (int i=10; i<=30; i += 10) {
            Thread.sleep(i);
            timer.split();
        }
        Thread.sleep(20);
        Assert.assertEquals(20, timer.avgMs(), ERROR_IN_MS);       
    }
    

    @Test
    public void testTotWithoutStop() throws InterruptedException {
        Timer timer = new Timer();
        timer.start();
        double sum = 0;
        for (int i=10; i<=30; i += 10) {
            Thread.sleep(i);
            sum += i;
            Assert.assertEquals(sum, timer.totMs(), ERROR_IN_MS);
            Assert.assertEquals(sum / 1000.0, timer.totSec(), ERROR_IN_SEC);    
        }
    }
    
    @Test
    public void testMaxSplit() throws InterruptedException {
        Timer timer = new Timer();
        for (int i=10; i<=30; i += 10) {
            timer.start();
            Thread.sleep(i);
            timer.stop();
        }
        Assert.assertEquals(30, timer.maxSplitMs(), ERROR_IN_MS); 
    }

    @Test
    public void testStdDev() throws InterruptedException {
        Timer timer = new Timer();
        for (int i=10; i<=30; i += 10) {
            timer.start();
            Thread.sleep(i);
            timer.stop();
        }
        Assert.assertEquals(Math.sqrt((sq(10 - 20) + sq(20-20) + sq(30-20)) / 2), timer.stdDevMs(), ERROR_IN_MS); 
    }
    
    private static double sq(double v) {
        return v * v;
    }
}
