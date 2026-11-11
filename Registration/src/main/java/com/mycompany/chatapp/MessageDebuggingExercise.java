/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/*
 * STUDENT DEBUGGING EXERCISE - FIXED VERSION
 * All methods kept, errors corrected.
 */
public class MessageDebuggingExercise {

    private static final int MAX_LIMIT = 100;
    private static String[] sentMessagesArray = new String[MAX_LIMIT];
    private static String[] storedMessagesArray = new String[MAX_LIMIT];
    private static String[] messageHashArray = new String[MAX_LIMIT];
    private static String[] messageIdArray = new String[MAX_LIMIT];
    private static String[] recipientArray = new String[MAX_LIMIT];

    // CORRECTED: Separate arrays for stored messages so they don't overwrite sent
    private static String[] storedMessageIdArray = new String[MAX_LIMIT];
    private static String[] storedMessageHashArray = new String[MAX_LIMIT];
    private static String[] storedRecipientArray = new String[MAX_LIMIT];

    private static int sentCount = 0;
    private static int storedCount = 0;
    private static int totalMessagesSent = 0;

    public static boolean checkMessageID(String messageId) {
        if (messageId == null) return false;
        // CORRECTED: was < 10, must allow exactly 10
        return messageId.length() <= 10;
    }

    public static String checkRecipientCell(String cellNumber) {
        // CORRECTED: was \\d{8}, must be \\d{9} for +27712345678
        if (cellNumber != null && cellNumber.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted. Please try again.";
    }

    public static String checkMessageLength(String messageText) {
        if (messageText == null) return "Message is empty.";
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }
        int overrun = messageText.length() - 250;
        return "Message exceeds 250 characters by " + overrun + ".";
    }

    public static String createMessageHash(String messageId, int currentMsgNum, String messageText) {
        if (messageId == null || messageId.length() < 2) return "";
        if (messageText == null || messageText.trim().isEmpty()) return "";
        String firstTwoId = messageId.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        // CORRECTED: was words[words.length] -> ArrayIndexOutOfBoundsException
        String lastWord = words[words.length - 1];
        return (firstTwoId + ":" + currentMsgNum + ":" + firstWord + lastWord).toUpperCase();
    }

    public static String sendOrStoreMessage(Scanner input, String messageId,
                                            String recipient, String messageText) {
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();

        String hash = createMessageHash(messageId, totalMessagesSent + 1, messageText);

        if (choice == 1) {
            if (sentCount >= MAX_LIMIT) return "Sent inbox full.";
            sentMessagesArray[sentCount] = messageText;
            messageHashArray[sentCount] = hash;
            messageIdArray[sentCount] = messageId;
            recipientArray[sentCount] = recipient;
            sentCount++;
            // CORRECTED: was totalMessagesSent--
            totalMessagesSent++;

            System.out.println("Message successfully sent.");
            return "Message successfully sent.";
        } else if (choice == 2) {
            System.out.println("Message disregarded.");
            return "Message disregarded.";
        } else if (choice == 3) {
            if (storedCount >= MAX_LIMIT) return "Stored inbox full.";
            storedMessagesArray[storedCount] = messageText;
            // CORRECTED: was trackingIndex = sentCount + storedCount
            storedMessageIdArray[storedCount] = messageId;
            storedMessageHashArray[storedCount] = hash;
            storedRecipientArray[storedCount] = recipient;
            storedCount++;

            saveMessage(messageId, hash, recipient, messageText);
            return "Message successfully stored.";
        }

        return "Invalid choice.";
    }

    public static String getLongestStoredMessageText() {
        if (storedCount == 0) return "No messages stored.";
        // CORRECTED: method was incomplete, now completed
        String longest = storedMessagesArray[0];
        for (int i = 1; i < storedCount; i++) {
            if (storedMessagesArray[i] != null && storedMessagesArray[i].length() > longest.length()) {
                longest = storedMessagesArray[i];
            }
        }
        return longest;
    }

    public static void saveMessage(String messageId, String hash, String recipient, String messageText) {
        try (FileWriter writer = new FileWriter("storedMessages.txt", true)) {
            writer.write("ID: " + messageId + "\n");
            writer.write("Hash: " + hash + "\n");
            writer.write("Recipient: " + recipient + "\n");
            writer.write("Message: " + messageText + "\n");
            writer.write("-----\n");
            System.out.println("Message saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    public static String generateMessageID() {
        Random rand = new Random();
        long num = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(num).substring(0, 10);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many messages do you want to enter? ");
        int num = input.nextInt();
        input.nextLine();

        for (int i = 0; i < num; i++) {
            String messageId = generateMessageID();
            System.out.println("\nMessage ID: " + messageId);
            System.out.println("Valid ID: " + checkMessageID(messageId));

            System.out.print("Enter recipient (+27...): ");
            String recipient = input.nextLine();
            System.out.println(checkRecipientCell(recipient));

            System.out.print("Enter message: ");
            String messageText = input.nextLine();
            System.out.println(checkMessageLength(messageText));

            if (checkMessageLength(messageText).equals("Message ready to send.")) {
                System.out.println(sendOrStoreMessage(input, messageId, recipient, messageText));
            }
        }

        System.out.println("\nTotal sent: " + totalMessagesSent);
        System.out.println("Longest stored: " + getLongestStoredMessageText());
        input.close();
    }
}
