package com.bhaktivedanta_library.books.helper;

import java.util.HashMap;

public class ToolBarNameHelper {

    private HashMap<String, String> l1Map;
    private HashMap<String, String> l2Map;
    private HashMap<String, String> l3Map;

    public ToolBarNameHelper() {
        initL1();
        initL2();
        initL3();
    }

    private void initL3() {
        l3Map = new HashMap<>();
        l3Map.put("Śrī Caitanya-caritāmṛta", "CC");
        l3Map.put("Śrīmad-Bhāgavatam", "SB");

    }

    private void initL2() {
        l2Map = new HashMap<>();
        l2Map.put("Bhagavad-gītā As It Is", "BG");
        l2Map.put("The Science of Self Realization", "SSR");
        l2Map.put("Life Comes from Life", "LCFL");
    }

    private void initL1() {
        l1Map = new HashMap<>();
        l1Map.put("Teachings of Lord Kapila, the Son of Devahuti", "TLK");
        l1Map.put("Kṛṣṇa Consciousness, The Matchless Gift", "MG");
        l1Map.put("Teachings of Queen Kuntī", "TQK");
        l1Map.put("Beyond Birth & Death", "BBD");
        l1Map.put("The Perfection of Yoga", "POY");
        l1Map.put("Śrī Īśopaniṣad", "ISO");
        l1Map.put("Elevation to Kṛṣṇa Consciousness", "EKC");
        l1Map.put("Teachings of Lord Caitanya", "TLC");
        l1Map.put("The Nectar of Devotion", "NOD");

        l1Map.put("KṚṢṆA, The Supreme Personality of Godhead", "KB");
        l1Map.put("Transcendental Teachings of Prahlāda Mahārāja", "TTP");
        l1Map.put("On the Way to Kṛṣṇa", "OWK");
        l1Map.put("Rāja-Vidyā: The King of Knowledge", "RV");
        l1Map.put("The Nectar of Instruction", "NOI");
        l1Map.put("Kṛṣṇa Consciousness, The Topmost Yoga System", "TYS");
        l1Map.put("The Path of Perfection", "POP");
        l1Map.put("Easy Journey to Other Planets", "EJ");
        l1Map.put("Perfect Questions, Perfect Answers", "PQPA");
        l1Map.put("Kṛṣṇa, the Reservoir of Pleasure", "KRP");

    }


    public String getL1TitleName(String bookname, int page) {


        return l1Map.get(bookname) + " " + page;

    }

    public String getL2TitleName(String bookname, int chapter, String verse) {

        if(bookname.equals("Bhagavad-gītā As It Is"))
            return verse;
        else{
          return l2Map.get(bookname)+" "+chapter+"."+verse;
        }

    }

    public String getL3TitleName(String verse) {
        return verse;
    }
}
