package com.example.githubtrending;

import java.util.List;

public class Bean<I> {
    private int count;
    private String msg;
    private List<Items> items;

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Items> getItems(){
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class Items{
        private String repo;
        private String repo_link;
        private String desc;
        private String language;
        private String star;
        private String fork;
        private String added_star;
        private List<String> avatars;

        public String getRepo() {
            return repo;
        }

        public void setRepo(String repo) {
            this.repo = repo;
        }

        public String getRepo_link() {
            return repo_link;
        }

        public void setRepo_link(String repo_link) {
            this.repo_link = repo_link;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getFork() {
            return fork;
        }

        public void setFork(String fork) {
            this.fork = fork;
        }

        public String getAdded_star() {
            return added_star;
        }

        public void setAdded_star(String added_star) {
            this.added_star = added_star;
        }

        public List<String> getAvatars() {
            return avatars;
        }

        public void setAvatars(List<String> avatars) {
            this.avatars = avatars;
        }
    }
}
