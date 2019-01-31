package run;

import java.util.HashMap;
import java.util.Map;

/**
 * 行为型模式
 * 中介者模式
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class MediatorPattern {
    public static void main(String[] args) {
        testChatRoom();
    }

    private static void testChatRoom() {
        IChatRoom chatRoom = new ChatRoom();
        Member king = new DiamondMember("king");
        Member beggar = new CommonMember("beggar");

        chatRoom.register(king.getName(), king);
        chatRoom.register(beggar.getName(), beggar);

        king.sendText("beggar", "I am king, you are a begger, hahaha");
        king.sendImage("beggar", "this is an image");

        beggar.sendText("king", "Help me, my lord");
        try {
            beggar.sendImage("king", "image?");
        } catch (Exception e) {
            System.out.println("!!!!! beggar can not send image");
        }


    }
}

/**
 * 实例：虚拟聊天室
 * <p>
 * 某论坛系统欲增加一个虚拟聊天室，允许论坛会员通过该聊天室进行信息交流，普通会员(CommonMember)可以给其他会员发送文本信息，
 * 钻石会员(DiamondMember)既可以给其他会员发送文本信息，还可以发送图片信息。该聊天室可以对不雅字符进行过滤，如“日”等字符；
 * 还可以对发送的图片大小进行控制。用中介者模式设计该虚拟聊天室。
 */
interface Member {
    void receiveText(String from, String text);

    void receiveImage(String from, String image);

    void sendText(String to, String text);

    void sendImage(String to, String image);

    void setChatRoom(IChatRoom chatRoom);

    String getName();
}

class DiamondMember implements Member {


    @Override
    public void receiveText(String from, String text) {
        System.out.println(this.name + " receiveText ==> " + " from " + from + ": " + text);
    }

    @Override
    public void receiveImage(String from, String image) {
        System.out.println(this.name + " receiveImage ==> " + " from " + from + ": " + image);
    }

    @Override
    public void sendText(String to, String text) {
        System.out.println(this.name + " sendText ==> " + " to " + to + ": " + text);
        this.chatRoom.sendText(this.name, to, text);
    }

    @Override
    public void sendImage(String to, String image) {
        System.out.println(this.name + " sendText ==> " + " to " + to + ": " + image);
        this.chatRoom.sendText(this.name, to, image);
    }

    @Override
    public void setChatRoom(IChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    private String name;
    private IChatRoom chatRoom;

    public DiamondMember(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

class CommonMember implements Member {

    @Override
    public void receiveText(String from, String text) {
        System.out.println(this.name + " receiveText ==> " + " from " + from + ": " + text);
    }

    @Override
    public void receiveImage(String from, String image) {
        System.out.println(this.name + " receiveImage ==> " + " from " + from + ": " + image);
    }

    @Override
    public void sendText(String to, String text) {
        System.out.println(this.name + " sendText ==> " + " to " + to + ": " + text);
        this.chatRoom.sendText(this.name, to, text);
    }

    @Override
    @Deprecated
    public void sendImage(String to, String image) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setChatRoom(IChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


    private String name;
    private IChatRoom chatRoom;

    public CommonMember(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

interface IChatRoom {
    void register(String who, Member member);

    void sendText(String from, String to, String text);

    void sendImage(String from, String to, String image);
}

class ChatRoom implements IChatRoom {
    private Map<String, Member> members = new HashMap<>();

    @Override
    public void register(String who, Member member) {
        members.put(who, member);
        member.setChatRoom(this);
    }

    @Override
    public void sendText(String from, String to, String text) {
        members.get(to).receiveText(from, text);
    }

    @Override
    public void sendImage(String from, String to, String image) {
        members.get(to).receiveImage(from, image);
    }


}
