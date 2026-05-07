package com.narxoz.rpg.guild;

import java.util.List;
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }
    @Override
    public List<String> topics() {
        return List.of("healing", "orders");
    }

    public void prepareAid(String payload) {
        getMediator().dispatch("healing", this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Healer " + getName() + "] received on '" + topic + "' from " + from.getName() + ": " + payload);
    }
}
