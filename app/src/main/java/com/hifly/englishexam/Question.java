package com.hifly.englishexam;

public class Question {
    private int type;
    private String title;
    private String selection1;
    private String selection2;
    private String selection3;
    private String selection4;
    private String answer;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelection1() {
        return selection1 == null ? "" : selection1;
    }

    public void setSelection1(String selection1) {
        this.selection1 = selection1;
    }

    public String getSelection2() {
        return selection2 == null ? "" : selection2;
    }

    public void setSelection2(String selection2) {
        this.selection2 = selection2;
    }

    public String getSelection3() {
        return selection3 == null ? "" : selection3;
    }

    public void setSelection3(String selection3) {
        this.selection3 = selection3;
    }

    public String getSelection4() {
        return selection4 == null ? "" : selection4;
    }

    public void setSelection4(String selection4) {
        this.selection4 = selection4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
