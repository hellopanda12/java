package learn;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

	public class hack  {

		static String password = "";
		static String chars = "0123456789aABbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyzZ";

		//this is the MD5 hash function
		public static String getMD5 (String input) throws NoSuchAlgorithmException {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			byte[] messageDigest = MD5.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hash = no.toString(16);
			while (hash.length() < 32) {
				hash = "0" + hash;
			}
			return hash;
		}

		//this is the SHA256 hash function
		public static byte[] SHA256(String input) throws NoSuchAlgorithmException{
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			return sha256.digest(input.getBytes(StandardCharsets.UTF_8));
		}
		public static String toHexString(byte[] hash){
			BigInteger number = new BigInteger(1, hash);
			StringBuilder hexString = new StringBuilder(number.toString(16));
			while (hexString.length() < 32){
				hexString.insert(0, '0');
			}
			return hexString.toString();
		}
		//this is the brute force thing
		private static void crack(String pass) {
			for (int length = 1; length<=15; length++) {
				password = "";
				for (int n = 0; n < length; n++) {
					password += "0";
				}
				int last = length - 1;
				while(!password.equals(pass)) {
					String end = "";
					for (int n = 0; n < password.length(); n++) {
						end += "Z";
					}
					if (password.equals(end)) {
						break;
					}
					int indInChars = chars.indexOf(password.charAt(last));
                if (indInChars < chars.length() && indInChars >= 0) {
                    boolean t = true;
                    int howManyZs = 0;
                    while (t == true) {
                        if (password.charAt(password.length() - 1 - howManyZs) == 'Z') {
                            howManyZs++;
                        } else {
                            t = false;
                        }
                    }
                    String reset0s = "";
                    for (int l = 0; l < howManyZs; l++) {
                        reset0s += "0";
                    }
                    if (last < password.length() - 1 && last > 0) {
                        last--;
                        indInChars = chars.indexOf(password.charAt(last)) + 1;
                        password = password.substring(0, last) + chars.charAt(indInChars)
                                + password.substring(last + 1);
                    } else if (password.length() - howManyZs == 1) {
                    password = chars.substring(chars.indexOf(password.charAt(0)) + 1,
                                chars.indexOf(password.charAt(0)) + 2) + reset0s;
                    } else if (password.length() - howManyZs > 1 && howManyZs != 0) {
                    password = password.substring(0, password.length() - 1 - howManyZs)
                                + chars.substring(chars.indexOf(password.charAt(password.length() - 1 - howManyZs)) + 1,
                                        chars.indexOf(password.charAt(password.length() - 1 - howManyZs)) + 2)
                                + reset0s;
                    } else {
                        indInChars = chars.indexOf(password.charAt(last)) + 1;
                        password = password.substring(0, last) + chars.charAt(indInChars);
                    }
                    //this is not necessary, but it looks cool
                    //System.out.println(password);
                   
                }
            }
            if (password.equals(pass)) {
                break;
           
            }
			}
}
		public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException
		{
			for (String i : args) {
				if(i.equals("MD5")) {
					Scanner scan = new Scanner (System.in);
					System.out.println("Enter a password: ");
					String input = scan.nextLine();
					//this import and scan the file
					File file = new File("\\Users\\100121095\\OneDrive - Clear Creek ISD\\Desktop\\list.txt");
					try (Scanner scan1 = new Scanner(file)) {
						while(scan1.hasNextLine()) {
							if(scan1.nextLine().equals(input)) {
								String bcrypthash = BCrypt.hashpw(input, BCrypt.gensalt(12));
								System.out.println("Password was found in the list: " + input);
								System.out.println("MD5: " + getMD5(input));
								//System.out.println("SHA-256: " + toHexString(SHA256(input)));
								//System.out.println("BCrypt: " + bcrypthash);
								System.exit(0);
							}

						}
						if(!scan1.equals(input)) {
							System.out.println("Password not found in the list, guessing...");
							long start = System.currentTimeMillis();
							crack(input);
							long end = System.currentTimeMillis();
							long milliSecs = TimeUnit.MILLISECONDS.toMillis(end - start);
							;
							long secs = milliSecs / 1000;
							long mins = secs / 60;
							long hours = mins / 60;
							long days = hours / 24;
							milliSecs = milliSecs % 1000;
							System.out.println("The password is: " + password);
							if (hours > 0) {
								if (hours == 1) {
									System.out.println("it took " + hours + " hour and " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								} else {
									System.out.println("it took " + hours + " hours and " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								}
							} else if (mins > 0) {
								if (mins == 1) {
									System.out.println("it took " + mins + " min and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								} else {
									System.out.println("it took " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								}
							} else if (secs > 0) {
								if (secs == 1) {
									System.out.println("it took " + secs + " sec and " + milliSecs + " milliseconds to crack the password");
								} else {
									System.out.println("it took " + secs + " secs and " + milliSecs + " milliseconds to crack the password");
								}
							} else {
								System.out.println("it took " + milliSecs + " milliseconds to crack the password");
							}

							System.exit(0);

						}
						}

			}
				else if(i.equals("SHA-256")) {
					Scanner scan = new Scanner (System.in);
					System.out.println("Enter a password: ");
					String input = scan.nextLine();
					//this import and scan the file
					File file = new File("\\Users\\100121095\\OneDrive - Clear Creek ISD\\Desktop\\list.txt");
					try (Scanner scan1 = new Scanner(file)) {
						while(scan1.hasNextLine()) {
							if(scan1.nextLine().equals(input)) {
								String bcrypthash = BCrypt.hashpw(input, BCrypt.gensalt(12));
								System.out.println("Password was found in the list: " + input);
								//System.out.println("MD5: " + getMD5(input));
								System.out.println("SHA-256: " + toHexString(SHA256(input)));
								//System.out.println("BCrypt: " + bcrypthash);
								System.exit(0);
							}

						}
						if(!scan1.equals(input)) {
							System.out.println("Password not found in the list, guessing...");
							long start = System.currentTimeMillis();
							crack(input);
							long end = System.currentTimeMillis();
							long milliSecs = TimeUnit.MILLISECONDS.toMillis(end - start);
							;
							long secs = milliSecs / 1000;
							long mins = secs / 60;
							long hours = mins / 60;
							long days = hours / 24;
							milliSecs = milliSecs % 1000;
							System.out.println("The password is: " + password);
							if (hours > 0) {
								if (hours == 1) {
									System.out.println("it took " + hours + " hour and " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								} else {
									System.out.println("it took " + hours + " hours and " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								}
							} else if (mins > 0) {
								if (mins == 1) {
									System.out.println("it took " + mins + " min and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								} else {
									System.out.println("it took " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								}
								} else if (secs > 0) {
								if (secs == 1) {
									System.out.println("it took " + secs + " sec and " + milliSecs + " milliseconds to crack the password");
								} else {
									System.out.println("it took " + secs + " secs and " + milliSecs + " milliseconds to crack the password");
								}
								} else {
									System.out.println("it took " + milliSecs + " milliseconds to crack the password");
								}

							System.exit(0);

						}
					}

				}
				else if(i.equals("BCrypt")) {
					Scanner scan = new Scanner (System.in);
					System.out.println("Enter a password: ");
					String input = scan.nextLine();
					//this import and scan the file
					File file = new File("\\Users\\100121095\\OneDrive - Clear Creek ISD\\Desktop\\list.txt");
					try (Scanner scan1 = new Scanner(file)) {
						while(scan1.hasNextLine()) {
							if(scan1.nextLine().equals(input)) {
								String bcrypthash = BCrypt.hashpw(input, BCrypt.gensalt(12));
								System.out.println("Password was found in the list: " + input);
								//System.out.println("MD5: " + getMD5(input));
								//System.out.println("SHA-256: " + toHexString(SHA256(input)));
								System.out.println("BCrypt: " + bcrypthash);
								System.exit(0);
							}

						}
						if(!scan1.equals(input)) {
							System.out.println("Password not found in the list, guessing...");
							long start = System.currentTimeMillis();
							crack(input);
							long end = System.currentTimeMillis();
							long milliSecs = TimeUnit.MILLISECONDS.toMillis(end - start);
							;
							long secs = milliSecs / 1000;
							long mins = secs / 60;
							long hours = mins / 60;
							long days = hours / 24;
							milliSecs = milliSecs % 1000;
							System.out.println("The password is: " + password);
							if (hours > 0) {
								if (hours == 1) {
									System.out.println("it took " + hours + " hour and " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								} else {
									System.out.println("it took " + hours + " hours and " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								}
							} else if (mins > 0) {
								if (mins == 1) {
									System.out.println("it took " + mins + " min and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								} else {
									System.out.println("it took " + mins + " mins and " + secs + " secs and " + milliSecs
											+ " milliseconds to crack the password");
								}
							} else if (secs > 0) {
								if (secs == 1) {
									System.out.println("it took " + secs + " sec and " + milliSecs + " milliseconds to crack the password");
								} else {
									System.out.println("it took " + secs + " secs and " + milliSecs + " milliseconds to crack the password");
								}
							} else {
								System.out.println("it took " + milliSecs + " milliseconds to crack the password");
							}
							
							System.exit(0);

						}
					}

				}

			}

}

}

