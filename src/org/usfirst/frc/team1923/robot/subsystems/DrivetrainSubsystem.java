package org.usfirst.frc.team1923.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.drive.RawDriveCommand;

public class DrivetrainSubsystem extends Subsystem {

    private static final double WHEEL_DIAMETER = 6;
    private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

    private TalonSRX[] leftTalons = new TalonSRX[RobotMap.LEFT_TALON_PORTS.length];
    private TalonSRX[] rightTalons = new TalonSRX[RobotMap.RIGHT_TALON_PORTS.length];

    private ControlMode controlMode = ControlMode.PercentOutput;

    public DrivetrainSubsystem() {
        for (int i = 0; i < this.leftTalons.length; ++i) {
            this.leftTalons[i] = new TalonSRX(RobotMap.LEFT_TALON_PORTS[i]);
            if (i > 0) {
                this.leftTalons[i].set(ControlMode.Follower, RobotMap.LEFT_TALON_PORTS[0]);
            }
        }
        for (int i = 0; i < this.rightTalons.length; ++i) {
            this.rightTalons[i] = new TalonSRX(RobotMap.RIGHT_TALON_PORTS[i]);
            if (i > 0) {
                this.rightTalons[i].set(ControlMode.Follower, RobotMap.RIGHT_TALON_PORTS[0]);
            }
        }
    }

    public void drive(double left, double right) {
        this.leftTalons[0].set(this.controlMode, left);
        this.rightTalons[0].set(this.controlMode, right);
    }

    public void setControlMode(ControlMode mode) {
        this.controlMode = mode;
    }

    public void stop() {
        drive(0, 0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RawDriveCommand());
    }

    private double distanceToRotations(double distance) {
        return distance / WHEEL_CIRCUMFERENCE;
    }

    private double rotationsToDistance(double rotations) {
        return rotations * WHEEL_CIRCUMFERENCE;
    }

}