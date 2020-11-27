package com.sm.net.jw.wol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sm.net.project.Language;

public class ScheduleForMeeting {
    private boolean present = true;
    private String bibleChapters;
    private ScheduleForMeeting.Song song1;
    private ScheduleForMeeting.Part openingComments;
    private ScheduleForMeeting.Part treasuresTalk;
    private ObservableList<Point> talkPointsList;
    private ScheduleForMeeting.Part treasuresDigging;
    private ObservableList<ScheduleForMeeting.Point> diggingPointsList;
    private ScheduleForMeeting.BibleReadingPart treasuresBibleReading;
    private ObservableList<ScheduleForMeeting.MinistryPart> ministryPartsList;
    private ScheduleForMeeting.Song song2;
    private ObservableList<ScheduleForMeeting.ChristiansPart> christiansPartsList;
    private ScheduleForMeeting.ChristiansPart congregationBibleStudy;
    private ScheduleForMeeting.Part review;
    private ScheduleForMeeting.Song song3;

    public ScheduleForMeeting(ArrayList<String> relevantRows, Language language) {
        this.defineFirstInfo(relevantRows, language);
        this.defineTreasures(relevantRows, language);
        this.defineMinistry(relevantRows, language);
        this.defineChristians(relevantRows, language);
        this.defineLastInfo(relevantRows, language);
    }

    private void defineFirstInfo(ArrayList<String> relevantRows, Language language) {
        int index = this.find(relevantRows, language.getString("wol.pattern.song1"));
        if (this.isValid(relevantRows, index)) {
            this.song1 = new ScheduleForMeeting.Song((String)relevantRows.get(index));
            --index;
            if (this.isValid(relevantRows, index)) {
                this.bibleChapters = (String)relevantRows.get(index);
                index += 2;
                if (this.isValid(relevantRows, index)) {
                    this.openingComments = new ScheduleForMeeting.Part((String)relevantRows.get(index));
                }
            }
        } else {
            this.present = false;
        }

    }

    private void defineTreasures(ArrayList<String> relevantRows, Language language) {
        int talkIndex = this.find(relevantRows, language.getString("wol.pattern.treasures"));
        int diggingIndex = this.find(relevantRows, language.getString("wol.pattern.digging"));
        int bibleReadingIndex = this.find(relevantRows, language.getString("wol.pattern.biblereading"));
        int i;
        if (this.isValid(relevantRows, talkIndex) && this.isValid(relevantRows, diggingIndex)) {
            ++talkIndex;
            this.treasuresTalk = new ScheduleForMeeting.Part((String)relevantRows.get(talkIndex));
            this.talkPointsList = FXCollections.observableArrayList();

            for(i = talkIndex + 1; i < diggingIndex; ++i) {
                this.talkPointsList.add(new ScheduleForMeeting.Point((String)relevantRows.get(i), language));
            }
        }

        if (this.isValid(relevantRows, diggingIndex) && this.isValid(relevantRows, bibleReadingIndex)) {
            this.treasuresDigging = new ScheduleForMeeting.Part((String)relevantRows.get(diggingIndex));
            this.diggingPointsList = FXCollections.observableArrayList();

            for(i = diggingIndex + 1; i < bibleReadingIndex; ++i) {
                this.diggingPointsList.add(new ScheduleForMeeting.Point((String)relevantRows.get(i), language));
            }
        }

        if (this.isValid(relevantRows, bibleReadingIndex)) {
            this.treasuresBibleReading = new ScheduleForMeeting.BibleReadingPart((String)relevantRows.get(bibleReadingIndex));
        }

    }

    private void defineMinistry(ArrayList<String> relevantRows, Language language) {
        int ministryIndex = this.find(relevantRows, language.getString("wol.pattern.ministry"));
        int christiansIndex = this.find(relevantRows, language.getString("wol.pattern.christians"));
        if (this.isValid(relevantRows, ministryIndex) && this.isValid(relevantRows, christiansIndex)) {
            this.ministryPartsList = FXCollections.observableArrayList();

            for(int i = ministryIndex + 1; i < christiansIndex; ++i) {
                this.ministryPartsList.add(new ScheduleForMeeting.MinistryPart((String)relevantRows.get(i), language));
            }
        }

    }

    private void defineChristians(ArrayList<String> relevantRows, Language language) {
        int song2Index = this.find(relevantRows, language.getString("wol.pattern.song2"));
        int congrBibleStudyIndex = this.find(relevantRows, language.getString("wol.pattern.congrbiblestudy"));
        if (this.isValid(relevantRows, song2Index) && this.isValid(relevantRows, congrBibleStudyIndex)) {
            this.song2 = new ScheduleForMeeting.Song((String)relevantRows.get(song2Index));
            this.christiansPartsList = FXCollections.observableArrayList();

            for(int i = song2Index + 1; i < congrBibleStudyIndex; ++i) {
                this.christiansPartsList.add(new ScheduleForMeeting.ChristiansPart((String)relevantRows.get(i), language));
            }

            this.congregationBibleStudy = new ScheduleForMeeting.ChristiansPart((String)relevantRows.get(congrBibleStudyIndex), language);
        }

    }

    private void defineLastInfo(ArrayList<String> relevantRows, Language language) {
        int reviewIndex = this.find(relevantRows, language.getString("wol.pattern.review"));
        int song3Index = this.findAt(relevantRows, language.getString("wol.pattern.song1"), 2);
        if (this.isValid(relevantRows, reviewIndex) && this.isValid(relevantRows, song3Index)) {
            this.review = new ScheduleForMeeting.Part((String)relevantRows.get(reviewIndex));
            this.song3 = new ScheduleForMeeting.Song((String)relevantRows.get(song3Index));
        }

    }

    private boolean isValid(ArrayList<String> relevantRows, int index) {
        return index > -1 && index < relevantRows.size();
    }

    private int find(ArrayList<String> relevantRows, String pattern) {
        for(int i = 0; i < relevantRows.size(); ++i) {
            if (((String)relevantRows.get(i)).toLowerCase().matches(pattern)) {
                return i;
            }
        }

        return -1;
    }

    private int findAt(ArrayList<String> relevantRows, String pattern, int at) {
        if (at > 0) {
            int count = 0;

            for(int i = 0; i < relevantRows.size(); ++i) {
                if (((String)relevantRows.get(i)).toLowerCase().matches(pattern)) {
                    ++count;
                    if (count == at) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    public static Integer firstNumberInText(String text) {
        Matcher m = Pattern.compile("\\d+").matcher(text);
        return m.find() ? Integer.valueOf(m.group()) : null;
    }

    public static String removeLastBrackets(String text) {
        if (text.charAt(text.length() - 1) == ')') {
            int index = text.lastIndexOf("(");
            return index > -1 ? text.substring(0, index).trim() : text;
        } else {
            return text;
        }
    }

    public static String removeLastColon(String text) {
        return text.charAt(text.length() - 1) == ':' ? text.substring(0, text.length() - 1).trim() : text;
    }

    public static String getLastBrachets(String text) {
        if (text.charAt(text.length() - 1) == ')') {
            int index = text.lastIndexOf("(");
            return index > -1 ? text.substring(index, text.length()).trim() : text;
        } else {
            return "";
        }
    }

    public static String removeOpenAndCloseSquareBrackets(String text) {
        if (text.isEmpty()) {
            return text;
        } else {
            return text.charAt(0) == '[' && text.charAt(text.length() - 1) == ']' ? text.substring(1, text.length() - 1).trim() : text;
        }
    }

    public static String removeOpenAndCloseBrackets(String text) {
        if (text.isEmpty()) {
            return text;
        } else {
            return text.charAt(0) == '(' && text.charAt(text.length() - 1) == ')' ? text.substring(1, text.length() - 1).trim() : text;
        }
    }

//    public String print() {
//        String text = "- Bible chapters: ";
//        if (this.bibleChapters != null) {
//            text = text + this.bibleChapters;
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Song 1: ";
//        if (this.song1 != null) {
//            text = text + this.song1.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Opening comments: ";
//        if (this.openingComments != null) {
//            text = text + this.openingComments.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Treasures Talk: ";
//        if (this.treasuresTalk != null) {
//            text = text + this.treasuresTalk.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Treasures Talk Points: ";
//        ScheduleForMeeting.Point p;
//        Iterator var3;
//        if (this.talkPointsList != null) {
//            for(var3 = this.talkPointsList.iterator(); var3.hasNext(); text = text + p.print()) {
//                p = (ScheduleForMeeting.Point)var3.next();
//            }
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Treasures Digging: ";
//        if (this.treasuresDigging != null) {
//            text = text + this.treasuresDigging.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Treasures Digging Points: ";
//        if (this.diggingPointsList != null) {
//            for(var3 = this.diggingPointsList.iterator(); var3.hasNext(); text = text + p.print()) {
//                p = (ScheduleForMeeting.Point)var3.next();
//            }
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Treasures Bible Reading: ";
//        if (this.treasuresBibleReading != null) {
//            text = text + this.treasuresBibleReading.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Treasures Ministry Part: ";
//        ScheduleForMeeting.MinistryPart p;
//        if (this.ministryPartsList != null) {
//            for(var3 = this.ministryPartsList.iterator(); var3.hasNext(); text = text + p.print()) {
//                p = (ScheduleForMeeting.MinistryPart)var3.next();
//            }
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Song 2: ";
//        if (this.song2 != null) {
//            text = text + this.song2.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Christians Part: ";
//        ScheduleForMeeting.ChristiansPart p;
//        if (this.christiansPartsList != null) {
//            for(var3 = this.christiansPartsList.iterator(); var3.hasNext(); text = text + p.print()) {
//                p = (ScheduleForMeeting.ChristiansPart)var3.next();
//            }
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Congregation Bible Study: ";
//        if (this.congregationBibleStudy != null) {
//            text = text + this.congregationBibleStudy.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Review: ";
//        if (this.review != null) {
//            text = text + this.review.print();
//        }
//
//        text = text + "\n";
//        text = text + "\n";
//        text = text + "- Song 3: ";
//        if (this.song3 != null) {
//            text = text + this.song3.print();
//        }
//
//        text = text + "\n";
//        return text;
//    }

    public ScheduleForMeeting.Song getSong1() {
        return this.song1;
    }

    public void setSong1(ScheduleForMeeting.Song song1) {
        this.song1 = song1;
    }

    public String getBibleChapters() {
        return this.bibleChapters;
    }

    public void setBibleChapters(String bibleChapters) {
        this.bibleChapters = bibleChapters;
    }

    public ScheduleForMeeting.Part getOpeningComments() {
        return this.openingComments;
    }

    public void setOpeningComments(ScheduleForMeeting.Part openingComments) {
        this.openingComments = openingComments;
    }

    public ScheduleForMeeting.Part getTreasuresTalk() {
        return this.treasuresTalk;
    }

    public void setTreasuresTalk(ScheduleForMeeting.Part treasuresTalk) {
        this.treasuresTalk = treasuresTalk;
    }

    public ScheduleForMeeting.Part getTreasuresDigging() {
        return this.treasuresDigging;
    }

    public void setTreasuresDigging(ScheduleForMeeting.Part treasuresDigging) {
        this.treasuresDigging = treasuresDigging;
    }

    public ObservableList<ScheduleForMeeting.Point> getTalkPointsList() {
        return this.talkPointsList;
    }

    public void setTalkPointsList(ObservableList<ScheduleForMeeting.Point> talkPointsList) {
        this.talkPointsList = talkPointsList;
    }

    public ObservableList<ScheduleForMeeting.Point> getDiggingPointsList() {
        return this.diggingPointsList;
    }

    public void setDiggingPointsList(ObservableList<ScheduleForMeeting.Point> diggingPointsList) {
        this.diggingPointsList = diggingPointsList;
    }

    public ScheduleForMeeting.BibleReadingPart getTreasuresBibleReading() {
        return this.treasuresBibleReading;
    }

    public void setTreasuresBibleReading(ScheduleForMeeting.BibleReadingPart treasuresBibleReading) {
        this.treasuresBibleReading = treasuresBibleReading;
    }

    public ObservableList<ScheduleForMeeting.MinistryPart> getMinistryPartsList() {
        return this.ministryPartsList;
    }

    public void setMinistryPartsList(ObservableList<ScheduleForMeeting.MinistryPart> ministryPartsList) {
        this.ministryPartsList = ministryPartsList;
    }

    public ScheduleForMeeting.Song getSong2() {
        return this.song2;
    }

    public void setSong2(ScheduleForMeeting.Song song2) {
        this.song2 = song2;
    }

    public ObservableList<ScheduleForMeeting.ChristiansPart> getChristiansPartsList() {
        return this.christiansPartsList;
    }

    public void setChristiansPartsList(ObservableList<ScheduleForMeeting.ChristiansPart> christiansPartsList) {
        this.christiansPartsList = christiansPartsList;
    }

    public ScheduleForMeeting.ChristiansPart getCongregationBibleStudy() {
        return this.congregationBibleStudy;
    }

    public void setCongregationBibleStudy(ScheduleForMeeting.ChristiansPart congregationBibleStudy) {
        this.congregationBibleStudy = congregationBibleStudy;
    }

    public ScheduleForMeeting.Song getSong3() {
        return this.song3;
    }

    public void setSong3(ScheduleForMeeting.Song song3) {
        this.song3 = song3;
    }

    public ScheduleForMeeting.Part getReview() {
        return this.review;
    }

    public void setReview(ScheduleForMeeting.Part review) {
        this.review = review;
    }

    public boolean isPresent() {
        return this.present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public class BibleReadingPart {
        private String text;
        private String textPart;
        private Integer min;
        private String bible;
        private String material;

        public BibleReadingPart(String text) {
            this.text = text;
            this.textPart = this.defineTextPart();
            this.min = ScheduleForMeeting.firstNumberInText(this.text);
            this.bible = this.defineBible();
            this.material = this.defineMaterial();
        }

        private String defineTextPart() {
            int i = this.text.indexOf("(");
            return i > -1 ? this.text.substring(0, i - 1).trim() : "";
        }

        private String defineBible() {
            int i = this.text.indexOf(")");
            return i > -1 ? ScheduleForMeeting.removeLastBrackets(this.text.substring(i + 1, this.text.length()).trim()) : "";
        }

        private String defineMaterial() {
            return ScheduleForMeeting.removeOpenAndCloseBrackets(ScheduleForMeeting.getLastBrachets(this.text));
        }

        public String print() {
            String text = "";
            text = text + "\n";
            text = text + "Text: ";
            text = text + this.text;
            text = text + "\n";
            text = text + "TextPart: ";
            if (this.textPart != null) {
                text = text + this.textPart;
            }

            text = text + "\n";
            text = text + "Min: ";
            if (this.min != null) {
                text = text + this.min.toString();
            }

            text = text + "\n";
            text = text + "Bible: ";
            if (this.bible != null) {
                text = text + this.bible;
            }

            text = text + "\n";
            text = text + "Material: ";
            if (this.material != null) {
                text = text + this.material;
            }

            return text;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTextPart() {
            return this.textPart;
        }

        public void setTextPart(String textPart) {
            this.textPart = textPart;
        }

        public Integer getMin() {
            return this.min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public String getBible() {
            return this.bible;
        }

        public void setBible(String bible) {
            this.bible = bible;
        }

        public String getMaterial() {
            return this.material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }
    }

    public class ChristiansPart {
        private String text;
        private String textPart;
        private Integer min;
        private String body;

        public ChristiansPart(String text, Language language) {
            this.text = text;
            this.textPart = this.defineTextPart();
            int firstBrackets = this.text.indexOf("(");
            if (firstBrackets > -1) {
                this.min = ScheduleForMeeting.firstNumberInText(this.text.substring(firstBrackets, text.length()));
            } else {
                this.min = ScheduleForMeeting.firstNumberInText(this.text);
            }

            this.body = this.defineBody(language);
        }

        private String defineTextPart() {
            int i = this.text.indexOf("(");
            return i > -1 ? this.text.substring(0, i - 1).trim() : "";
        }

        private String defineBody(Language language) {
            int i = this.text.indexOf(language.getString("wol.ministry.bodydivisor"));
            return i > -1 ? this.text.substring(i + 1, this.text.length()).trim() : "";
        }

        public String print() {
            String text = "";
            text = text + "\n";
            text = text + "Text: ";
            text = text + this.text;
            text = text + "\n";
            text = text + "TextPart: ";
            if (this.textPart != null) {
                text = text + this.textPart;
            }

            text = text + "\n";
            text = text + "Min: ";
            if (this.min != null) {
                text = text + this.min.toString();
            }

            text = text + "\n";
            text = text + "Body: ";
            if (this.body != null) {
                text = text + this.body;
            }

            text = text + "\n";
            return text;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTextPart() {
            return this.textPart;
        }

        public void setTextPart(String textPart) {
            this.textPart = textPart;
        }

        public Integer getMin() {
            return this.min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public String getBody() {
            return this.body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    public class MinistryPart {
        private String text;
        private String textPart;
        private Integer min;
        private String body;
        private String material;
        private MinistryType ministryType;
        private MinistryTypeTranslated ministryTypeTranslated;

        public MinistryPart(String text, Language language) {
            this.text = text;
            this.textPart = this.defineTextPart(language);
            this.min = ScheduleForMeeting.firstNumberInText(this.text);
            this.body = this.defineBody(language);
            this.material = this.defineMaterial();
            this.ministryType = this.defineMinistryType(language);
            this.ministryTypeTranslated = this.defineMinistryTypeTranslated(language);
        }

        private String defineTextPart(Language language) {
            int i = this.text.indexOf(language.getString("wol.ministry.divisor"));
            String textPart = i > -1 ? this.text.substring(0, i).trim() : this.text;
            return ScheduleForMeeting.removeLastBrackets(textPart).trim();
        }

        private String defineBody(Language language) {
            int i = this.text.indexOf(language.getString("wol.ministry.bodydivisor"));
            return i > -1 ? ScheduleForMeeting.removeLastBrackets(this.text.substring(i + 1, this.text.length()).trim()) : "";
        }

        private String defineMaterial() {
            return ScheduleForMeeting.removeOpenAndCloseBrackets(ScheduleForMeeting.getLastBrachets(this.text));
        }

        private MinistryType defineMinistryType(Language language) {
            if (!this.textPart.isEmpty()) {
                String text = this.textPart.toLowerCase().trim();
                if (text.matches(language.getString("wol.ministry.initialcall1"))) {
                    return MinistryType.INITIAL_CALL;
                }

                if (text.matches(language.getString("wol.ministry.returnvisit1"))) {
                    return MinistryType.RETURN_VISIT;
                }

                if (text.matches(language.getString("wol.ministry.returnvisit2"))) {
                    return MinistryType.RETURN_VISIT;
                }

                if (text.matches(language.getString("wol.ministry.returnvisit3"))) {
                    return MinistryType.RETURN_VISIT;
                }

                if (text.matches(language.getString("wol.ministry.biblestudy1"))) {
                    return MinistryType.BIBLE_STUDY;
                }

                if (text.matches(language.getString("wol.ministry.talk1"))) {
                    return MinistryType.TALK;
                }
            }

            return MinistryType.DISCUSSION;
        }

        private MinistryTypeTranslated defineMinistryTypeTranslated(Language language) {
            return this.ministryType != null ? MinistryType.getMinistryTypeTranslated(this.ministryType, language) : null;
        }

        public String print() {
            String text = "";
            text = text + "\n";
            text = text + "Text: ";
            text = text + this.text;
            text = text + "\n";
            text = text + "TextPart: ";
            if (this.textPart != null) {
                text = text + this.textPart;
            }

            text = text + "\n";
            text = text + "Min: ";
            if (this.min != null) {
                text = text + this.min.toString();
            }

            text = text + "\n";
            text = text + "Body: ";
            if (this.body != null) {
                text = text + this.body;
            }

            text = text + "\n";
            text = text + "Material: ";
            if (this.material != null) {
                text = text + this.material;
            }

            text = text + "\n";
            text = text + "MinistryType: ";
            if (this.ministryType != null) {
                text = text + this.ministryType.ordinal() + " - " + this.ministryType.getName();
            }

            text = text + "\n";
            text = text + "MinistryTypeTranslated: ";
            if (this.ministryTypeTranslated != null) {
                text = text + this.ministryTypeTranslated.getName();
            }

            text = text + "\n";
            return text;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTextPart() {
            return this.textPart;
        }

        public void setTextPart(String textPart) {
            this.textPart = textPart;
        }

        public Integer getMin() {
            return this.min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public String getBody() {
            return this.body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getMaterial() {
            return this.material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public MinistryType getMinistryType() {
            return this.ministryType;
        }

        public void setMinistryType(MinistryType ministryType) {
            this.ministryType = ministryType;
        }

        public MinistryTypeTranslated getMinistryTypeTranslated() {
            return this.ministryTypeTranslated;
        }

        public void setMinistryTypeTranslated(MinistryTypeTranslated ministryTypeTranslated) {
            this.ministryTypeTranslated = ministryTypeTranslated;
        }
    }

    public class Part {
        private String text;
        private Integer min;
        private String title;

        public Part(String text) {
            this.text = text;
            this.min = ScheduleForMeeting.firstNumberInText(this.text);
            this.title = ScheduleForMeeting.removeLastBrackets(ScheduleForMeeting.removeLastColon(this.text));
        }

        public String print() {
            String text = "";
            text = text + "\n";
            text = text + "Text: ";
            text = text + this.text;
            text = text + "\n";
            text = text + "Min: ";
            if (this.min != null) {
                text = text + this.min.toString();
            }

            text = text + "\n";
            text = text + "Title: ";
            if (this.title != null) {
                text = text + this.title;
            }

            return text;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getMin() {
            return this.min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class Point {
        private String text;
        private String bible;
        private String title;
        private String material;

        public Point(String text, Language language) {
            this.text = text;
            if (this.isPoint(this.text, language)) {
                this.bible = this.defineBible(language);
                this.title = this.defineTitle(language);
                this.material = this.defineMaterial();
            } else if (this.isPointText(this.text, language)) {
                this.bible = "";
                this.title = ScheduleForMeeting.removeOpenAndCloseSquareBrackets(this.text);
                this.material = "";
            } else {
                this.bible = "";
                this.title = text;
                this.material = "";
            }

        }

        private String defineMaterial() {
            return ScheduleForMeeting.removeOpenAndCloseBrackets(ScheduleForMeeting.getLastBrachets(this.text));
        }

        private String defineTitle(Language language) {
            int i = this.text.indexOf(language.getString("wol.pattern.point2"));
            return i > -1 ? ScheduleForMeeting.removeLastBrackets(this.text.substring(i + 1, this.text.length()).trim()) : "";
        }

        private String defineBible(Language language) {
            int i = this.text.indexOf(language.getString("wol.pattern.point2"));
            return i > -1 ? this.text.substring(0, i - 1).trim() : "";
        }

        private boolean isPoint(String text, Language language) {
            return text.matches(language.getString("wol.pattern.point"));
        }

        private boolean isPointText(String text, Language language) {
            String pattern = language.getString("wol.pattern.pointtext");
            return text.matches(pattern);
        }

        public String print() {
            String text = "";
            text = text + "\n";
            text = text + "Text: ";
            text = text + this.text;
            text = text + "\n";
            text = text + "Bible: ";
            if (this.bible != null) {
                text = text + this.bible;
            }

            text = text + "\n";
            text = text + "Title: ";
            if (this.title != null) {
                text = text + this.title;
            }

            text = text + "\n";
            text = text + "Material: ";
            if (this.material != null) {
                text = text + this.material;
            }

            text = text + "\n";
            return text;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getBible() {
            return this.bible;
        }

        public void setBible(String bible) {
            this.bible = bible;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMaterial() {
            return this.material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }
    }

    public class Song {
        private String text;
        private Integer songNo;

        public Song(String text) {
            this.text = text;
            this.songNo = ScheduleForMeeting.firstNumberInText(this.text);
        }

        public String print() {
            String text = "";
            text = text + "\n";
            text = text + "Text: ";
            text = text + this.text;
            text = text + "\n";
            text = text + "SongNo: ";
            if (this.songNo != null) {
                text = text + this.songNo.toString();
            }

            return text;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getSongNo() {
            return this.songNo;
        }

        public void setSongNo(Integer songNo) {
            this.songNo = songNo;
        }
    }
}
