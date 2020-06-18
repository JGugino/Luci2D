/*Created By: Gugino
 *Date Created: Jun 18, 2020
 */
package com.gugino.engine.audio;

import java.util.HashMap;

import com.gugino.engine.audio.enums.ClipStatus;
import com.gugino.engine.util.debug.Debug;

public class AudioManager {
	public HashMap <String, AudioClip> importedAudioClips = new HashMap<String, AudioClip>();
	
	public void importAudioClip(String _clipID, String _clipPath, boolean _isLooping) {
		if(!importedAudioClips.containsKey(_clipID)) {
			AudioClip _clip = new AudioClip(_clipID, _clipPath, _isLooping);
			importedAudioClips.put(_clipID, _clip);
		}else {
			Debug.printError("Clip ID " + _clipID + " is already taken!");
		}
	}
	
	public void removeAudioClip(String _clipID) {
		if(importedAudioClips.containsKey(_clipID)) {
			AudioClip _clip = importedAudioClips.get(_clipID);
			
			if(_clip != null) {
				_clip.stopClip();
				importedAudioClips.remove(_clipID);
			}
		}
	}
	
	public void stopAllAudioClips() {
		for (AudioClip _clip : importedAudioClips.values()) {
			if(_clip.getClipStatus() == ClipStatus.PLAYING) {
				_clip.stopClip();
			}
		}
	}
	
	public AudioClip findClipByID(String _clipID) {
		if(importedAudioClips.containsKey(_clipID)) {
			return importedAudioClips.get(_clipID);
		}else {
			Debug.printError("Clip " + _clipID + " doesn't exist!");
			return null;
		}
	}
}
