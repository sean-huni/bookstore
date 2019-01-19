package xyz.lib.bookstore.dto;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.dto
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 18:23
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class ChatMessageDTO extends AbstractDTO{
    private String message;
    private String hostname;

    public ChatMessageDTO(Long id, String message, String hostname) {
        this.setId(id);
        this.message = message;
        this.hostname = hostname;
    }

    public ChatMessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + this.getId() +
                ", message='" + message + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
