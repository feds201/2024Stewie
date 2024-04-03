// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.compound;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.RotateWristToPosition;
import frc.robot.commands.intake.RotateWristToPositionInfinite;
import frc.robot.commands.leds.SetLEDColor;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Vision.VisionVariables;
import frc.robot.subsystems.intake.Wrist;
import frc.robot.subsystems.shooter.ShooterIRSensor;
import frc.robot.subsystems.shooter.ShooterRotation;
import frc.robot.subsystems.shooter.ShooterServos;
import frc.robot.subsystems.shooter.ShooterWheels;
import frc.robot.subsystems.leds.Leds;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootFromHandoff extends SequentialCommandGroup {
    /**
     * Creates a new ShootNoteAtSpeaker.
     *
     * @param wrist
     * @param shooterRotation
     * @param shooterWheels
     * @param servos
     */
    
    public ShootFromHandoff(Wrist wrist, ShooterRotation shooterRotation, ShooterWheels shooterWheels,
                            ShooterServos servos, ShooterIRSensor irsensor, Leds leds) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(
            // new RotateWristToPosition(wrist,
            // IntakeConstants.WristPID.kWristIdlePosition),
            new SetLEDColor(leds, -0.79),
            new ParallelCommandGroup(
                new ShootNoteAtSpeakerOnly(shooterRotation, shooterWheels, servos, irsensor, leds)
            ),
            new ParallelCommandGroup(
                new RotateWristToPosition(wrist, IntakeConstants.WristPID.kWristIdlePosition),
                new SetLEDColor(leds, Leds.getAllianceColor())));
        
    }
    
}
