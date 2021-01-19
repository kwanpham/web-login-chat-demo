package kwan.org.webloginchatdemo.dto;

import kwan.org.webloginchatdemo.dto.chatenum.MessageType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by https://github.com/kwanpham
 */
@Data
@Accessors(chain = true)
public class ChatMessageDto {

    private String username;
    private String fullName;
    private String message;
    private MessageType type;

}
