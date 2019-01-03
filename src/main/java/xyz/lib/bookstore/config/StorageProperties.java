package xyz.lib.bookstore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

import static xyz.lib.bookstore.constants.Constants.RESOURCES_IMAGES;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.config
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 18:54
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = new File(RESOURCES_IMAGES).getAbsolutePath();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
