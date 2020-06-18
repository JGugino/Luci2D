/*Created By: Gugino
 *Date Created: Jun 18, 2020
 */
package com.gugino.engine.audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.gugino.engine.audio.enums.ClipStatus;
import com.gugino.engine.util.debug.Debug;

public class AudioClip {

	protected String clipID;
	protected String clipPath;
	protected boolean isLooping = false;
	protected long currentClipFrame = 0;
	protected ClipStatus clipStatus;
	protected AudioInputStream clipAudioStream;
	protected Clip audioClip;
	
	protected boolean waitForEndOfClip = false;
	
	public AudioClip(String _clipID, String _clipPath, boolean _isLooping) {
		this.clipID = _clipID;
		this.clipPath = _clipPath;
		this.isLooping = _isLooping;
		
		URL _audioClip = getClass().getResource(_clipPath);
		
		if(_audioClip != null) {
				try {
					clipAudioStream = AudioSystem.getAudioInputStream(_audioClip);
					audioClip = AudioSystem.getClip();
					audioClip.open(clipAudioStream);
					
					if(isLooping) {
						audioClip.loop(Clip.LOOP_CONTINUOUSLY);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				}	
		}
	}
	
	public void playClip(boolean _waitForEnd) {
		waitForEndOfClip = _waitForEnd;
		if(clipStatus != ClipStatus.PLAYING) {
			if(audioClip != null) {
				if(!audioClip.isOpen()) {
					try {
						audioClip.open(clipAudioStream);
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				audioClip.start();
				clipStatus = ClipStatus.PLAYING;	
			}
		}else {
			if(waitForEndOfClip) {
				if(audioClip.getMicrosecondPosition() >= audioClip.getMicrosecondLength()) {
					restartClip();
				}	
			}else {
				restartClip();
			}
		}
	}
	
	public void pauseClip() {
		if(clipStatus != ClipStatus.PAUSED) {
			currentClipFrame = audioClip.getMicrosecondPosition();
			audioClip.stop();
			clipStatus = ClipStatus.PAUSED;
		}else {
			Debug.printWarning("Clip " + clipID + " is already paused!");
		}
	}
	
	public void resumeClip() {
		if(clipStatus == ClipStatus.PAUSED) {
			audioClip.setMicrosecondPosition(currentClipFrame);
			playClip(waitForEndOfClip);
		}
	}
	
	public void stopClip() {
		if(clipStatus != ClipStatus.STOPPED) {
			currentClipFrame = 0L;
			audioClip.stop();
			audioClip.close();
		}
	}
	
	protected void restartClip() {
		audioClip.stop();
		clipStatus = ClipStatus.STOPPED;
		currentClipFrame = 0L;
		audioClip.setMicrosecondPosition(currentClipFrame);
		playClip(waitForEndOfClip);
	}
	
	public String getClipID() {
		return clipID;
	}
	
	public ClipStatus getClipStatus() {
		return clipStatus;
	}
}
