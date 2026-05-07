package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Loremaster;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");
        Hero aragorn = new Hero("Aragorn", 120, 80, 45, 30, 200);
        Hero legolas = new Hero("Legolas", 90,  60, 55, 20, 150);
        List<Hero> party = List.of(aragorn, legolas);
        System.out.println("\n--- Party ---");
        party.forEach(System.out::println);
        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear the Mines", QuestPriority.LOW, 50, false));
        questLog.add(new Quest("Escort the Merchant", QuestPriority.NORMAL, 120, false));
        questLog.add(new Quest("Retrieve the Artifact", QuestPriority.HIGH, 300, false));
        questLog.add(new Quest("Siege of Darkhold", QuestPriority.URGENT, 500, true));
        questLog.add(new Quest("Slay the Forest Troll", QuestPriority.NORMAL, 180, false));
        questLog.add(new Quest("Break the Ancient Curse",QuestPriority.HIGH, 400, true));
        GuildHall hall = new GuildHall();
        Captain captain = new Captain("Boromir",   hall);
        Quartermaster quartermaster = new Quartermaster("Gimli", hall);
        Scout scout = new Scout("Legolas",     hall);
        Healer healer = new Healer("Gandalf",    hall);
        Loremaster loremaster = new Loremaster("Elrond", hall);

        System.out.println("\n--- Iterator: Ordered ---");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            System.out.println("  " + ordered.next());
        }

        System.out.println("\n--- Iterator: Reverse ---");
        QuestIterator reverse = questLog.reverse();
        while (reverse.hasNext()) {
            System.out.println("  " + reverse.next());
        }

        System.out.println("\n--- Iterator: Priority HIGH+ ---");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            System.out.println("  " + priority.next());
        }

        System.out.println("\n--- Iterator: Reward Sorted ---");
        QuestIterator rewardSorted = questLog.rewardSorted();
        while (rewardSorted.hasNext()) {
            System.out.println("  " + rewardSorted.next());
        }

        System.out.println("\n--- Mediator: Guild Coordination ---");
        captain.issueOrder("All officers report to the war room!");
        quartermaster.requestSupplies("Need 50 healing potions and rope.");
        scout.reportRoute("Northern pass is clear, avoid the swamp.");
        healer.prepareAid("Aid stations ready at checkpoints 1 and 3.");
        loremaster.shareLore("Ancient ward on Darkhold requires silver weapons.");
        loremaster.reportCurse("Forest Troll carries a binding hex - beware.");

        System.out.println("\n--- CouncilEngine Run ---");
        CouncilRunResult result = new CouncilEngine().runCouncil(party, questLog, hall);
        System.out.println("\n--- Final Result ---");
        System.out.println(result);
    }
}
