package com.chat.desktop;


public class TestMain {

    public static void main(String... args) {
//        CommChannel.instantiate(MyWorkerThread.class, null, false);
//        CommChannel.makeConnection(Constant.MAIN_HOST, Constant.MAIN_HOST, Constant.MAIN_PORT);
//        Thread thread = new Thread(TestMain::process);
//        thread.start();

    }


//    private static void process() {
//        System.out.println("Command line client...");
//
//        final String fromID = "fromID";
//        final String toID = Constant.MAIN_HOST;
//        final String requestID = "requestID";
//        final String sessionID = "sessionID";
//        final SimpleMessage message = new SimpleMessage(fromID, toID, requestID, sessionID);
//        InputStreamReader bis = new InputStreamReader(System.in);
//        BufferedReader in = new BufferedReader(bis);
//
//
//        String line;
//        try {
//
//            while (true) {
//                line = in.readLine();
//                if (line != null) {
//                    if (line.equalsIgnoreCase("exit")) {
//                        break;
//                    }
//                    message.setMessage(line);
//                    System.out.println("Sending message...");
////                    CommChannel.send(message, null);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Exiting...");
//    }
}
