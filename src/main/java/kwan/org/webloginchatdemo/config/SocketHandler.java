package kwan.org.webloginchatdemo.config;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import kwan.org.webloginchatdemo.constant.ChatRoomType;
import kwan.org.webloginchatdemo.dto.ChatMessageDto;
import kwan.org.webloginchatdemo.dto.chatenum.MessageType;
import kwan.org.webloginchatdemo.entity.ChatMessage;
import kwan.org.webloginchatdemo.repo.ChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    Gson gson;

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {


//        System.out.println("Message : " + message.getPayload());

//        HttpHeaders headers = session.getHandshakeHeaders();
//        headers.forEach((key,value) ->{
//            System.out.println("Header Name: "+key+" Header Value: "+value);
//        });


        ChatMessage chatMessage = new ChatMessage()
                .setContent(message.getPayload())
                .setType(MessageType.CHAT.name())
                .setCreatedTime(Instant.now())
                .setRoomId(1);

        chatMessageRepo.save(chatMessage);

        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(message.getPayload()));
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Faker faker = new Faker();
       // String username = faker.name().fullName();
        String fullName = faker.name().fullName();
        ChatMessageDto chatMessageDto = new ChatMessageDto()
                .setUsername(session.getPrincipal().getName())
                .setFullName(fullName)
                .setType(MessageType.JOIN);

        System.out.println("Connect open : " + session);
        sessions.add(session);
        session.sendMessage(new TextMessage(gson.toJson(chatMessageDto)));
        sendListUserOnline();

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
       // System.out.println("Connect close : " + session.getPrincipal().getName());
        System.out.println("Connect close : ");
        sessions.remove(session);
        sendListUserOnline();

//        ChatMessage chatMessage = new ChatMessage()
//                .setType(MessageType.CLOSE)
//                .setMessage("Closed");
//        session.sendMessage(new TextMessage(gson.toJson(chatMessage)));

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        System.out.println("Connect error : ");
       // exception.printStackTrace();
    }

    private void sendListUserOnline() throws IOException {
        for (WebSocketSession session : sessions) {
            session.sendMessage(buildListUser());
        }
    }

    private TextMessage buildListUser() {
        StringBuffer listUser = new StringBuffer("list_user");
        for (WebSocketSession session : sessions) {
            listUser.append(session.getPrincipal().getName() + " \n");
        }
        ChatMessageDto chatMessageDto = new ChatMessageDto()
                .setType(MessageType.LIST_FRIENDS_ONLINE)
                .setMessage(listUser.toString());
        return new TextMessage(gson.toJson(chatMessageDto));
    }
}