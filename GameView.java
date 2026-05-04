/**
 * GameView - Observer interface for the SameGame views.
 * 
 * Any class that wants to display the game state must implement this.
 * When the model changes, it calls update() on all registered views.
 */
public interface GameView
{
   /**
    * Called by the GameModel when the game state changes.
    * The view should refresh itself based on the new model state.
    */
   void update(GameModel model);
}