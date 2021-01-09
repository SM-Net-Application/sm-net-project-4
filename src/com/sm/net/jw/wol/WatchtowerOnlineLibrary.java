package com.sm.net.jw.wol;

import java.time.LocalDate;
import java.util.ArrayList;

import com.sm.net.project.Language;

public class WatchtowerOnlineLibrary {
    public WatchtowerOnlineLibrary() {
    }

    public static String createLink(Language language, LocalDate date) {
        String linkPattern = "https://wol.jw.org/%s/wol/dt/r%s/lp-%s/%d/%d/%d";
        String languageCode = language.getString("wol.language.code");
        String languageNo = language.getString("wol.language.no");
        String languageShortcode = language.getString("wol.language.shortcode");
        int dateYear = date.getYear();
        int dateMonth = date.getMonthValue();
        int dateDay = date.getDayOfMonth();
        String link = String.format(linkPattern, languageCode, languageNo, languageShortcode, dateYear, dateMonth, dateDay);
        return link;
    }

    public static ArrayList<String> relevantHTMLRows(String sourceCode) {
        ArrayList<String> list = null;
        if (!sourceCode.isEmpty()) {
            list = new ArrayList<>();
            String[] codeRows = sourceCode.split("\n");
            String[] var6 = codeRows;
            int var5 = codeRows.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String codeRow = var6[var4];
                codeRow = codeRow.trim();
                if (!codeRow.isEmpty() && codeRow.matches(".+id=\"p\\d+\".+")) {
                    list.add(codeRow);
                }
            }
        }

        return list;
    }

    public static class LanguageKey {
        public static final String LANGUAGECODE = "wol.language.code";
        public static final String LANGUAGESHORTCODE = "wol.language.shortcode";
        public static final String LANGUAGENO = "wol.language.no";
        public static final String PATTERNSONGANDPRAY = "wol.pattern.song1";
        public static final String PATTERNTREASURES = "wol.pattern.treasures";
        public static final String PATTERNDIGGING = "wol.pattern.digging";
        public static final String PATTERNBIBLEREADING = "wol.pattern.biblereading";
        public static final String PATTERNPOINT = "wol.pattern.point";
        public static final String PATTERNPOINT2 = "wol.pattern.point2";
        public static final String PATTERNPOINTTEXT = "wol.pattern.pointtext";
        public static final String PATTERNMINISTRY = "wol.pattern.ministry";
        public static final String PATTERNCHRISTIANS = "wol.pattern.christians";
        public static final String PATTERNSONG = "wol.pattern.song2";
        public static final String PATTERNCONGRBIBLESTUDY = "wol.pattern.congrbiblestudy";
        public static final String PATTERNREVIEW = "wol.pattern.review";
        public static final String MINISTRYTYPEDISCUSSION = "wol.ministry.discussion";
        public static final String MINISTRYTYPEINITIALCALL = "wol.ministry.initialcall";
        public static final String MINISTRYTYPERETURNVISIT = "wol.ministry.returnvisit";
        public static final String MINISTRYTYPEBIBLESTUDY = "wol.ministry.biblestudy";
        public static final String MINISTRYTYPETALK = "wol.ministry.talk";
        public static final String PATTERNINITIALCALL1 = "wol.ministry.initialcall1";
        public static final String PATTERNRETURNVISIT1 = "wol.ministry.returnvisit1";
        public static final String PATTERNRETURNVISIT2 = "wol.ministry.returnvisit2";
        public static final String PATTERNRETURNVISIT3 = "wol.ministry.returnvisit3";
        public static final String PATTERNBIBLESTUDY1 = "wol.ministry.biblestudy1";
        public static final String PATTERNTALK1 = "wol.ministry.talk1";
        public static final String PATTERNMINISTRYDIVISOR = "wol.ministry.divisor";
        public static final String PATTERNMINISTRYBODYDIVISOR = "wol.ministry.bodydivisor";

        public LanguageKey() {
        }
    }

    public static class Pattern {
        public static final String RELEVANT_ROW = ".+id=\"p\\d+\".+";

        public Pattern() {
        }
    }
}
