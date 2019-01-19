package xyz.lib.bookstore.config;

import org.springframework.stereotype.Component;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.config
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 18:30
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class ApplicationPropertiesConfig {

    private final Topic topic = new Topic();

    public static class Topic {
        private String message;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public Topic getTopic() {
        return topic;
    }
}
