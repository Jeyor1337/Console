package com.console.config;

public class AppConfig {
    private String appName;
    private String version;
    private String author;
    private String repoUrl;

    public AppConfig(String appName, String version, String author, String repoUrl) {
        this.appName = appName;
        this.version = version;
        this.author = author;
        this.repoUrl = repoUrl;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getRepoUrl() {
        return repoUrl;
    }
}
