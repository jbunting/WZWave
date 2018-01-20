/*
 *******************************************************************************
 * Copyright (c) 2013 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.wzwave.commandclass;

import com.whizzosoftware.wzwave.node.ZWaveEndpoint;

import java.util.ServiceLoader;

/**
 * Convenience factory class that creates CommandClass instances from a command class ID byte.
 *
 * @author Dan Noguerol
 */
public class CommandClassFactory {
    public static CommandClass createCommandClass(byte commandClassId) {
        switch (commandClassId) {
            case AlarmCommandClass.ID:
                return new AlarmCommandClass();
            case AlarmSensorCommandClass.ID:
                return new AlarmSensorCommandClass();
            case BasicCommandClass.ID:
                return new BasicCommandClass();
            case BatteryCommandClass.ID:
                return new BatteryCommandClass();
            case BinarySensorCommandClass.ID:
                return new BinarySensorCommandClass();
            case BinarySwitchCommandClass.ID:
                return new BinarySwitchCommandClass();
            case ColorControlCommandClass.ID:
                return new ColorControlCommandClass();
            case ManufacturerSpecificCommandClass.ID:
                return new ManufacturerSpecificCommandClass();
            case MeterCommandClass.ID:
                return new MeterCommandClass();
            case MultiInstanceCommandClass.ID:
                return new MultiInstanceCommandClass();
            case MultilevelSensorCommandClass.ID:
                return new MultilevelSensorCommandClass();
            case MultilevelSwitchCommandClass.ID:
                return new MultilevelSwitchCommandClass();
            case NoOperationCommandClass.ID:
                return new NoOperationCommandClass();
            case VersionCommandClass.ID:
                return new VersionCommandClass();
            case WakeUpCommandClass.ID:
                return new WakeUpCommandClass();
            default:
                ServiceLoader<CommandClassProvider> providers = ServiceLoader.load(CommandClassProvider.class, CommandClassProvider.class.getClassLoader());
                for (CommandClassProvider provider: providers) {
                    if (provider.ccId() == commandClassId) {
                        return provider.provide();
                    }
                }
                return null;
        }
    }

    public static <CC extends CommandClass> CC getCommandClass(ZWaveEndpoint node, Class<CC> commandClass) {
        byte ccId = getCommandClassId(commandClass);
        if (ccId >= 0 && node.hasCommandClass(ccId)) {
            return commandClass.cast(node.getCommandClass(ccId));
        }
        return null;
    }

    public static boolean hasCommandClass(ZWaveEndpoint node, Class<? extends CommandClass> commandClass) {
        byte ccId = getCommandClassId(commandClass);
        return node.hasCommandClass(ccId);
    }

    private static byte getCommandClassId(Class<? extends CommandClass> commandClass) {
        if (commandClass == AlarmCommandClass.class) {
            return AlarmCommandClass.ID;
        } else if (commandClass == AlarmSensorCommandClass.class) {
            return AlarmSensorCommandClass.ID;
        } else if (commandClass == BasicCommandClass.class) {
            return BasicCommandClass.ID;
        } else if (commandClass == BatteryCommandClass.class) {
            return BatteryCommandClass.ID;
        } else if (commandClass == BinarySensorCommandClass.class) {
            return BinarySensorCommandClass.ID;
        } else if (commandClass == BinarySwitchCommandClass.class) {
            return BinarySwitchCommandClass.ID;
        } else if (commandClass == ColorControlCommandClass.class) {
            return ColorControlCommandClass.ID;
        } else if (commandClass == ManufacturerSpecificCommandClass.class) {
            return ManufacturerSpecificCommandClass.ID;
        } else if (commandClass == MeterCommandClass.class) {
            return MeterCommandClass.ID;
        } else if (commandClass == MultiInstanceCommandClass.class) {
            return MultiInstanceCommandClass.ID;
        } else if (commandClass == MultilevelSensorCommandClass.class) {
            return MultilevelSensorCommandClass.ID;
        } else if (commandClass == MultilevelSwitchCommandClass.class) {
            return MultilevelSwitchCommandClass.ID;
        } else if (commandClass == NoOperationCommandClass.class) {
            return NoOperationCommandClass.ID;
        } else if (commandClass == VersionCommandClass.class) {
            return VersionCommandClass.ID;
        } else if (commandClass == WakeUpCommandClass.class) {
            return WakeUpCommandClass.ID;
        } else {
            ServiceLoader<CommandClassProvider> providers = ServiceLoader.load(CommandClassProvider.class, CommandClassProvider.class.getClassLoader());
            for (CommandClassProvider provider: providers) {
                if (provider.ccType() == commandClass) {
                    return provider.ccId();
                }
            }
            return -1;
        }
    }
}
