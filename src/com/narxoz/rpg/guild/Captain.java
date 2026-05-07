package com.narxoz.rpg.guild;

import java.util.List;

/**
 * Guild officer responsible for orders and mission coordination.
 */
public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> topics() {
        return List.of("supplies", "scouting", "healing");
    }

    public void issueOrder(String payload) {
        getMediator().dispatch("orders", this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Captain " + getName() + "] received on '" + topic + "' from " + from.getName() + ": " + payload);
    }
}
