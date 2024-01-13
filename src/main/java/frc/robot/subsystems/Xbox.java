// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class Xbox extends XboxController {
    public static final int A = 1;
    public static final int B = 2;
    public static final int X = 3;
    public static final int Y = 4;

    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;

    public static final int START = 7;
    public static final int RESET = 8;

    public static final int LEFT_CLICK = 9;
    public static final int RIGHT_CLICK = 10;

    public static final int LEFT_AXIS_Y = 1;
    public static final int RIGHT_AXIS_Y = 5;

    public static final int LEFT_AXIS_X = 0;
    public static final int RIGHT_AXIS_X = 4;

    public static final int LEFT_AXIS_TRIGGER = 2;
    public static final int RIGHT_AXIS_TRIGGER = 3;

    public static final int LT = 2, RT = 3;

    public int[] AXIS_INVERSIONS = {1, 1, 1, 1, 1, 1};

    // Control scheme

    public static int layerState;


    JoystickButton aButton, bButton, xButton, yButton, leftBumper, rightBumper, start, reset, rightTrigger, leftTrigger;

    JoystickButton[] buttons = new JoystickButton[] {
        null, aButton, bButton, xButton, yButton, leftBumper, rightBumper, start, reset, rightTrigger, leftTrigger
    };

    public enum DPAD {
        NEUTRAL, 
        UP, 
        UP_RIGHT, 
        RIGHT, 
        DOWN_RIGHT, 
        DOWN, 
        DOWN_LEFT, 
        LEFT, 
        UP_LEFT;
    }

    public enum RUMBLE_STATES {
        LIGHT, HEAVY
    }

    /**
     * 
     * @param port - Id of the controller (found in driver station)
     * 
     */
    public Xbox(int port) {
        super(port);
        for (int i = 1; i < buttons.length; i++) {
            buttons[i] = new JoystickButton(this, i);
        }
    }

    public Xbox getStick() {
        return this;
    }

    /**
     * Inverts the axis provided, if your controller is weird (and sussy)
     * @param axis - the axis to be inverted
     * 
     * @author Foz
     * i did not hit her- oh hi mark 
     * https://youtu.be/aekfPU0SwNw
     * @return this controller for nice inline inversion statements :)
     */
    public Xbox invertAxis(int axis) {
        this.AXIS_INVERSIONS[axis] = -AXIS_INVERSIONS[axis];
        return this;
    }
    
    /**
     * @return a double from -1 to 1 of the vertical angle of the left joystick
     */
    public double getLeftStickX() {
        return getRawAxis(LEFT_AXIS_X) * AXIS_INVERSIONS[LEFT_AXIS_X];
    }

    /**
     * @return a double from -1 to 1 of the vertical angle of the left joystick
     */
    public double getLeftStickY() {
        return getRawAxis(LEFT_AXIS_Y) * AXIS_INVERSIONS[LEFT_AXIS_Y];
    }

    /**
     * @return a double from -1 to 1 of the vertical angle of the right joystick
     */
    public double getRightStickY() {
        return getRawAxis(RIGHT_AXIS_Y) * AXIS_INVERSIONS[RIGHT_AXIS_Y];
    }
    
    public double getRightStickX() {
        return getRawAxis(RIGHT_AXIS_X) * AXIS_INVERSIONS[RIGHT_AXIS_X];
    }

    public double getRightTriggerAxis() {
        return getRawAxis(RT) * AXIS_INVERSIONS[RT];
    }

    public double getLeftTriggerAxis() {
        return getRawAxis(LT) * AXIS_INVERSIONS[LT];
    }

    /**
     * Set the rumble of the controller
     * @param xbox - Controller
     * @param value - A number from 0-1 for strength of rumble
     * @param state - Xbox.RUMBLE_STATES.LIGHT or Xbox.RUMBLE_STATES.HEAVY
     */
    public static void setRumble(Xbox xbox, double value, RUMBLE_STATES state) {
        switch(state){
            case LIGHT:
                xbox.setRumble(RumbleType.kRightRumble, value);
                break;
            case HEAVY:
                xbox.setRumble(RumbleType.kLeftRumble, value);
                break;
        }
    }

    /**
     * Sets the rumble value of the controller
     * @param xbox - Controller
     * @param value - a number from 0-1 for strength of rumble
     * @param state - 0 for light 1 for heavy
     */
    public static void setRumble(Xbox xbox, double value, int state) {
        switch(state){
            case 0:
                xbox.setRumble(RumbleType.kRightRumble, value);
                break;
            case 1:
                xbox.setRumble(RumbleType.kLeftRumble, value);
                break;
        }
    }

    public DPAD getState() {
        int angle = getPOV();
        switch (angle) {
            case -1:
                return DPAD.NEUTRAL;
            case 0:
                return DPAD.UP;
            case 45:
                return DPAD.UP_RIGHT;
            case 90:
                return DPAD.RIGHT;
            case 135:
                return DPAD.DOWN_RIGHT;
            case 180:
                return DPAD.DOWN;
            case 225:
                return DPAD.DOWN_LEFT;
            case 270:
                return DPAD.LEFT;
            case 315:
                return DPAD.UP_LEFT;
        }
        return DPAD.NEUTRAL;
    }
    
    public JoystickButton getButton(int buttonName) {
        return buttons[buttonName];
    }

    // ----- Control Scheme -----

    public enum controllerState {
        LAYER_1,
        LAYER_2;
    }

    public double getInvertedRawAxis(int axis) {
        return AXIS_INVERSIONS[axis] * getRawAxis(axis);
    }
}