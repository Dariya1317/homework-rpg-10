package com.narxoz.rpg.guild;

import java.util.List;
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }
    @Override
    public List<String> topics() {
        return List.of("scouting", "orders");
    }

    public void reportRoute(String payload) {
        getMediator().dispatch("scouting", this, payload);
    }
    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Scout " + getName() + "] received on '" + topic + "' from " + from.getName() + ": " + payload);
    }
}
