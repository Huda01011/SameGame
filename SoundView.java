import javax.sound.sampled.*;

public class SoundView implements GameView
{
   private int previousScore = 0;



 @Override
public void update(GameModel model)
{
   System.out.println("SoundView update called! Score: " + model.getScore() + " Previous: " + previousScore);
   
   if (model.isWon())
   {
      new Thread(() -> playWinSound()).start();
   }
   else if (model.isLost())
   {
      new Thread(() -> playLoseSound()).start();
   }
   else if (model.getScore() > previousScore)
   {
      System.out.println("Playing click sound!");
      new Thread(() -> playClickSound()).start();
   }
   previousScore = model.getScore();
}

   private void playClickSound()
   {
      playTone(800, 150);
   }

   private void playWinSound()
   {
      playTone(400, 150);
      playTone(600, 150);
      playTone(800, 300);
   }

   private void playLoseSound()
   {
      playTone(600, 150);
      playTone(400, 150);
      playTone(200, 300);
   }

   private void playTone(int frequency, int durationMs)
   {
      try
      {
         float sampleRate = 44100;
         int numSamples = (int)(sampleRate * durationMs / 1000);
         byte[] buffer = new byte[numSamples];

         for (int i = 0; i < numSamples; i++)
         {
            double angle = 2.0 * Math.PI * frequency * i / sampleRate;
            buffer[i] = (byte)(Math.sin(angle) * 70);
         }

         AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
         DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
         SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
         line.open(format);
         line.start();
         line.write(buffer, 0, buffer.length);
         line.drain();
         line.close();
      }
      catch (Exception e)
      {
         System.out.println("Sound error: " + e.getMessage());
      }
   }
}