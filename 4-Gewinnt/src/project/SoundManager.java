package project;

import java.io.*;

import javax.sound.sampled.*;

/**
 * This class represents a single playing sound file, and can either be looped
 * or played just once.
 * 
 * @author Enes Akg�mus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 * 
 *
 */
public class SoundManager {

	private FloatControl volume;
	private Clip clip;

	/**
	 * This function initializes the sound-class, and as to be called for each
	 * soundtrack individually. WARNING Can only play .wav files.
	 * 
	 * @param filename
	 *            The filename of the soundtrack in the music folder.
	 * @param ambient
	 *            The boolean, true means background(looped) or just one time.
	 * @throws Exception
	 *             If file is not found.
	 */
	public SoundManager(String filename, boolean ambient) throws Exception {
		String musicFile = System.getProperty("user.dir") + File.separator + "resources" + File.separator + filename + ".wav";

		InputStream in = new FileInputStream(musicFile);
		BufferedInputStream bufferedIn = new BufferedInputStream(in);
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);

		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		if (ambient) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			clip.start();
		}

	}

	/**
	 * This function sets the volume corresponding to the slider bar in options.
	 * 
	 * @param value
	 *            The new value, from which the volume is calculated.
	 */
	public void setVolume(int value) {
		value = value / 10;
		this.volume.setValue(-24.0F + (value * 3.0F));
	}

	public void startMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		clip.stop();
	}

}