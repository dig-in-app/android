package com.github.digin.android;

public class ImageCacheEntry {
    private String url;
    private String id;

    public ImageCacheEntry(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
