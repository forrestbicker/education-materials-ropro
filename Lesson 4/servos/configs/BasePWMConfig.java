package common.servos.configs;

import common.servos.configs.interfaces.IPWMConfig;

public class BasePWMConfig implements IPWMConfig {
    protected int channel;

    public BasePWMConfig(int channel) {
        this.channel = channel;
    }

    @Override
    public int getChannel() {
        return channel;
    }

}