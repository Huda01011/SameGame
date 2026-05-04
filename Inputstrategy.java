/**
 * InputStrategy - Strategy interface for game input.
 *
 * Different input methods (mouse, keyboard, etc.) implement this interface.
 * The game can switch between input methods at any time (Strategy pattern).
 */
public interface InputStrategy
{
   /**
    * Connects this input strategy to the game model and view.
    * Called when this strategy is activated.
    */
   void connect(GameModel model, GraphicalView view);

   /**
    * Disconnects this input strategy from the game.
    * Called when switching to a different strategy.
    */
   void disconnect(GraphicalView view);
}