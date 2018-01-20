package com.whizzosoftware.wzwave.commandclass;

import java.util.ServiceLoader;

public interface CommandClassProvider<CC extends CommandClass> {
    byte ccId();

    Class<CC> ccType();

    CC provide();
}
