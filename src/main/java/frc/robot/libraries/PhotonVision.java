// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.libraries;

import org.photonvision.PhotonCamera;

/** Add your docs here. */
public class PhotonVision {
    PhotonCamera m_photonCamera1;

    public PhotonVision() {
        m_photonCamera1 = new PhotonCamera("ourcamhere");
    }

    public void setDriveMode() {
        m_photonCamera1.setDriverMode(true);
    }

    public void setTarget() {
        m_photonCamera1.setDriverMode(false);
    }

    
}
