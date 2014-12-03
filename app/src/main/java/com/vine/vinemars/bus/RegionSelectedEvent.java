package com.vine.vinemars.bus;

/**
 * Created by chengfei on 14/12/2.
 */
public class RegionSelectedEvent {

    private String[] selected;

    public RegionSelectedEvent(String[] selected) {
        this.selected = selected;
    }

    public String[] getSelected() {
        return selected;
    }
}
