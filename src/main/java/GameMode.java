package main.java;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.*;

public abstract class GameMode {
    private int numberOfTries;
    private int combinationLength;
    private Scanner scanner;
    protected int tab[];
    protected int tabUser[];
    private int tabLength;
    private boolean comparaison;
    private int RANDOM_MAX =10;//<=========================================================================
    private int RANDOM_MIN;
    private int secretCombinationOfRandom[];
    private boolean userSucces;
    private boolean computerSucess;

   private final static Logger logger = Logger.getLogger(GameMode.class.getName());


    public GameMode(/*int numberOfTries, int combinationLength*/ GameProperties gameProperties) {
        this.numberOfTries = /* gameProperties.getNumbersOfTries()*/ numberOfTries;
        this.combinationLength = /*gameProperties.getGameLength()*/ combinationLength  ;
        //this.difficulty = gameProperties.getDifficulty()
        // même chose pour le dev mode
        RANDOM_MAX=10;
        RANDOM_MIN=0;
    }

    public GameMode(int i, int i1){}; // constructeur vide pour le MastermindDefender.....




    public GameMode challenger() {

        return null;
    }

    public GameMode duel() {
        return null;

    }

    public GameMode defender() {
        return null;

    }

    public void combinationLengthGestion() { // méthode qui gère la taille de la combinaison
        GameProperties gp = new GameProperties();
        try {
            /*logger.info("Entrez la taille souhaitée de combinaison :  de 4 min à 10 max");*/
            System.out.println("Entrez la taille souhaitée de combinaison :  de 4 min à 10 max");
            tabLength = gp.getGameLength();
            if (tabLength >= 4 && tabLength <= 10) {
                tab = new int[tabLength];
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            logger.warning("Merci de rentrer un chiffre entre 4 et 10 svp ");
            combinationLengthGestion();
        } catch (NullPointerException e) {
            logger.warning("Merci de rentrer un chiffre entre 4 et 10 svp ");
            combinationLengthGestion();
        } catch (NegativeArraySizeException e) {
            logger.warning("Merci de rentrer un chiffre entre 4 et 10 svp ");

        }
    }


    public void numberOfTriesGestion() { // méthode qui gère le nombre d'essais
        GameProperties gp = new GameProperties();
        System.out.println("Merci de rentrer le nombre voulu d'essais ");
        try {
            numberOfTries = gp.getNumbersOfTries();
            if (numberOfTries < 1 || numberOfTries > 10000) {
                throw new InputMismatchException();
            }


        } catch (InputMismatchException e) {
            logger.warning("Merci de rentrer un chiffre entre 1 et 10000 ");
            numberOfTriesGestion();
        } catch (NullPointerException e) {
            logger.warning("Merci de rentrer un chiffre ");
            numberOfTriesGestion();
        }
    }



    public void tipsGestion() {

    }

    public int randomCombination() { // génère un random entre 0 et 9
        Random random = new Random();
        return random.nextInt(RANDOM_MAX); // retourne un random dont le max est 10

    }

    public void userCombination() { // méthode qui permet dans le mode challenger de recupérer la proposition de l'utilisateur
        scanner = new Scanner(System.in);
        try {
            System.out.println("\nFaites votre propoposition");
            tabUser = new int[tabLength];
            String response = scanner.nextLine();
            if (response.length() != tabLength) {
                throw new InputMismatchException();
            }
            for (int i = 0; i < response.length(); i++) {
                tabUser[i] = Integer.parseInt(new String(String.valueOf(response.charAt(i))));
            }

        } catch (InputMismatchException e) {
            logger.warning("Merci de rentrer un chiffre entre 0 et 9 svp ");
            userCombination();
        } catch (NullPointerException e) {
            logger.warning("Merci de rentrer un chiffre entre 0 et 9 svp ");
            userCombination();
        } catch (NumberFormatException e) {
            logger.warning("Merci de rentrer un nombre");
            userCombination();
        }
    }

    public boolean comparaison() { // compare pour le mode challenger le tableau réponse de l'utilisateur avec le tableau de la combinaison secrète

        for (int i = 0; i < tabUser.length; i++) {
            if (tab[i] != tabUser[i]) {
                comparaison = false;
                return comparaison;

            }

        }
        return true;
    }

    public void randomGestion() { // génère un tableau de random avec la taille de combinaison souhaitée par l'utili
       /* combinationLengthGestion();*/
        secretCombinationOfRandom = new int[tabLength];
        for (int i = 0; i < getTabLength(); i++) {
            tab[i] = randomCombination();
            secretCombinationOfRandom[i] = tab[i];// création d'une var tierce pour stocker la combinaison et s'en resservir au besoin
           /* */
        }


    }

    public void secretCombinationOfRandomPrint(){
        logger.info("Le secret généré par l'ordinateur : "); //<===================================================
        for (int i =0; i<getTabLength(); i++){
            System.out.print(secretCombinationOfRandom[i] + ", ");
        }
    }

    public int[] getTab() {
        return tab;
    }

    public void setTab(int[] tab) {
        this.tab = tab;
    }

    //recup élément précis du tableau tableau de random
    public int getElementFromTabAt(int position) {
        return tab[position];
    }

    //sett un élément précis du tableau tab de random
    public void setElementFromTabAt(int position, int value) {
        tab[position] = value;
    }


    public int[] getTabUser() {
        return tabUser;
    }

    public void setTabUser(int[] tabUser) {
        this.tabUser = tabUser;
    }

    //recup élément précis du tableau User
    public int getElementFromTabUserAt(int position) {
        return tabUser[position];
    }

    //sett un élément précis du tableau User
    public void setElementFromTabUserAt(int position, int value) {
        tabUser[position] = value;
    }


    public int getTabLength() {
        return tabLength;
    }

    public void setTabLength(int tabLength) {
        this.tabLength = tabLength;
    }

    public boolean isComparaison() {
        return comparaison;
    }

    public void setComparaison(boolean comparaison) {
        this.comparaison = comparaison;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public int getRandomMax() {
        return RANDOM_MAX;
    }

    public void setRandomMax(int randomMax) {
        this.RANDOM_MAX = randomMax;
    }

    public int getRandomMin() {
        return RANDOM_MIN;
    }

    public void setRandomMin(int randomMin) {
        this.RANDOM_MIN = randomMin;
    }

    public int[] getSecretCombinationOfRandom() {
        return secretCombinationOfRandom;
    }

    public void setSecretCombinationOfRandom(int[] secretCombinationOfRandom) {
        this.secretCombinationOfRandom = secretCombinationOfRandom;
    }

    public boolean isUserSucces() {
        return userSucces;
    }

    public void setUserSucces(boolean userSucces) {
        this.userSucces = userSucces;
    }

    public boolean isComputerSucess() {
        return computerSucess;
    }

    public void setComputerSucess(boolean computerSucess) {
        this.computerSucess = computerSucess;
    }
}



