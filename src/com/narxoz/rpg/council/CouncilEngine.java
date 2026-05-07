package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.guild.GuildMember;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        GuildMember coordinator = new CouncilCoordinator(hall);
        System.out.println("\n=== WAR COUNCIL: Quest Briefing (ordered) ===");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest quest = ordered.next();
            questsTraversed++;
            String msg = "Quest briefing: " + quest.getTitle() + " [" + quest.getPriority() + "]";
            hall.dispatch("orders", coordinator, msg);
            messagesRouted++;
            membersNotified += countRecipients(hall, "orders");
        }
        System.out.println("\n=== WAR COUNCIL: Priority Alerts (HIGH+) ===");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            Quest quest = priority.next();
            questsTraversed++;
            String msg = "Priority alert: " + quest.getTitle() + ", reward=" + quest.getRewardGold() + "g";
            hall.dispatch("supplies", coordinator, msg);
            messagesRouted++;
            membersNotified += countRecipients(hall, "supplies");
        }

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }

    private int countRecipients(GuildMediator hall, String topic) {
        if (hall instanceof GuildHall) {
            return ((GuildHall) hall).subscriberCount(topic);
        }
        return 0;
    }

    private static class CouncilCoordinator extends GuildMember {

        CouncilCoordinator(GuildMediator mediator) {
            super("Council", mediator);
        }

        @Override
        public List<String> topics() {
            return List.of();
        }

        @Override
        public void receive(String topic, GuildMember from, String payload) {
        }
    }
}
