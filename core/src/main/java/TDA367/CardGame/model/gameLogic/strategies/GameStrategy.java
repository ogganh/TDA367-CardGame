package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;

/**
 * Interface för ett regelverk för olika kortspel. Används av GoFishRules och kan implementeras för andra spel.
 */

public interface GameStrategy {
    /**  
     * Initialiserar spelet mha av ett GameState, t.ex. delar ut kort till spelare. 
     */
    void setup(GameState state);    
    /** 
    * Tar input från klassen PlayerAction och uppdaterar GameState baserat på spelets regler.
    */
    void handleTurn(GameState state, PlayerAction action);  
    int getCurrentPlayerIndex();
    /**  
     * Kallas på via GameController efter varje spelrunda för att kolla om spelet är slut. 
     * */
    boolean isGameOver(GameState state);    
}
