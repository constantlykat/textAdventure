package textAdventure;


public class Game{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private CLS cls_var;

    public Game(){
        parser = new Parser();
        player = new Player();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.setupGame();
        game.play(); 

    }

    public void printInformation(){
        System.out.println (currentRoom.getShortDescription());
        System.out.println(currentRoom.getExitString());
        System.out.println(currentRoom.getInventory());
        System.out.println(player.getInventory());
    }

    public void setupGame(){
        Room hallway = new Room ("hall way","You are in the hall way" , "You have entered the crowded hall way as students loudly talk on their way to class.");
        Room mathclass = new Room ("math class", "You have entered math class", "You have entered Ms. Divisions' math class and see a test in front of you");
        Room englishclass = new Room ("english class","You have entered english class", "You have netered Mr. Spell's and you see the words Logos, Ethos, and Pathos on the board. What do they mean?");
        Room scienceclass = new Room ("science class","You have entered science class", "You have entered Mr. Atom's science class. There are 3 beakers: 1 with a pink liquid, 1 with a green liquid, and 1 with a blue liquid.");
        Room artclass = new Room ("art class","You have entered art class", "You have entered Mrs. Color's art class. You see a paintbrush, canvas, and palete in front of you. ");

        Item mathtest = new Item("math test", "long descriptions");
        Item pencil = new Item("pencil", "long descriptions");
        Item pinkbeaker = new Item("pink beaker", "long descriptions");
        Item greenbeaker = new Item("green beaker", "long descriptions");
        Item purplebeaker = new Item("purple beaker", "long descriptions");
        Item bluebeaker = new Item("blue beaker", "long descriptions");
        Item canvas = new Item("canvas", "long descriptions");
        Item paintbrush = new Item("paintBrush", "long descriptions");
        Item palete = new Item("palete", "long descriptions");
        Item logos = new Item("logos", "long descriptions");
        Item ethos = new Item("ethos", "long descriptions");
        Item pathos = new Item("pathos", "long descriptions");
        Item ainmath = new Item("ainmath", "long descriptions");
        Item ainenglish = new Item("ainenglish", "long descriptions");
        Item ainscience = new Item("ainscience", "long descriptions");
        Item ainart = new Item("ainart", "long descriptions");

        hallway.setExit("math class", mathclass);
        hallway.setExit("english class", englishclass);
        hallway.setExit("science class", scienceclass);
        hallway.setExit("art class", artclass);

        mathclass.setExit("hall way", hallway);

        englishclass.setExit("hall way", hallway);

        scienceclass.setExit("hall way", hallway);

        artclass.setExit("hall way", hallway);

        mathclass.setItem("math test", mathtest);
        mathclass.setItem("pencil", pencil);
        //mathclass.setItem("ainmath", ainmath);
        scienceclass.setItem("pink beaker", pinkbeaker);
        scienceclass.setItem("green beaker", greenbeaker);
        scienceclass.setItem("blue beaker", bluebeaker);
        //scienceclass.setItem("ainscience", ainscience);
        artclass.setItem("canvas", canvas);
        artclass.setItem("paint brush", paintbrush);
        artclass.setItem("palete", palete);
        //artclass.setItem("ainart", ainart);
        englishclass.setItem("logos", logos);
        englishclass.setItem("ethos", ethos);
        englishclass.setItem("pathos", pathos);
        //englishclass.setItem("ainenglish", ainenglish);

        try {
            cls_var.main(); 
        }catch(Exception e) {
            System.out.println(e); 
        }

        currentRoom = hallway; 
        printInformation();

    }

    public void play() {
        while(true) {            
            Command command = parser.getCommand(); 
            try {
                cls_var.main(); 
            }catch(Exception e) {
                System.out.println(e); 
            }
            processCommand(command);
            printInformation();
        }
    }

    public void processCommand(Command command){
        String commandWord = command.getCommandWord().toLowerCase();

        switch(commandWord){
            case "help":
                help(command);
                break;
            case "speak":
                System.out.println (" you wanted me to speak this word, " + command.getSecondWord());
                break; 
            case "look":
                look(command);
                break;
            case "go":
                goRoom(command);
                break;
            case "grab":
                grab(command);
                break;
            case "drop":
                drop(command);
                break;
            case "paint":
                paint(command);
                break;
            case "mix":
                // mix pink beaker blue beaker 
                mix(command); 
                break; 
            case "take":
                take(command);
                break;
            case "define":
                define(command);
                break;
        }
    }


    public void help(Command command){
        if (!command.hasSecondWord()){
            System.out.println("Here is a list of your command words:");
            System.out.println("\"go\" is how you move from room to room");
            System.out.println("\"grab\" is how you get items into your inventory");
            System.out.println("\"drop\" is how you remove items from your inventory");
            System.out.println("\"drop\" is how you remove items from your inventory");
            System.out.println("\"paint\" will be used in Art Class");
            System.out.println("\"mix\" will be used in Science Class");
            System.out.println("\"take\" will be used in Math Class");
            System.out.println("\"define\" will be used in English Class");
            System.out.println("");
            System.out.println("");
        }
        else {
            System.out.println("try printing just \"help\"");
        }

        
    }

    public void goRoom(Command command){

        if (!command.hasSecondWord()){
            System.out.println ("go where?");
        }
        String direction = command.getSecondWord() + command.getLine();
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null){
            System.out.println ("you can't go there");
        }
        else{
            currentRoom = nextRoom; 
        }
    }
    
    public void look(Command command){
        String printString = "Looking at ";
        String thingToLook = null;
        if (!command.hasSecondWord()){
            System.out.println ("look at what?");
        }

        if(!command.hasLine()){
            thingToLook = command.getSecondWord();
        }

        else if(command.hasLine()) {
            thingToLook = command.getSecondWord()+ command.getLine();
        }

        if (thingToLook.equals(currentRoom.getName ())){
            printString += "the room " + currentRoom.getName() + "\n" + currentRoom.getLongDescription();
        }
        else if (currentRoom.getItem (thingToLook ) != null){
            printString += "the item " + currentRoom.getItem(thingToLook).getName() + "\n" + currentRoom.getItem(thingToLook).getDescription();
        }
        else if (player.getItem (thingToLook ) != null){
            printString += "the item " + player.getItem(thingToLook).getName() + "\n" + player.getItem(thingToLook).getDescription();
        }
        else{
            printString += "\n" + "you can't look at that";
        }
        System.out.println (printString);
    }

    public void grab(Command command){

        if (!command.hasSecondWord()){
            System.out.println ("grab what?");
        }

        String item = command.getSecondWord()+ command.getLine();
        Item itemToGrab = currentRoom.removeItem(item);

        if (itemToGrab == null){
            System.out.println ("you can't grab that");
        }
        else{
            player.setItem(item, itemToGrab);
        }
    }

    public void drop(Command command){
        if (!command.hasSecondWord()){
            System.out.println ("drop what?");
            return;
        } 

        String item = command.getSecondWord()+ command.getLine();
        Item itemToGrab = player.removeItem(item);

        if (itemToGrab == null){
            System.out.println ("you can't drop that");
            return;
        }
        else{
            currentRoom.setItem(item, itemToGrab);
        }
    }

    public void hasBeaker(){
        //check player hash map for pink and blue
        //if (player.getInventory ("pink beaker")){}

        
    }

    public void mix(Command command){
        
        if (!command.hasSecondWord()){
            System.out.println ("grab what?");
        }

       
        String itemToGrab = player.getInventory();

        if (itemToGrab == null){
            System.out.println ("you can't grab that");
        }
        else{
            
        }
        //declare and initialize purple beaker 
    }

    public void take(Command command){

    }

    public void define(Command command){

        if (!command.hasSecondWord()){
            System.out.println ("define what?");
        }

        String word = command.getSecondWord();

        if (word.equals("logos")){
            System.out.println("Logos:appeals to the audience's reason, building up logical arguments.");
            Item logos= currentRoom.removeItem(word);
        }
        
        else if (word.equals("ethos")){
            System.out.println("Ethos:appeals to the speaker's status or authority, making the audience more likely to trust them.");
            Item ethos = currentRoom.removeItem(word);
        }
        
        else if (word.equals("pathos")){
            System.out.println("Pathos:appeals to the emotions.");
            Item pathos = currentRoom.removeItem(word);
        }
        
        else{
            System.out.println("You cannot define that word");
        }
        if (currentRoom.checkEmpty() ) {
        	player.setItem("ainenglish" );
        }
        
        }
        
    
    
        public void paint(Command command){
        if (!command.hasSecondWord()){
            System.out.println ("paint what?");
            return;
        } 

        ;        
    }

    

    public boolean endGame(){
        return true;
    }
}


