package com.bhaktivedanta_library.books.helper;

public class StringHelper {

    public static String removeNewLine(String s){

        // removing leading

        int startIndex=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)!='\n'){
                startIndex=i;
                break;
            }
        }
        int endIndex=0;
        for (int i = s.length()-1; i>=0; i--) {
            if(s.charAt(i)!='\n'){
                endIndex=i;
                break;
            }
        }

        return "\n"+s.substring(startIndex,endIndex)+"\n";

    }
}
