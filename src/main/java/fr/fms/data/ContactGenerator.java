package fr.fms.data;

import java.util.*;

public class ContactGenerator {
    public static void main(String[] args) {
        List<Contact> contacts = generateContacts(40);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    static List<Contact> generateContacts(int count) {
        List<Contact> contacts = new ArrayList<>();
        String[] firstNames = {"Gilles", "Sarah", "Etanou", "Livio", "Emma", "Liam", "Olivia", "Noah", "Ava", "William", "Sophia", "James", "Isabella", "Oliver", "Charlotte", "Elijah", "Amelia", "Benjamin", "Mia", "Lucas"};
        String[] lastNames = {"Quodbach", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin"};
        String[] emails = {"example1@gmail.com", "example2@gmail.com", "example3@gmail.com", "example4@gmail.com", "example5@gmail.com", "example6@gmail.com", "example7@gmail.com", "example8@gmail.com", "example9@gmail.com", "example10@gmail.com"};
        String[] phoneNumbers = {"0612345678", "0623456789", "0634567890", "0645678901", "0656789012", "0667890123", "0678901234", "0689012345", "0690123456", "0601234567"};
        String[] addresses = {"123 Main Street", "456 Elm Street", "789 Oak Street", "1011 Pine Street", "1213 Maple Street", "1415 Cedar Street", "1617 Birch Street", "1819 Walnut Street", "2021 Chestnut Street", "2223 Spruce Street"};
        ContactType[] types = {ContactType.PERSONAL, ContactType.WORK, ContactType.FAMILY, ContactType.OTHER};

        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String email = emails[rand.nextInt(emails.length)];
            String phoneNumber = phoneNumbers[rand.nextInt(phoneNumbers.length)];
            String address = addresses[rand.nextInt(addresses.length)];
            ContactType type = types[rand.nextInt(types.length)];

            Contact contact = new Contact(firstName, lastName, email, phoneNumber, address, type);
            contacts.add(contact);
        }

        return contacts;
    }
}

class Contact {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;
    ContactType type;

    public Contact(String firstName, String lastName, String email, String phoneNumber, String address, ContactType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.type = type;
    }

    @Override
    public String toString() {
        return "contactRepository.save(new Contact(\"" + firstName + "\", \"" + lastName + "\", \"" + email + "\", \"" + phoneNumber + "\", \"" + address + "\", " + type + "));";
    }
}

enum ContactType {
    PERSONAL, WORK, FAMILY, OTHER
}
