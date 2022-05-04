package frc.robot.subsystems;

import java.util.ArrayList;

public class Smoother {
    private int arraySize;
    private ArrayList<Double> data = new ArrayList<Double>();
    private double average = 0;
    private double sum = 0;

    public Smoother(int n) 
    {
        arraySize = n;
        for (int i=0; i<arraySize; i++) {
            data.add(0.0);
        }
        
    }

    public double updateList(double speed) {
        data.add(speed);
    //    if (data.size() > arraySize) {
        data.remove(0);
        for (int i=0; i<arraySize; i++) {
            sum += data.get(i);
        }
       //     sum += (speed - data.remove(0));
      //  } 
       //else {
      //      sum += speed;
       // }
        average = sum / data.size();
        sum = 0;
        return average;
    }

    public double getAverage() {
        return average;
    }

   // public void setValue(double value) {
    //    for(int i = 0; i < arraySize; i++) {
   //         data.set(i, value);
   //     }
//}
    public void clearArray() {
        data.clear();
    }

    public void setSpeed(double rawSpeed) {
        for (int i=0; i<arraySize; i++) {
            data.set(i, rawSpeed);
        }
    }
    public double returnArray() {
        double tempSum = 0;
        for (int i=0; i<arraySize; i++) {
            tempSum += data.get(i);
        }
        return tempSum/arraySize;
    }
}
