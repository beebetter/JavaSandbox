import java.io.File;

public class FolderGenerator {
    public static String[] getMessages() {
        String rawMessage = "";
        String[] messages = rawMessage.split(", |\\. |\\.");
        return messages;
    }
    public static void createFolders() {
        String[] message = getMessages();
        String path = "C:" + File.separator + "!Sandbox" + File.separator + "OperationBirthDay" + File.separator;
        String nameOfFile;
        for (Integer num = 0; num < message.length; )
            try {
                File f = new File(path + message[num] + ".txt");
                f.getParentFile().mkdirs();
                f.createNewFile();
                num++;
                path += num.toString() + File.separator;
            } catch (Exception e) {

            }
    }
    public static void main(String[] args) {
        createFolders();

    }
}
