# Name
Password Cracker

Language: Java

# Description
This is a password cracker that uses both dictionary and brute-force. It uses MD5, SHA-256, and BCrypt in order to hashes and find the correct password. The program will search through the list of 10000 passwords and find the matching password. If there are no match, a brute force attack will occur. Depending on the length of the password, the program will find the matching password in a specific time. 

# Installation
You can just copy and paste it or get the raw version and copy and paste. Just change the package and class name to match yours. You have to make a seperate class for the BCrypt and another class for the password cracker. For the list, you have to download the list and change the file destination to your file. 

# Usage
To get the command line arguments and change the type of hash, go to Run Configuration, Arguments, and type in the name of the hash function that you want to use. Depending on the has function you use, it will spit out what you want. For the brute force, if the password is not found in the list, the brute force will run and depending on the length of the password, it will take a certain amount of time to crack. 

# Credits
BCrypt - lokeshgupta1981
I couldn't do BCrypt. However a found a website that included a source code from lokeshgupta1981. url: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

Brute Force - https://codereview.stackexchange.com/questions/114594/brute-force-passwords-in-java
This website had the brute force. This created a starting point that allowed me to get the brute force. I used part of this code while also adding my own code. 

top 10000 passwords
https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10-million-password-list-top-10000.txt
