package model;

import java.util.ArrayList;

public class TextConfirm {

    public boolean checkBlackWord(String str, ArrayList<String> bannedWords) {
        String newstr = this.deleteSC(str);

        for (int i = 0; i < bannedWords.size(); i++) {
            if (newstr.contains(bannedWords.get(i)))
                return true;
        }
        return false;
    }

    public String deleteSC(String text) {
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        return text.replaceAll(match, "");
    }
}
