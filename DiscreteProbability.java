import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.text.AttributedString;
import java.util.*;
import java.math.BigInteger;

public class DiscreteProbability extends JFrame {
	private static JFrame menu, dice, coin, card;
	private static JButton diceOption, coinOption, cardOption, rollDice, dealCard, tossCoin;
	private static JTextField screen, numHeads, numTails, actHeads, actTails, preHeads, preTails;
	private static JTextArea sequence;
	private static JScrollPane scroll;
	private static int diceNumber, coinNum, heads, tails, ones, twos, threes, fours, fives, sixes, tabs;
	private static double headsOne, headsTwo, tailsOne, tailsTwo, onesOne, onesTwo, twosOne, twosTwo,
							threesOne, threesTwo, foursOne, foursTwo, fivesOne, fivesTwo, sixesOne, sixesTwo;
	private static String coinFace;
	private static BigInteger waysHeads, waysTails, waysOne, waysTwo, waysThree, waysFour, waysFive, waysSix;

	public DiscreteProbability() {
		menu = new JFrame();
		cardOption = new JButton("Shuffle Cards");
		cardOption.setBounds(125, 150, 150, 30);
		menu.add(cardOption);
		cardOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card = new JFrame();
				activateCards();
				card.setSize(400, 500);
				card.setLayout(null);
				card.setVisible(true);
				menu.setVisible(false);
			}
		});
		diceOption = new JButton("Roll Dice");
		diceOption.setBounds(150, 200, 100, 30);
		menu.add(diceOption);
		diceOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dice = new JFrame();
				activateDice();
				dice.setSize(400, 500);
				dice.setLayout(null);
				dice.setVisible(true);
				menu.setVisible(false);
			}
		});
		coinOption = new JButton("Toss Coin");
		coinOption.setBounds(150, 250, 100, 30);
		menu.add(coinOption);
		coinOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coin = new JFrame();
				activateCoin();
				coin.setSize(400, 500);
				coin.setLayout(null);
				coin.setVisible(true);
				menu.setVisible(false);
			}
		});
		menu.setSize(400, 500);
		menu.setLayout(null);
		menu.setVisible(true);
	}
	private static void activateCards() {
		String[] spades = {"Ace-Spades", "2-Spades", "3-Spades", "4-Spades", "5-Spades", "6-Spades", "7-Spades",
			"8-Spades", "9-Spades", "10-Spades", "Jack-Spades", "Queen-Spades", "King-Spades"};
		String[] hearts = {"Ace-Hearts", "2-Hearts", "3-Hearts", "4-Hearts", "5-Hearts", "6-Hearts", "7-Hearts",
			"8-Hearts", "9-Hearts", "10-Hearts", "Jack-Hearts", "Queen-Hearts", "King-Hearts"};
		String[] diamonds = {"Ace-Diamonds", "2-Diamonds", "3-Diamonds", "4-Diamonds", "5-Diamonds", "6-Diamonds",
			"7-Diamonds", "8-Diamonds", "9-Diamonds", "10-Diamonds", "Jack-Diamonds", "Queen-Diamonds", "King-Diamonds"};
		String[] clubs = {"Ace-Clubs", "2-Clubs", "3-Clubs", "4-Clubs", "5-Clubs", "6-Clubs", "7-Clubs", "8-Clubs",
			"9-Clubs", "10-Clubs", "Jack-Clubs", "Queen-Clubs", "King-Clubs"};
		String[][] deck = {spades, hearts, diamonds, clubs};
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < 13; k++) {
				System.out.println(deck[i][k]);
			}
		}
		ArrayList<String> orderDeck = orderCards(deck);
		ArrayList<String> shuffledDeck = shuffleCards(orderDeck);
		boolean replacement = false;
		dealCard = new JButton("Deal Card");
		dealCard.setBounds(150, 250, 100, 30);
		cardOption.add(dealCard);
		dealCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(shuffledDeck.get(0));
			}
		});
	}
	private static ArrayList<String> orderCards(String[][] deck) {
		ArrayList<String> orderDeck = new ArrayList<String>();
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < 13; k++) {
				String card = deck[i][k];
				orderDeck.add(card);
			}
		}
		return orderDeck;
	}
	private static ArrayList<String> shuffleCards(ArrayList<String> orderDeck) {
		ArrayList<String> shuffledDeck = new ArrayList<String>();
		int cardNum = 0;
		String card = "";
		while(!orderDeck.isEmpty()) {
			int size = orderDeck.size();
			System.out.println(size);
			cardNum = (int) Math.random() * (size-1);
			System.out.println(cardNum);
			card = shuffledDeck.get(cardNum);
			System.out.println(card);
			shuffledDeck.add(card);
			orderDeck.remove(card);
		}
		return shuffledDeck;
	}
	private static void activateDice() {
		rollDice = new JButton("Roll Dice");
		rollDice.setBounds(150, 100, 100, 30);
		dice.add(rollDice);
		screen = new JTextField();
		screen.setBounds(150, 200, 100, 30);
		dice.add(screen);
		sequence = new JTextArea();
		sequence.setBounds(100, 250, 200, 70);
		dice.add(sequence);
		tabs = 0;
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				diceNumber = (int)(Math.random() * 6) + 1;
				if(diceNumber == 1)
					ones++;
				else if(diceNumber == 2)
					twos++;
				else if(diceNumber == 3)
					threes++;
				else if(diceNumber == 4)
					fours++;
				else if(diceNumber == 5)
					fives++;
				else
					sixes++;
				screen.setText(""+diceNumber);
				sequence.append(""+diceNumber);
				if(sequence.getText().length() % 28 == 0) {
					sequence.append("\n");
					tabs++;
				}
				String contents = sequence.getText();
				int length = contents.length()-tabs;
				double lengthD = (double) contents.length()-tabs;
				double constantOne = 1.0/6;
				double constantTwo = 5.0/6;
				waysOne = calculateCombination(length, ones);
				onesOne = waysOne.doubleValue() * Math.pow(constantOne, ones) * Math.pow(constantTwo, length - ones);
				waysTwo = calculateCombination(length, twos);
				twosOne = waysTwo.doubleValue() * Math.pow(constantOne, twos) * Math.pow(constantTwo, length - twos);
				waysThree = calculateCombination(length, threes);
				threesOne = waysThree.doubleValue() * Math.pow(constantOne, threes) * Math.pow(constantTwo, length - threes);
				waysFour = calculateCombination(length, fours);
				foursOne = waysFour.doubleValue() * Math.pow(constantOne, fours) * Math.pow(constantTwo, length - fours);
				waysFive = calculateCombination(length, fives);
				fivesOne = waysFive.doubleValue() * Math.pow(constantOne, fives) * Math.pow(constantTwo, length - fives);
				waysSix = calculateCombination(length, sixes);
				sixesOne = waysSix.doubleValue() * Math.pow(constantOne, sixes) * Math.pow(constantTwo, length - sixes);
				System.out.println(length);
				System.out.println(onesOne+" "+twosOne+" "+threesOne+" "+foursOne+" "+fivesOne+" "+sixesOne);
				onesTwo = ones/lengthD;
				twosTwo = twos/lengthD;
				threesTwo = threes/lengthD;
				foursTwo = fours/lengthD;
				fivesTwo = fives/lengthD;
				sixesTwo = sixes/lengthD;
				System.out.println(onesTwo+" "+twosTwo+" "+threesTwo+" "+foursTwo+" "+fivesTwo+" "+sixesTwo);

			}
		});
	}
	private static void activateCoin() {
		tossCoin = new JButton("Toss Coin");
		tossCoin.setBounds(150, 50, 100, 30);
		coin.add(tossCoin);
		screen = new JTextField();
		screen.setBounds(150, 150, 100, 30);
		coin.add(screen);
		numHeads = new JTextField();
		numHeads.setBounds(150, 300, 40, 30);
		coin.add(numHeads);
		numTails = new JTextField();
		numTails.setBounds(210, 300, 40, 30);
		coin.add(numTails);
		sequence = new JTextArea();
		sequence.setBounds(100, 200, 200, 70);
		coin.add(sequence);
		preHeads = new JTextField();
		preHeads.setBounds(40, 350, 140, 30);
		coin.add(preHeads);
		preTails = new JTextField();
		preTails.setBounds(220, 350, 140, 30);
		coin.add(preTails);
		actHeads = new JTextField();
		actHeads.setBounds(40, 400, 140, 30);
		coin.add(actHeads);
		actTails = new JTextField();
		actTails.setBounds(220, 400, 140, 30);
		coin.add(actTails);
		tabs = 0;
		tossCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coinNum = (int) Math.round(Math.random());
				if(coinNum == 0) {
					coinFace = "H";
					heads++;
				}
				else {
					coinFace = "T";
					tails++;
				}
				screen.setText(coinFace);
				sequence.append(coinFace);
				numHeads.setText(""+heads);
				numTails.setText(""+tails);
				if(sequence.getText().length() % 24 == 0) {
					sequence.append("\n");
					tabs++;
				}
				String contents = sequence.getText();
				int length = contents.length()-tabs;
				System.out.println(length);
				double lengthD = (double) contents.length()-tabs;
				waysHeads = calculateCombination(length, heads);
				System.out.println(waysHeads);
				headsOne = waysHeads.doubleValue() * Math.pow(0.5, length);
				waysTails = calculateCombination(length, tails);
				System.out.println(waysTails);
				tailsOne = waysTails.doubleValue() * Math.pow(0.5, length);
				headsTwo = heads/lengthD;
				tailsTwo = tails/lengthD;
				preHeads.setText(""+headsOne);
				preTails.setText(""+tailsOne);
				actHeads.setText(""+headsTwo);
				actTails.setText(""+tailsTwo);
			}
		});
	}
	private static BigInteger calculateCombination(int n, int k) {
		int nMinusK = n - k;
		int num = 0;
		int numTwo = 0;
		if(nMinusK > n) {
			num = nMinusK;
			numTwo = k;
		}
		else {
			num = k;
			numTwo = nMinusK;
		}
		BigInteger numerator = new BigInteger("1");
		for(int i = n; i > num; i--) {
			numerator = numerator.multiply(BigInteger.valueOf(i));
		}
		BigInteger denominator = new BigInteger("1");
		for(int i = numTwo; i >= 1; i--) {
			denominator = denominator.multiply(BigInteger.valueOf(i));
		}
		return numerator.divide(denominator);
	}
	public static void main(String [] args) {
		new DiscreteProbability();
	}
}