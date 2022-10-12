package gr.aueb.cf.ch10.projects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

/**
 * MobileApp represents a mobile contacts management system.
 */

public class MobileApp {
    // Scanner for user input
    final static Scanner in = new Scanner(System.in);
    // Path for logging
    final static Path path = Paths.get("C:/tmp/log-mobile.txt");
    // 2D array to store contacts [500 contacts][3 attributes: firstname, lastname, phonenumber]
    final static String[][] contacts = new String[500][3];
    // Pointer to the last contact index
    static int pivot = -1;


    public static void main(String[] args) {
        boolean quit = false;
        String s;
        // Main menu loop
        do {
            printGenericMenu();
            s = getChoice();

            // Check if the user wants to quit
            if (s.matches("[qQ]")) quit = true;
            else {
                try {
                    handleChoiceController(s);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!quit);

        System.out.println("Thank you for using the Mobile-Contacts Management System");

    }

    /**
     * Prints the generic menu for the user.
     */
    public static void printGenericMenu() {
        System.out.println("Select one of the following: ");
        System.out.println("1. Insert contact");
        System.out.println("2. Delete contact");
        System.out.println("3. Update contact");
        System.out.println("4. Search contact");
        System.out.println("5. Print contacts");
        System.out.println("Q/q Exit");
    }

    /**
     * Gets the user's choice from input.
     *
     * @return The user's choice as a string.
     */
    public static String getChoice() {
        System.out.println("Enter choice: ");
        return in.nextLine().trim();
    }

    /**
     * Controller method to handle the user's choice.
     *
     * @param s The user's choice as a string.
     */
    public static void handleChoiceController(String s) {
        int choice;
        String phoneNumber;

        try{
            choice = Integer.parseInt(s);
            if (!isValid(choice)) {
               throw new IllegalArgumentException("Error - Choice between 1-5");
           }

            switch (choice) {
                case 1:
                    try{
                        printContactMenu();
                        insertContactService(getFirstName(), getLastName(), getPhoneNumber());
                        System.out.println("Successful insertion");
                    } catch (IllegalArgumentException e) {
                        log(e, "Insert Contact Error");
                        throw e;
                    }
                    break;
                case 2:
                    try{
                        phoneNumber = getPhoneNumber();
                        deleteContactService(phoneNumber);
                        System.out.println("Successful deletion");
                    } catch (IllegalArgumentException e) {
                        log(e, "Delete Contact Error");
                        throw e;
                    }

                case 3:
                    try{
                        phoneNumber = getPhoneNumber();
                        getContactByPhoneNumberService(phoneNumber);
                        printContactMenu();
                        updateContactService(phoneNumber, getFirstName(), getLastName(), getPhoneNumber());
                        System.out.println("Successful update");
                    } catch (IllegalArgumentException e) {
                        log(e, "Update Contact Error");
                        throw e;
                    }

                case 4:
                    try{
                        phoneNumber = getPhoneNumber();
                        String[] contact = getContactByPhoneNumberService(phoneNumber);
                        printContact(contact);
                    } catch (IllegalArgumentException e) {
                        log(e, "Get contact Error");
                        throw e;
                    }
                    break;

                case 5:
                    try{
                        String[][] allContacts = getAllContactsService();
                        printContacts(allContacts);
                    } catch (IllegalArgumentException e) {
                        log(e, "Get contacts Error");
                        throw e;
                    }
                    break;

                default:
                    throw  new IllegalArgumentException("Invalid choice");
            }

        }catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Prints a contact.
     *
     * @param contact The contact to print.
     */
    public static  void printContact(String[] contact) {
        for (String item : contact) {
            System.out.print(item + " ");
        }
    }

    /**
     * Prints the contact menu for user input.
     */
    public static void printContactMenu() {
        System.out.println("Enter First Name, Last Name, Phone Number");
    }

    /**
     * Checks if the choice is valid (between 1-5).
     *
     * @param choice The user's choice.
     * @return True if the choice is valid, false otherwise.
     */
    public static boolean isValid(int choice) {
        return ((choice >= 1) && (choice <= 5));
    }

    /**
     * Prints the list of contacts.
     *
     * @param contacts The contacts to print.
     */
    public static void printContacts(String[][] contacts) {
        for (String[] contact :contacts) {
            System.out.printf("%s\t%s\t%s\n", contact[0], contact[1], contact[2]);
        }
    }

    /**
     * Gets the first name from the user.
     *
     * @return The first name provided by the user.
     */
    public static String getFirstName() {
        System.out.println("Enter First Name");
        return in.nextLine().trim();
    }

    /**
     * Gets the last name from the user.
     *
     * @return The last name provided by the user.
     */
    public static String getLastName() {
        System.out.println("Enter Last Name");
        return in.nextLine().trim();
    }

    /**
     * Gets the phone number from the user.
     *
     * @return The phone number provided by the user.
     */
    public static String getPhoneNumber() {
        System.out.println("Enter Phone Number");
        return in.nextLine().trim();
    }


    /*
     * Services Layer - services provided to the client
     */

    /**
     * Service to get a contact by phone number.
     *
     * @param phoneNumber The phone number to search for a contact.
     * @return The contact information (first name, last name, phone number).
     * @throws IllegalArgumentException if the contact is not found.
     */
    public static String[] getContactByPhoneNumberService(String phoneNumber) {
        try {
            String[] contact = getContactByPhoneNumber(phoneNumber);
            if (contact.length == 0) {
                throw new IllegalArgumentException("Contact not found");
            } else {
                return contact;
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Service to get all contacts.
     *
     * @return A 2D array containing all contacts' information (first name, last name, phone number).
     * @throws IllegalArgumentException if the contact list is empty.
     */
    public static String[][] getAllContactsService() throws IllegalArgumentException {
        String[][] contacts = getAllContacts();

        try {
            if (contacts.length == 0) throw new IllegalArgumentException("List is empty");
            return contacts;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Service to insert a new contact.
     *
     * @param firstname   The first name of the contact.
     * @param lastname    The last name of the contact.
     * @param phoneNumber The phone number of the contact.
     * @throws IllegalArgumentException if there is an error in the insertion.
     */
    public static void insertContactService(String firstname, String lastname, String phoneNumber) {
        try {
            if (!insertContact(firstname, lastname, phoneNumber)) {
                throw  new IllegalArgumentException("Error in insert");
            }

        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Service to update a contact.
     *
     * @param oldPhoneNumber The old phone number of the contact to update.
     * @param firstname       The new first name of the contact.
     * @param lastname        The new last name of the contact.
     * @param phoneNumber     The new phone number of the contact.
     * @throws IllegalArgumentException if there is an error in the update.
     */
    public static void updateContactService(String oldPhoneNumber, String firstname, String lastname, String phoneNumber) {
        try {
            if (!updateContact(oldPhoneNumber, firstname, lastname, phoneNumber)) {
                throw new IllegalArgumentException("Update Error");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    /**
     * Service to delete a contact.
     *
     * @param phoneNumber The phone number of the contact to delete.
     * @throws IllegalArgumentException if there is an error in the deletion.
     */
    public static void deleteContactService(String phoneNumber) {
        try {
            if (!deleteContact(phoneNumber)) {
                throw new IllegalArgumentException("Error in delete");
            }

        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }


    /*
     * CRUD services
     */

    /**
     * Gets the index of a contact based on its phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return The index of the contact, or -1 if not found.
     */
    public static int getContactIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i <= pivot; i++) {
            if (contacts[i][2].equals(phoneNumber)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Inserts a new contact.
     *
     * @param firstname   The first name of the contact.
     * @param lastname    The last name of the contact.
     * @param phoneNumber The phone number of the contact.
     * @return True if the contact was successfully inserted, false otherwise.
     */
    public static boolean insertContact(String firstname, String lastname, String phoneNumber) {
        boolean inserted = false;

        if (isFull(contacts)) return false;
        if (getContactIndexByPhoneNumber(phoneNumber) == -1) {
            pivot++;
            contacts[pivot][0] = firstname;
            contacts[pivot][1] = lastname;
            contacts[pivot][2] = phoneNumber;
            inserted = true;
        }

        return inserted;
    }

    /**
     * Checks if the contact array is full.
     *
     * @param contacts The contacts array.
     * @return True if the array is full, false otherwise.
     */
    public static boolean isFull(String[][] contacts) {
        return (pivot == contacts.length-1);
    }

    /**
     * Updates an existing contact.
     *
     * @param oldPhoneNumber The old phone number of the contact.
     * @param firstname       The new first name of the contact.
     * @param lastname        The new last name of the contact.
     * @param phoneNumber     The new phone number of the contact.
     * @return True if the contact was successfully updated, false otherwise.
     */
    public static boolean updateContact(String oldPhoneNumber, String firstname, String lastname, String phoneNumber) {
        boolean updated = false;
        int positionToUpdate = getContactIndexByPhoneNumber(oldPhoneNumber);

        if (positionToUpdate == -1) {
            contacts[positionToUpdate][0] = firstname;
            contacts[positionToUpdate][1] = lastname;
            contacts[positionToUpdate][2] = phoneNumber;
            updated = true;
        }

        return updated;
    }

    /**
     * Deletes a contact.
     *
     * @param phoneNumber The phone number of the contact to delete.
     * @return True if the contact was successfully deleted, false otherwise.
     */
    public static boolean deleteContact(String phoneNumber) {
        int positionToDelete = getContactIndexByPhoneNumber(phoneNumber);
        boolean deleted = false;

        if (positionToDelete != -1) {
            System.arraycopy(contacts, positionToDelete + 1, contacts, positionToDelete, pivot - positionToDelete - 1);
            pivot--;
            deleted = true;
        }

        return deleted;
    }

    /**
     * Gets a contact based on its phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return The contact information (first name, last name, phone number).
     */
    public static String[] getContactByPhoneNumber(String phoneNumber) {
        int positionToReturn = getContactIndexByPhoneNumber(phoneNumber);

        if (positionToReturn == -1) {
            return new String[] {};
        } else {
            return contacts[positionToReturn];
        }
    }

    /**
     * Gets all contacts.
     *
     * @return A 2D array containing all contacts' information (first name, last name, phone number).
     */
    public static String[][] getAllContacts() {
        return Arrays.copyOf(contacts, pivot + 1);
    }

    /**
     * Logger to log exceptions and messages to a file.
     *
     * @param e        The exception to log.
     * @param messages Additional messages to log.
     */
    public static void log(Exception e, String...messages) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e + "\n");
            ps.printf("%s", (messages.length == 1) ? messages[0] : " ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
