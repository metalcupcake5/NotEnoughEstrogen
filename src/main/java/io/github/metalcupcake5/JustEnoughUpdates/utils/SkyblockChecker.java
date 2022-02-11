package io.github.metalcupcake5.JustEnoughUpdates.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;

import java.util.*;
import java.util.stream.Collectors;

/*
    adapted from https://github.com/Kraineff/Skyblocker/ because i cannot code
 */

public class SkyblockChecker {
    public static boolean inSkyblock = false;
    public static boolean inDwarvenMines = false;
    public static String location = "";
    public static String time = "";
    public static String season = "";
    public static int day = 1;

    public static void check() {
        List<String> sidebar = getSidebar();

        if (sidebar.isEmpty()) return;
        if (sidebar.get(sidebar.size() - 1).equals("www.hypixel.net")) {
            inSkyblock = sidebar.get(0).contains("SKYBLOCK");

            if(inSkyblock) {
                ArrayList<String> timeArray = new ArrayList<>(Arrays.asList(sidebar.get(3).split(" ")));
                if (!timeArray.isEmpty()) time = timeArray.get(1);

                ArrayList<String> locationArray = new ArrayList<>(Arrays.asList(sidebar.get(4).split(" ")));
                locationArray.remove(0);
                locationArray.remove(0);
                location = String.join(" ", locationArray);
                inDwarvenMines = isInDwarvenMines(location);

                ArrayList<String> dateArray = new ArrayList<>(Arrays.asList(sidebar.get(2).split(" ")));
                season = dateArray.get(1);
                String[] splitDate = sidebar.get(2).split("([A-z])\\w+");
                day = Integer.parseInt(splitDate[splitDate.length - 1].trim());
            }
        } else {
            inSkyblock = false;
            inDwarvenMines = false;
        }
    }

    private static List<String> getSidebar() {
        List<String> lines = new ArrayList<>();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return lines;

        Scoreboard scoreboard = client.world.getScoreboard();
        if (scoreboard == null) return lines;
        ScoreboardObjective sidebar = scoreboard.getObjectiveForSlot(1);
        if (sidebar == null) return lines;

        Collection<ScoreboardPlayerScore> scores = scoreboard.getAllPlayerScores(sidebar);
        List<ScoreboardPlayerScore> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .collect(Collectors.toList());

        if (list.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
        } else {
            scores = list;
        }

        for (ScoreboardPlayerScore score : scores) {
            Team team = scoreboard.getPlayerTeam(score.getPlayerName());
            if (team == null) return lines;
            String text = team.getPrefix().getString() + team.getSuffix().getString();
            if (text.trim().length() > 0)
                lines.add(text);
        }

        lines.add(sidebar.getDisplayName().getString());
        Collections.reverse(lines);

        return lines;
    }

    private static boolean isInDwarvenMines(String locationLine) {
        for(SkyblockLocations loc: SkyblockLocations.values()){
            if(locationLine.contains(loc.s)) return true;
        }
        return false;
    }

}