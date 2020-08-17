/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robotcode.camera;

import constants.CameraConstants;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private double mFOV = 59.6; // degrees

    public Limelight() {

    }

    // ******** //
    // RESOURCE //
    // ******** //

    public double getDoubleFromNetworkTable(String key) {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry(key).getDouble(0);
    }

    public NetworkTableEntry getEntryFromNetworkTable(String key) {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry(key);
    }

    // ********* //
    // DISTANCES //
    // ********* //
    public double xAngleToDistance() {
        return (CameraConstants.LimelightConstants.HEIGHT * Math.tan(Math.toRadians(getDoubleFromNetworkTable("tx"))));
    }

    public double yAngleToDistance() {
        return (CameraConstants.LimelightConstants.HEIGHT * Math.tan(Math.toRadians(getDoubleFromNetworkTable("ty"))));
    }


    // ************ //
    // LED SETTINGS //
    // ************ //
    public void setLedFromPipeline(){
        getEntryFromNetworkTable("ledMode").setNumber(0);
    }

    public void setLedOff(){
        getEntryFromNetworkTable("ledMode").setNumber(1);
    }

    public void setLedBlink(){
        getEntryFromNetworkTable("ledMode").setNumber(2);
    }

    public void setLedOn(){
        getEntryFromNetworkTable("ledMode").setNumber(3);
    }



    // *********** //
    // CAMERA MODE //
    // *********** //
    public void setVisionProcessor(){
        getEntryFromNetworkTable("camMode").setNumber(0);
    }

    public void setDriverCamera(){
        getEntryFromNetworkTable("camMode").setNumber(1);
    }



    // ******** //
    // PIPELINE //
    // ******** //
    public void setPipeline(int pPipeline){
        getEntryFromNetworkTable("pipeline").setNumber(pPipeline);
    }



    // ****** //
    // STREAM //
    // ****** //
    public void setStreamStandard(){
        getEntryFromNetworkTable("stream").setNumber(0);
    }
    
    public void setStreamMain(){
        getEntryFromNetworkTable("stream").setNumber(1);
    }
    
    public void setStreamSecondary(){
        getEntryFromNetworkTable("stream").setNumber(2);
    }



    // ******** //
    // SNAPSHOT //
    // ******** //
    public void stopSnapshot(){
        getEntryFromNetworkTable("snapshot").setNumber(0);
    }

    public void startSnapshot(){
        getEntryFromNetworkTable("snapshot").setNumber(1);
    }


}
