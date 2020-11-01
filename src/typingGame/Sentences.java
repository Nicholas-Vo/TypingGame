package typingGame;

import java.util.ArrayList;
import java.util.Random;

public class Sentences {
	public static ArrayList<String> s = new ArrayList<String>();
	static String getSentence() {
		ArrayList<String> s = new ArrayList<String>();
		Random randy = new Random();
		s.add("Fomites are objects or materials which are likely to carry infection.");
		s.add("The quick brown fox jumps over the lazy dog.");
		s.add("The the the the the the the the quick brown brown fox fox jumps jumps over the the the lazy dog.");
		s.add("F is for fire that burns down the whole town, U is for uranium..bombs!");
		s.add("Well, it’s no secret that the best thing about a secret is secretly telling someone your secret, thereby, secretly adding another secret to their secret collection of secret, secretly.");
		s.add("In Minecraft, players explore a blocky, procedurally-generated 3D world with infinite terrain.");
		s.add("The inner machinations of my mind are an enigma.");
		s.add("I knew a guy, who knew this guy, who knew this guy, who knew this guy, who knew this guy, who knew this guy, who knew this guy, who knew this guy");
		s.add("If I were to die right now in a fiery explosion due to the carelessness of a friend... Then it would just be alright.");
		return s.get(randy.nextInt(s.size()));
	}
}
