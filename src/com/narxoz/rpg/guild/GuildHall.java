package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    @Override
    public void register(GuildMember member) {
        for (String topic : member.topics()) {
            addSubscriber(topic, member);
        }
    }
    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        for (GuildMember member : subscribersFor(topic)) {
            if (member != from) {
                member.receive(topic, from, payload);
            }
        }
    }
    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    public int subscriberCount(String topic) {
        return membersByTopic.getOrDefault(topic, List.of()).size();
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
