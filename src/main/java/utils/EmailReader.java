package utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailReader {
    
    public static String getConfirmationLink(String email, String password) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");

        Session session = Session.getInstance(props);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", email, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        if (messages.length == 0) {
            System.out.println("⚠️ No new emails found.");
            return null;
        }

        Message firstMessage = messages[messages.length - 1];
        String content = getTextFromMessage(firstMessage);

        Pattern pattern = Pattern.compile("https://[\\w./?=&#_-]+");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }

    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < mimeMultipart.getCount(); i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                result.append(bodyPart.getContent().toString());
            }
            return result.toString();
        }
        return "";
    }
}
