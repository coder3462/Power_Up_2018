package org.usfirst.frc.team1923.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1923.robot.autonomous.Autonomous;
import org.usfirst.frc.team1923.robot.commands.drive.DriveDistanceCommand;

@Autonomous(
        startingPosition = { Autonomous.Side.LEFT, Autonomous.Side.RIGHT }
)
public class CrossLineLongAuton extends CommandGroup {

    public CrossLineLongAuton() {
        this.addSequential(new DriveDistanceCommand(280));
    }

}