package com.narxoz.rpg.guild;

import java.util.List;

public class Loremaster extends GuildMember {
    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> topics() {
        return List.of("lore", "curse", "history", "orders");
    }

    public void shareLore(String payload) {
        System.out.println("[Loremaster " + getName() + "] broadcasting lore: " + payload);
        getMediator().dispatch("lore", this, payload);
    }

    public void reportCurse(String payload) {
        System.out.println("[Loremaster " + getName() + "] reporting curse: " + payload);
        getMediator().dispatch("curse", this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Loremaster " + getName() + "] received on '" + topic + "' from " + from.getName() + ": " + payload);
    }
}
