package me.akaishin.cracked.event.events;

import me.akaishin.cracked.event.EventStage;
import me.akaishin.cracked.features.setting.Setting;

public class ValueChangeEvent
        extends EventStage {
    public Setting setting;
    public Object value;

    public ValueChangeEvent(Setting setting, Object value) {
        this.setting = setting;
        this.value = value;
    }
}

