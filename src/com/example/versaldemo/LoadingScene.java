package com.example.versaldemo;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import com.example.versaldemo.SceneManager.SceneType;

public class LoadingScene extends BaseScene{

	@Override
	public void createScene() {
	 setBackground(new Background(2f/255f,56f/255f,32f/255f));
	attachChild(new Text(960, 640, resourcesManager.mFont, "Loading...", vbom));
	
	createTiledSprite();
    
 
	}
	private void createTiledSprite()
	{
		long[] frameDurration = {20,20,20,20, 20, 20,20};
		
	        AnimatedSprite as = new AnimatedSprite(860, 490, resourcesManager.dice_region[0], vbom);    
	        as.animate(frameDurration,0,6,true);
	        attachChild(as);
	        
	        AnimatedSprite as2 = new AnimatedSprite(960, 490, resourcesManager.dice_region[1], vbom);
	        as2.animate(frameDurration,0,6,true);
	        attachChild(as2);
	        
	        AnimatedSprite as3 = new AnimatedSprite(1060, 490, resourcesManager.dice_region[2], vbom);
	        as3.animate(frameDurration,0,6,true);
	        attachChild(as3);
	        
	        AnimatedSprite as4 = new AnimatedSprite(1160, 490, resourcesManager.dice_region[3], vbom);
	        as4.animate(frameDurration,0,6,true);
	        attachChild(as4);
	        
	        AnimatedSprite as5 = new AnimatedSprite(760, 490, resourcesManager.dice_region[5], vbom);
	        as5.animate(frameDurration,0,6,true);
	        attachChild(as5);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		 return SceneType.SCENE_LOADING;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

}
