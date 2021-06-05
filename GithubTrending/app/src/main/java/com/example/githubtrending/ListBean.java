package com.example.githubtrending;

import java.util.List;

public class ListBean {
    private String author;
    private String name;
    private String avatar;
    private String url;
    private String introduction;
    private String language;
    private String languageColor;
    private int star;
    private int fork;
    private int currentPeriodStarts;
    private List<BuiltByBean> builtBy;

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public int getStar(){
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFork() {
        return fork;
    }

    public void setFork(int fork) {
        this.fork = fork;
    }

    public int getCurrentPeriodStarts() {
        return currentPeriodStarts;
    }

    public void setCurrentPeriodStarts(int currentPeriodStarts) {
        this.currentPeriodStarts = currentPeriodStarts;
    }

    public List<BuiltByBean> getBuiltBy(){
        return builtBy;
    }

    public void setBuiltBy(List<BuiltByBean> builtBy) {
        this.builtBy = builtBy;
    }

    public static class BuiltByBean{
        private String username;
        private String avatar;

    }
}
