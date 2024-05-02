package fr.fms.data;

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
